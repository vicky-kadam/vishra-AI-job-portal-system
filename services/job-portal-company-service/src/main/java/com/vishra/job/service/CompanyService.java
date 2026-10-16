package com.vishra.job.service;


import com.vishra.job.domain.CompanyStatus;
import com.vishra.job.domain.CompanyType;
import com.vishra.job.domain.IndustryType;
import com.vishra.job.dto.request.CompanyRequest;
import com.vishra.job.dto.response.CompanyResponse;
import com.vishra.job.model.Company;

import java.util.List;

public interface CompanyService {
    CompanyResponse createCompany(Long ownerId, CompanyRequest request) throws Exception;
    CompanyResponse getCompanyById(Long id) throws Exception;
    CompanyResponse getMyCompany(Long ownerId) throws Exception;
    List<CompanyResponse> getAllCompanies(CompanyType companyType, IndustryType industryType, CompanyStatus companyStatus);
    CompanyResponse updateCompany(Long companyId,Long ownerId, CompanyRequest request) throws Exception;
    CompanyResponse verifyCompany(Long companyId) throws Exception;
    CompanyResponse deactivateCompany(Long companyId) throws Exception;
    void deleteCompany(Long companyId,Long ownerId) throws Exception;

    Company getCompanyEntityById(Long id) throws Exception;
}

