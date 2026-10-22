package com.vishra.job.model.embeddable;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.math.BigDecimal;

@Embeddable
@Setter
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class SalaryRange {
    private BigDecimal salary;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;


}
