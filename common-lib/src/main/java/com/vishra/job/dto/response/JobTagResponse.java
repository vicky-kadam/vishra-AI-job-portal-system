package com.vishra.job.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class JobTagResponse {
    private Long id;
    private String slug;
    private String name;
}
