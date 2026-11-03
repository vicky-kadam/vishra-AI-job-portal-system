package com.vishra.job.payload;

import com.vishra.job.domain.AiShortlistStatus;
import com.vishra.job.domain.ApplicationStatus;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * Optional filters for GET /api/applications/company/{companyId}.
 * All fields are nullable — null means "no filter applied".
 * Spring MVC binds these from query params via @ModelAttribute.
 */
@Data
public class CompanyApplicationFilterRequest {


    private Long jobId;


    private ApplicationStatus status;

    private Boolean isRead;

    private Boolean isStarred;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate appliedFrom;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate appliedTo;


    private AiShortlistStatus aiShortlistStatus;


    private Integer minAiScore;


    private String sortBy;
}
