package com.vishra.job.mapper;

import com.vishra.job.dto.response.JobTagResponse;
import com.vishra.job.model.JobTag;
import org.springframework.stereotype.Component;

@Component
public class JobTagMapper {
    public JobTagResponse toJobTagResponse(JobTag jobTag){
        return JobTagResponse.builder()
                .id(jobTag.getId())
                .name(jobTag.getName())
                .slug(jobTag.getSlug())
                .build();
    }
}
