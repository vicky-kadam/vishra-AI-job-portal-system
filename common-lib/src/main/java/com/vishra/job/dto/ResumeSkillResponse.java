package com.vishra.job.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vishra.job.domain.ProficiencyLevel;
import com.vishra.job.domain.SkillCategory;
import lombok.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResumeSkillResponse {

    private Long id;
    private String skillName;
    private ProficiencyLevel proficiencyLevel;
    private Integer yearsOfExperience;
    private Integer displayOrder;
}

