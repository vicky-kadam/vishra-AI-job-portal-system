package com.vishra.job.mapper;

import com.vishra.job.dto.JobSkillResponse;
import com.vishra.job.model.JobSkill;
import org.springframework.stereotype.Component;


@Component
public class JobSkillMapper {

    public static JobSkillResponse toJobSkillResponse(JobSkill jobSkill){
        return JobSkillResponse.builder()
                .id(jobSkill.getId())
                .slug(jobSkill.getSlug())
                .name(jobSkill.getName())
                .skillCategory(jobSkill.getSkillCategory())
                .active(jobSkill.getActive())
                .build();
    }
}
