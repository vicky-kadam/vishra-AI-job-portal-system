package com.vishra.job.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ResumeParseRequest {

    @NotBlank(message = "Resume text is required")
    private String resumeText;
}