package com.vishra.job.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vishra.job.domain.ApplicationStatus;
import com.vishra.job.dto.response.CompanyResponse;
import com.vishra.job.dto.response.JobResponse;
import com.vishra.job.dto.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationResponse {

    private Long id;
    private UserResponse candidate;
    private Long employerId;

    private CompanyResponse company;
    private JobResponse job;

    private ApplicationStatus status;

    private Long jobId;


    private Long resumeId;
    private String coverLetter;

    private BigDecimal expectedSalary;
    private LocalDate availableFrom;

    private Boolean isRead;
    private Boolean isStarred;





    private LocalDateTime withdrawnAt;
    private String withdrawnReason;

    private LocalDateTime appliedAt;
    private LocalDateTime updatedAt;


}
