package com.vishra.job.payload;

import com.vishra.job.domain.ExperienceLevel;
import com.vishra.job.domain.JobStatus;
import com.vishra.job.domain.JobType;
import com.vishra.job.domain.WorkMode;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobSearchRequest {

    private String keyWord;
    private Long categoryId;
    private List<Long> skillIds;
    private List<Long> tagIds;

    private Long companyId;
    private String location;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;

    private JobType jobType;
    private WorkMode workMode;
    private ExperienceLevel experienceLevel;

    private JobStatus jobStatus;
    private Integer minOpenings;
    private Integer maxOpenings;

}
