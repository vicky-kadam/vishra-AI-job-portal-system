
package com.vishra.job.mapper;

import com.vishra.job.dto.JobSkillResponse;
import com.vishra.job.dto.response.JobResponse;
import com.vishra.job.dto.response.JobTagResponse;
import com.vishra.job.model.Job;
import com.vishra.job.model.embeddable.JobLocation;
import com.vishra.job.model.embeddable.SalaryRange;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JobMapper {

    private final JobCategoryMapper jobCategoryMapper;
    private final JobSkillMapper jobSkillMapper;
    private final JobTagMapper jobTagMapper;

    public JobMapper(JobCategoryMapper jobCategoryMapper, JobSkillMapper jobSkillMapper, JobTagMapper jobTagMapper) {
        this.jobCategoryMapper = jobCategoryMapper;
        this.jobSkillMapper = jobSkillMapper;
        this.jobTagMapper = jobTagMapper;
    }

    public JobResponse toDto(Job job) {
        return toDto(job, null);
    }

    public JobResponse toDto(
            Job job,
            JobResponse.CompanySummaryResponse companyResponse
    ) {
        if (job == null) {
            return null;
        }

        // Map job skills
        Set<JobSkillResponse> skills = job.getSkills() == null
                ? Collections.emptySet()
                : job.getSkills()
                .stream()
                .map(JobSkillMapper::toJobSkillResponse)
                .collect(Collectors.toSet());

        // Map job tags
        Set<JobTagResponse> tags = job.getTags() == null
                ? Collections.emptySet()
                : job.getTags()
                .stream()
                .map(jobTagMapper::toJobTagResponse)
                .collect(Collectors.toSet());

        JobLocation location = job.getJobLocation();
        SalaryRange salary = job.getSalaryRange();

        return JobResponse.builder()
                .id(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .requirements(job.getRequirements())
                .responsibilities(job.getResponsibilities())
                .benefits(job.getBenefits())

                .company(companyResponse)
                .employerId(job.getEmployerId())
                .jobCategory(
                        jobCategoryMapper.toCategoryResponse(
                                job.getJobCategory(), false
                        )
                )

                .jobSkillResponses(skills)
                .jobTagResponses(tags)

                .address(location != null ? location.getAddress() : null)
                .city(location != null ? location.getCity() : null)
                .state(location != null ? location.getState() : null)
                .country(location != null ? location.getCountry() : null)
                .zipCode(location != null ? location.getZipCode() : null)

                .minSalary(salary != null ? salary.getMinSalary() : null)
                .maxSalary(salary != null ? salary.getMaxSalary() : null)

                .jobType(job.getJobType())
                .workMode(job.getWorkMode())
                .experienceLevel(job.getExperienceLevel())
                .jobStatus(job.getJobStatus())

                .openings(job.getOpenings())
                .applicationDeadline(job.getApplicationDeadline())
                .expiryDate(job.getExpiryDate())
                .active(job.getActive())

                .createdAt(job.getCreatedAt())
                .updatedAt(job.getUpdatedAt())
                .publishedAt(job.getPublishedAt())
                .closedAt(job.getClosedAt())
                .build();
    }
}

