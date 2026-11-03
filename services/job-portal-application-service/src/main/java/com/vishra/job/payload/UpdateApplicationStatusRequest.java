package com.vishra.job.payload;

import com.vishra.job.domain.ApplicationStatus;
import lombok.Data;

@Data
public class UpdateApplicationStatusRequest {
    private ApplicationStatus applicationStatus;
}
