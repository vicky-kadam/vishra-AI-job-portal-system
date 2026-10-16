package com.vishra.job.model;

import com.vishra.job.domain.SocialPlatform;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocialLink {
    private String url;
    private SocialPlatform socialPlatform;


}
