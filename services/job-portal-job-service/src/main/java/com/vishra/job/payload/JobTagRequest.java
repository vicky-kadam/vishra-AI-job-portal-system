package com.vishra.job.payload;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.WithBy;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class JobTagRequest {
    @NotBlank(message = "name is required")
    private String name;



}
