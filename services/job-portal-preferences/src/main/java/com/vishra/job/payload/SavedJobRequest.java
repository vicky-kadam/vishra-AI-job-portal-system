package com.vishra.job.payload;

import lombok.Data;

@Data
public class SavedJobRequest {

    private Long jobId;


    private Long companyId;

    private String notes;
}