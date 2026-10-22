package com.vishra.job.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobCategoryRequest {
    @NotBlank(message = "name is required")
    private String name;

    @Size(max = 500, message = "description is required")
    private String description;

    private String iconUrl;
    private Long parentId;

    private Boolean active;
}
