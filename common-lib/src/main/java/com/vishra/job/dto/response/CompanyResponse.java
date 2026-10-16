package com.vishra.job.dto.response;

import com.vishra.job.domain.CompanySize;
import com.vishra.job.domain.CompanyStatus;
import com.vishra.job.domain.CompanyType;
import com.vishra.job.domain.IndustryType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CompanyResponse {
    private Long id;
    private String name;
    private String slug;
    private String tagLine;
    private String description;
    private String logoUrl;
    private String coverImageUrl;
    private String website;
    private String email;
    private Integer foundedYear;
    private String phone;

    private CompanySize companySize;
    private CompanyStatus status;
    private CompanyType companyType;
    private IndustryType industryType;
    private Boolean verified;
    private Boolean active;
    private String registrationNumber;

    private Long ownerId;
    private List<SocialLinkResponse> socialLinkResponseList;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime verifiedAt;




}
