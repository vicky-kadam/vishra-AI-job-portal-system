package com.vishra.job.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SearchEnhanceRequest {

    @NotBlank(message = "Search query is required")
    private String query;
}

