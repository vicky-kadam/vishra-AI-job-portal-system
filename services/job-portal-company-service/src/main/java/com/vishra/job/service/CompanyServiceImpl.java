package com.vishra.job.service;

import com.vishra.job.domain.CompanyStatus;
import com.vishra.job.domain.CompanyType;
import com.vishra.job.domain.IndustryType;
import com.vishra.job.dto.request.CompanyRequest;
import com.vishra.job.dto.response.CompanyResponse;
import com.vishra.job.dto.response.SocialLinkResponse;
import com.vishra.job.mapper.CompanyMapper;
import com.vishra.job.model.Company;
import com.vishra.job.model.SocialLink;
import com.vishra.job.repository.CompanyServiceRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public final class CompanyServiceImpl implements CompanyService{
    private final CompanyServiceRepository companyServiceRepository;
    @Override
    public CompanyResponse createCompany(Long ownerId, CompanyRequest request) throws Exception {
        if(companyServiceRepository.existsByOwnerId(ownerId)){
            throw new Exception("You already have a company registerd. "+" only one comany per account is allowed");
        }
        if(companyServiceRepository.existsByName(request.getName())){
            throw new Exception("Company is already exit plz choose another name plz bhai and behna");

        }
        if (request.getRegistrationNumber() != null && companyServiceRepository.existsByRegistrationNumber(request.getRegistrationNumber())){
            throw new Exception("Company already exist plz choose another registration number");
        }
        String slug = genrateUniqueSlug(request.getName());
        Company company = Company.builder()
                .name(request.getName())
                .slug(slug)
                .tagline(request.getTagLine())
                .description(request.getDescription())
                .logoUrl(request.getLogoUrl())
                .coverImageUrl(request.getCoverImageUrl())
                .website(request.getWebsite())
                .email(request.getEmail())
                .phone(request.getPhone())
                .founderYear(request.getFoundedYear())
                .industryType(request.getIndustryType())
                .companySize(request.getCompanySize())
                .status(request.getCompanyStatus())
                .registrationNumber(request.getRegistrationNumber())
                .ownerId(request.getOwnerId())
                .socialLink(mapSocialLink(request.getSocialLinkResponses()))
                .companyType(request.getCompanyType())
                .ownerId(ownerId)              // method parameter, header wala
                .isVerified(false)
                .active(true)
                .build();
        Company save = companyServiceRepository.save(company);
        return CompanyMapper.toResponse(save);

    }

    private List<SocialLink> mapSocialLink(List<SocialLinkResponse> socialLinkResponses) {
        if(socialLinkResponses == null || socialLinkResponses.isEmpty()){
            return new ArrayList<>();
        }
        return socialLinkResponses.stream().map(socialLinkResponse -> SocialLink.builder()
                .socialPlatform(socialLinkResponse.getSocialPlatform())
                .url(socialLinkResponse.getUrl())
                .build()).collect(Collectors.toList());
    }

    private String genrateUniqueSlug( String name) {
        String base = name.toLowerCase().replaceAll("[^a-z0-9\\s-]","").trim().replaceAll("[\\s-]","-");
        if(!companyServiceRepository.existsBySlug(base) ) {
            return base;
        }
        int counter = 1;
        while (companyServiceRepository.existsBySlug(base+"-"+counter)){
            counter++;
        }
        return base+"-"+counter;

    }

    @Override
    public CompanyResponse getCompanyById(Long id) throws Exception {

       Company company = companyServiceRepository.findById(id).orElseThrow(()-> new Exception("company not found with this id " + id));
       return CompanyMapper.toResponse(company);
    }

    @Override
    public CompanyResponse getMyCompany(Long ownerId) throws Exception {
        Company company = companyServiceRepository.findByOwnerId(ownerId).orElseThrow(() -> new Exception("Company not exist for ownerId " + ownerId));
        return CompanyMapper.toResponse(company);

    }


    @Override
    public List<CompanyResponse> getAllCompanies(CompanyType companyType, IndustryType industryType, CompanyStatus companyStatus) {
       List<Company> companies=  companyServiceRepository.findByFilters(companyType,industryType,companyStatus);
       return companies.stream()
               .map(CompanyMapper::toResponse)
        .collect(Collectors.toList());

    }

    @Override
    public CompanyResponse updateCompany(Long companyId, Long ownerId, CompanyRequest request) throws Exception {
        Company company = companyServiceRepository.findById(companyId).orElseThrow(() -> new Exception("Company not found with this id " + companyId));
        assertOwner(company, ownerId);
        if(!company.getName().equals(request.getName()) && companyServiceRepository.existsByName(request.getName())){
            throw new Exception("Company already exist plz choose different name");
        }
        if (request.getRegistrationNumber() != null
                && !request.getRegistrationNumber().equals(company.getRegistrationNumber())
                && companyServiceRepository.existsByRegistrationNumber(request.getRegistrationNumber())) {
            throw new Exception("Company already exist plz choose different registration number");
        }
        company.setName(request.getName());
        company.setTagline(request.getTagLine());
        company.setDescription(request.getDescription());
        company.setCoverImageUrl(request.getCoverImageUrl());
        company.setLogoUrl(request.getLogoUrl());
        company.setWebsite(request.getWebsite());
        company.setEmail(request.getEmail());
        company.setPhone(request.getPhone());
        company.setFounderYear(request.getFoundedYear());
        company.setCompanySize(request.getCompanySize());
        company.setCompanyType(request.getCompanyType());
        company.setIndustryType(request.getIndustryType());
        company.setRegistrationNumber(request.getRegistrationNumber());
        company.setSocialLink(mapSocialLink(request.getSocialLinkResponses()));



        Company save = companyServiceRepository.save(company);

        return CompanyMapper.toResponse(save);
    }

    @Override
    public CompanyResponse verifyCompany(Long companyId) throws Exception {
        Company company = companyServiceRepository.findById(companyId).orElseThrow(() -> new Exception("Company not found with this id " + companyId));
        company.setStatus(CompanyStatus.ACTIVE);
        company.setIsVerified(true);
        return CompanyMapper.toResponse(companyServiceRepository.save(company));
    }

    @Override
    public CompanyResponse deactivateCompany(Long companyId) throws Exception {
        Company company= companyServiceRepository.findById(companyId).
                orElseThrow(() -> new Exception("Company not found with this id" + companyId));
        company.setStatus(CompanyStatus.SUSPENDED);
        company.setIsVerified(false);
        company.setActive(false);

        return CompanyMapper.toResponse(companyServiceRepository.save(company));
    }

    @Override
    public void deleteCompany(Long companyId, Long ownerId) throws Exception {
        Company company = getCompanyEntityById(companyId);
        assertOwner(company,ownerId);
        companyServiceRepository.deleteById(companyId);


    }

    private void assertOwner(Company company, Long ownerId) throws Exception {
        if(!company.getOwnerId().equals(ownerId)){
            throw new Exception("You are not authorized to delete this company");
        }
    }

    @Override
    public Company getCompanyEntityById(Long id) throws Exception {
        return companyServiceRepository.findById(id).orElseThrow(() -> new Exception("Company not found with this id " + id));
    }




}
