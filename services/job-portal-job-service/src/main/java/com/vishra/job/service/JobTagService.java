package com.vishra.job.service;

import com.vishra.job.dto.response.JobTagResponse;
import com.vishra.job.model.JobTag;
import com.vishra.job.payload.JobTagRequest;

import java.util.List;
import java.util.Set;

public interface JobTagService {
    JobTagResponse createJobTag(JobTagRequest jobTag) throws Exception;
    List<JobTagResponse> getAllJobTags();
    JobTagResponse getJobTagById(Long id);
    JobTagResponse updateJobTag(Long id, JobTagRequest jobTag) throws Exception;
    void deleteJobTag(Long id) throws Exception;
    JobTag getJobTagByIds(Long id) throws Exception;
    Set<JobTag> getJobTagByIds(Set<Long> ids) throws Exception;
}
