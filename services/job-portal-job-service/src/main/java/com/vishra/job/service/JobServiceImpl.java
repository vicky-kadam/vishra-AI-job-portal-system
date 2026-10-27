package com.vishra.job.service;

import com.vishra.job.domain.JobStatus;
import com.vishra.job.dto.request.JobRequest;
import com.vishra.job.dto.response.JobResponse;
import com.vishra.job.mapper.JobMapper;
import com.vishra.job.model.Job;
import com.vishra.job.model.JobCategory;
import com.vishra.job.model.JobSkill;
import com.vishra.job.model.JobTag;
import com.vishra.job.model.embeddable.JobLocation;
import com.vishra.job.model.embeddable.SalaryRange;
import com.vishra.job.payload.JobSearchRequest;
import com.vishra.job.repository.JobRepository;
import com.vishra.job.repository.JobSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class JobServiceImpl implements JobService {

    private final JobMapper jobMapper;
    private final JobRepository jobRepository;
    private final  JobCategoryService  jobCategoryService;
    private final JobSkillService jobSkillService;
    private final JobTagService jobTagService;

    @Override
    public JobResponse createJob(Long employerId, JobRequest jobRequest) throws Exception {
       JobCategory jobCategory = jobCategoryService.getCategoryEntityById(jobRequest.getCategoryId());
        Set<JobSkill>skills = jobRequest.getSkillIds() != null ?
                jobSkillService.getJobSkillByIds(jobRequest.getSkillIds())
                : Collections.emptySet();

        Set<JobTag> tag = jobTagService.getJobTagByIds(jobRequest.getTagIds()) != null ?
                jobTagService.getJobTagByIds(jobRequest.getTagIds())
                : Collections.emptySet();
        // TODO: Fetch company by ID
        Long companyId = 1L;

        Job job = Job.builder()
                .title(jobRequest.getTitle())
                .description(jobRequest.getDescription())
                .requirements(jobRequest.getRequirements())
                .responsibilities(jobRequest.getResponsibilities())
                .benefits(jobRequest.getBenefits())
                .jobCategory(jobCategory)
                .tags(tag)
                .skills(skills)
                .companyId(companyId)
                .employerId(employerId)
                .jobLocation(buildJobLocation(jobRequest))
                .salaryRange(buildSalaryRange(jobRequest))
                .jobType(jobRequest.getJobType())
                .jobStatus(JobStatus.DRAFT)
                .workMode(jobRequest.getWorkMode())
                .experienceLevel(jobRequest.getExperienceLevel())
                .openings(
                        jobRequest.getOpenings() != null
                                ? jobRequest.getOpenings()
                                : 1
                )
                .applicationDeadline(jobRequest.getApplicationDeadline())
                .expiryDate(jobRequest.getExpiryDate())
                .active(true)
                .build();

        Job savedJob = jobRepository.save(job);

        System.out.println("Before refresh: " + savedJob.getCreatedAt());

        jobRepository.flush();

        Job refreshedJob = jobRepository.findById(savedJob.getId())
                .orElseThrow();

        System.out.println("After fetch: " + refreshedJob.getCreatedAt());
        System.out.println("After fetch: " + refreshedJob.getUpdatedAt());


        return convertToResponse(savedJob);
    }

    private JobResponse convertToResponse(Job job) {

        JobResponse.CompanySummaryResponse companyResponse =
                JobResponse.CompanySummaryResponse.builder().build().builder()
                        .id(job.getCompanyId())
                        .build();

        return jobMapper.toDto(job, companyResponse);
    }

    private SalaryRange buildSalaryRange(JobRequest jobRequest) {

        return SalaryRange.builder()
                .minSalary(jobRequest.getMinSalary())
                .maxSalary(jobRequest.getMaxSalary())
                .build();
    }

    private JobLocation buildJobLocation(JobRequest jobRequest) {

        return JobLocation.builder()
                .address(jobRequest.getAddress())
                .city(jobRequest.getCity())
                .state(jobRequest.getState())
                .country(jobRequest.getCountry())
                .zipCode(jobRequest.getZipCode())
                .build();
    }

    @Override
    public JobResponse getJobById(Long id) throws Exception {

        Job job = jobRepository.findById(id)
                .orElseThrow(() ->
                        new Exception("Job not found with this id: " + id)
                );

        return convertToResponse(job);
    }

    @Override
    public JobResponse updateJob(Long employerId, Long jobId, JobRequest jobRequest) throws Exception {
        JobCategory jobCategory = jobCategoryService.getCategoryEntityById(jobRequest.getCategoryId());
        Set<JobSkill>skills = jobRequest.getSkillIds() != null ?
                jobSkillService.getJobSkillByIds(jobRequest.getSkillIds())
                : Collections.emptySet();

        Set<JobTag> tag = jobTagService.getJobTagByIds(jobRequest.getTagIds()) != null ?
                jobTagService.getJobTagByIds(jobRequest.getTagIds())
                : Collections.emptySet();
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new Exception("Job not found with this id: " + jobId)
                );

        assertEmployer(job, employerId);
        job.setTitle(jobRequest.getTitle());
        job.setDescription(jobRequest.getDescription());
        job.setRequirements(jobRequest.getRequirements());
        job.setResponsibilities(jobRequest.getResponsibilities());
        job.setBenefits(jobRequest.getBenefits());
        job.setJobCategory(jobCategory);
        job.setSkills(skills);
        job.setTags(tag);
        job.setCompanyId(1L);


        job.setSalaryRange(buildSalaryRange(jobRequest));
        job.setJobLocation(buildJobLocation(jobRequest));

        job.setJobType(jobRequest.getJobType());
        job.setWorkMode(jobRequest.getWorkMode());
        job.setExperienceLevel(jobRequest.getExperienceLevel());
        job.setOpenings(jobRequest.getOpenings());
        job.setApplicationDeadline(jobRequest.getApplicationDeadline());
        job.setExpiryDate(jobRequest.getExpiryDate());

        jobRepository.save(job);
        return convertToResponse(job);





    }


    @Override
    public List<JobResponse> getJobs(JobSearchRequest jobSearchRequest) {

        List<Job> jobs = jobRepository.findAll(
                JobSpecification.build(jobSearchRequest)
        );

        return jobs.stream()
                .map(this::convertToResponse)
                .toList();
    }

    @Override
    public List<JobResponse> getJobByCompany(Long companyId) {

        List<Job> jobs = jobRepository.findByCompanyId(companyId);

        return jobs.stream()
                .map(this::convertToResponse)
                .toList();
    }

    @Override
    @Transactional
    public JobResponse publicJobs(Long jobId, Long employerId)
            throws Exception {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new Exception("Job not found with this id: " + jobId)
                );

        assertEmployer(job, employerId);

        if (job.getJobStatus() == JobStatus.CLOSED
                || job.getJobStatus() == JobStatus.EXPIRED) {

            throw new Exception("Job is already closed or expired");
        }

        job.setJobStatus(JobStatus.OPEN);
        job.setPublishedAt(LocalDateTime.now());
        job.setActive(true);

        Job publishedJob = jobRepository.save(job);

        return convertToResponse(publishedJob);
    }

    @Override
    @Transactional
    public JobResponse closeJob(Long jobId, Long employerId)
            throws Exception {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new Exception("Job not found with this id: " + jobId)
                );

        assertEmployer(job, employerId);

        job.setJobStatus(JobStatus.CLOSED);
        job.setClosedAt(LocalDateTime.now());
        job.setActive(false);

        Job closedJob = jobRepository.save(job);

        return convertToResponse(closedJob);
    }

    @Override
    @Transactional
    public JobResponse deleteJob(Long jobId, Long employerId)
            throws Exception {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new Exception("Job not found with this id: " + jobId)
                );

        assertEmployer(job, employerId);

        jobRepository.delete(job);

        return convertToResponse(job);
    }

    @Override
    public List<JobResponse> getAllJobsAdmin() {

        List<Job> jobs = jobRepository.findAll();

        return jobs.stream()
                .map(this::convertToResponse)
                .toList();
    }

    private void assertEmployer(Job job, Long employerId)
            throws Exception {

        if (!job.getEmployerId().equals(employerId)) {
            throw new Exception(
                    "You are not the employer who posted this job"
            );
        }
    }
}

