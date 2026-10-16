package com.vishra.job.dto.response;

import com.vishra.job.domain.SocialPlatform;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class SocialLinkResponse {
    private SocialPlatform socialPlatform;
    private String url;
}
