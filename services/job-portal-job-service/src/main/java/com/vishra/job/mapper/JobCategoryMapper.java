
package com.vishra.job.mapper;

import com.vishra.job.dto.JobCategoryResponse;
import com.vishra.job.model.JobCategory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JobCategoryMapper {


    public static JobCategoryResponse toCategoryResponse(JobCategory jobCategory, boolean includeChildren) {
        List<JobCategoryResponse> subcategories = null;

                if (includeChildren && (jobCategory.getSubCategories() != null && !jobCategory.getSubCategories().isEmpty())) {
                    subcategories = jobCategory.getSubCategories().stream()
                            .map(sub -> toCategoryResponse(sub, false))
                            .toList();
                }

        JobCategoryResponse jobCategoryResponse = new JobCategoryResponse();

        jobCategoryResponse.setId(jobCategory.getId());
        jobCategoryResponse.setName(jobCategory.getName());
        jobCategoryResponse.setSlug(jobCategory.getSlug());
        jobCategoryResponse.setDescription(jobCategory.getDescription());
        jobCategoryResponse.setIconUrl(jobCategory.getIconUrl());
        jobCategoryResponse.setActive(jobCategory.getActive());

        jobCategoryResponse.setParentId(
                jobCategory.getParent() != null
                        ? jobCategory.getParent().getId()
                        : null
        );
        jobCategoryResponse.setParentName(
                jobCategory.getParent() != null
                        ? jobCategory.getParent().getName()
                        : null
        );
        jobCategoryResponse.setSubCategories(subcategories);
        jobCategoryResponse.setCreatedAt(jobCategory.getCreatedAt());
        jobCategoryResponse.setUpdatedAt(jobCategory.getUpdatedAt());

        return jobCategoryResponse;
    }
}

