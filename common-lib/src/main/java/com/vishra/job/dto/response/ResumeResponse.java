package com.vishra.job.dto.response;

import com.vishra.job.domain.ResumeTemplate;
import com.vishra.job.domain.ResumeVisibility;

import com.vishra.job.dto.PersonalInfoResponse;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResumeResponse {
    private Long id;
    private Long candidateId;
    private ResumeTemplate resumeTemplate;
    private ResumeVisibility resumeVisibility;
    private Boolean isDefault;
    private PersonalInfoResponse personalInfo;
    private String summary;
    private Integer completionScore;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
