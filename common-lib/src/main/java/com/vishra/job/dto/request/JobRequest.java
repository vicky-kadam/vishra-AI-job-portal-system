package com.vishra.job.dto.request;

import com.vishra.job.domain.ExperienceLevel;
import com.vishra.job.domain.JobType;
import com.vishra.job.domain.WorkMode;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class JobRequest {
    @NotBlank(message = "job title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    private String requirements;
    private String responsibilities;
    private String benefits;

    @NotNull(message = "Category id is required")
    private Long categoryId;

    private Set<Long> skillIds;
    private Set<Long> tagIds;

    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String country;


    @DecimalMin(value = "0.0", message = "Minimum salary cannot be negative")
    private BigDecimal minSalary;

    @DecimalMin(value = "0.0", message = "Maximum salary cannot be negative")
    private BigDecimal maxSalary;


    @NotNull(message = "Job type is required")
    private JobType jobType;

    @NotNull(message = "workMode is required")
    private WorkMode workMode;

    @NotNull(message = "Experience level is required")
    private ExperienceLevel experienceLevel;

    @Min(value = 1,message = "Openings must be at least 1")
    private Integer openings = 1;

    private LocalDate applicationDeadline;
    private LocalDate expiryDate;







}
