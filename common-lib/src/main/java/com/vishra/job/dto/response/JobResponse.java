package com.vishra.job.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vishra.job.domain.*;
import com.vishra.job.dto.JobCategoryResponse;
import com.vishra.job.dto.JobSkillResponse;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class JobResponse {
    private Long id;
    private String title;
    private String description;
    private String requirements;
    private String responsibilities;
    private String benefits;

    private CompanySummaryResponse company;
    private Long employerId;
    private JobCategoryResponse jobCategory;
    private Set<JobSkillResponse> jobSkillResponses;
    private Set<JobTagResponse> jobTagResponses;

    private String address;
    private String city;
    private String country;
    private String state;
    private String zipCode;

    private BigDecimal salary;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;



    private JobType jobType;
    private WorkMode workMode;
    private ExperienceLevel experienceLevel;
    private JobStatus jobStatus;

    private Integer openings;
    private LocalDate applicationDeadline;
    private LocalDate expiryDate;
    private Boolean active;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime publishedAt;
    private LocalDateTime closedAt;


    /**
     * Lightweight company card response.
     * Embedded inside JobResponse/JobSummaryResponse so other services
     * can show company info without a separate Feign call.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CompanySummaryResponse {

        private Long id;
        private String name;
        private String slug;
        private String logoUrl;
        private String tagline;
        private IndustryType industryType;
        private CompanySize companySize;
        private Boolean verified;

        /** HQ city and country for display on job cards. */
        private String city;
        private String country;
    }
}
