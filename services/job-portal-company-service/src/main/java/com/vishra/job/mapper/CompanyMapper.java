package com.vishra.job.mapper;

import com.vishra.job.dto.response.CompanyResponse;
import com.vishra.job.dto.response.SocialLinkResponse;
import com.vishra.job.model.Company;
import com.vishra.job.model.SocialLink;

import java.util.List;

public class CompanyMapper {

    private CompanyMapper() {
        // Utility class
    }

    public static CompanyResponse toResponse(Company company) {

        if (company == null) {
            return null;
        }

        CompanyResponse response = new CompanyResponse();

        response.setId(company.getId());
        response.setName(company.getName());
        response.setSlug(company.getSlug());
        response.setTagLine(company.getTagline());
        response.setDescription(company.getDescription());
        response.setLogoUrl(company.getLogoUrl());
        response.setCoverImageUrl(company.getCoverImageUrl());
        response.setWebsite(company.getWebsite());
        response.setEmail(company.getEmail());
        response.setPhone(company.getPhone());
        response.setFoundedYear(company.getFounderYear());
        response.setIndustryType(company.getIndustryType());
        response.setCompanySize(company.getCompanySize());
        response.setStatus(company.getStatus());
        response.setRegistrationNumber(company.getRegistrationNumber());
        response.setOwnerId(company.getOwnerId());
        response.setCompanyType(company.getCompanyType());
        response.setVerified(company.getIsVerified());
        response.setActive(company.getActive());
        response.setCreatedAt(company.getCreatedAt());
        response.setUpdatedAt(company.getUpdatedAt());
        List<SocialLinkResponse> socialLinks =
                company.getSocialLink() == null
                        ? List.of()
                        : company.getSocialLink().stream()
                        .map(CompanyMapper::toSocialLinkResponse)
                        .toList();

        response.setSocialLinkResponseList(socialLinks);

        return response;
    }

    private static SocialLinkResponse toSocialLinkResponse(
            SocialLink socialLink) {

        SocialLinkResponse response = new SocialLinkResponse();

        response.setSocialPlatform(socialLink.getSocialPlatform());
        response.setUrl(socialLink.getUrl());

        return response;
    }
}

