package com.vishra.job.exception;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class SkillsGapRequest {

    @NotBlank(message = "Job title is required")
    private String jobTitle;

    private List<String> candidateSkills;
    private List<String> requiredSkills;
}