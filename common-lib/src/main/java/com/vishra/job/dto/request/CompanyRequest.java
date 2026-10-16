package com.vishra.job.dto.request;

import com.vishra.job.domain.CompanySize;
import com.vishra.job.domain.CompanyStatus;
import com.vishra.job.domain.CompanyType;
import com.vishra.job.domain.IndustryType;
import com.vishra.job.dto.response.SocialLinkResponse;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CompanyRequest {
    @NotBlank(message = "Name is required")
    private String name;
    private String tagLine;
    private String description;
    private String logoUrl;
    private String coverImageUrl;
    @Pattern(regexp = "^https?://.*", message = "Website must be a valid URL")
    private String website;

    @Email(message = "Company email is required")
    private String email;

    private String phone;

    @Min(value = 1800, message = "Founded Year seems to old")
    @Max(value = 2100, message = "founded year is invalid")
    private Integer foundedYear;

    @NotNull(message = "company size is required")
    private CompanySize companySize;
    @NotNull(message = "industry type is required")
    private IndustryType industryType;

    @NotNull(message = "company type is required")
    private CompanyType companyType;

    @NotNull(message = "company status is required")
    private CompanyStatus companyStatus;
    private Long ownerId;

    private String registrationNumber;

    private List<SocialLinkResponse> socialLinkResponses;



}
