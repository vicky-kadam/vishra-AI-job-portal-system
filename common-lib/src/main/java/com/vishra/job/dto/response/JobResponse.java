package com.vishra.job.dto.response;

import com.vishra.job.domain.ExperienceLevel;
import com.vishra.job.domain.JobStatus;
import com.vishra.job.domain.JobType;
import com.vishra.job.domain.WorkMode;
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

    private com.zosh.job.common.dto.response.CompanySummaryResponse company;
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



}
