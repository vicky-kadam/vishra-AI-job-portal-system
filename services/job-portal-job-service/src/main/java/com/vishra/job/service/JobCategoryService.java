package com.vishra.job.service;

import com.vishra.job.dto.JobCategoryResponse;
import com.vishra.job.model.JobCategory;
import com.vishra.job.payload.JobCategoryRequest;

import java.util.List;

public interface JobCategoryService {
    JobCategoryResponse createJobCategory(JobCategoryRequest jobCategory) throws Exception;
    List<JobCategoryResponse> getAllJobCategories();
    JobCategoryResponse getJobCategoryById(Long id) throws Exception;
    JobCategoryResponse updateJobCategory(Long id, JobCategoryRequest jobCategory) throws Exception;
    void deleteJobCategory(Long id);
    JobCategory getCategoryEntityById(Long id);
}
