package com.vishra.job.payload;

import com.vishra.job.domain.SkillCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class JobSkillRequest {
    @NotBlank(message = "name is required")
    @Size(max = 100, message = "name must be less than 100 characters")
    private String name;

    @NotNull(message = "skill category is required")
    private SkillCategory skillCategory;





}
