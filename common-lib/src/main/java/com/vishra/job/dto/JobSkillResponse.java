package com.vishra.job.dto;

import com.vishra.job.domain.SkillCategory;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JobSkillResponse {
    private Long id;
    private String slug;
    private String name;
    private SkillCategory skillCategory;
    private Boolean active;
}
