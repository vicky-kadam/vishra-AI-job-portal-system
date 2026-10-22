package com.vishra.job.service;

import com.vishra.job.dto.request.JobRequest;
import com.vishra.job.dto.response.JobResponse;
import com.vishra.job.model.Job;
import com.vishra.job.payload.JobSearchRequest;

import java.util.List;

public interface JobService {
    JobResponse createJob(Long employerId, JobRequest jobRequest) throws Exception;
    JobResponse getJobById(Long id) throws Exception;
    JobResponse updateJob(Long employerId,Long jobId, JobRequest jobRequest) throws Exception;
    List<JobResponse> getJobs(JobSearchRequest jobSerachRequest);
    List<JobResponse> getJobByCompany(Long companyId);
    JobResponse publicJobs(Long jobId, Long employerId) throws Exception;
    JobResponse closeJob(Long jobId, Long employerId) throws Exception;
    JobResponse deleteJob(Long jobId, Long employerId) throws Exception;

    List<JobResponse> getAllJobsAdmin();


}
