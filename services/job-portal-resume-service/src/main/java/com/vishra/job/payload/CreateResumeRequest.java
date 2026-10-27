package com.vishra.job.payload;

import com.vishra.job.domain.ResumeTemplate;
import com.vishra.job.domain.ResumeVisibility;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateResumeRequest {
    @NotBlank(message = "resume title is required")
    private String title;
//    @NotNull(message = "resume template is required")
    private ResumeTemplate resumeTemplate;
//    @NotNull(message = "resume visibility is required")
    private ResumeVisibility resumeVisibility;
    private Boolean isDefault = false;

}
