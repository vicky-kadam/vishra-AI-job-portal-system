package com.vishra.job.repository;

import com.vishra.job.domain.CompanyStatus;
import com.vishra.job.domain.CompanyType;
import com.vishra.job.domain.IndustryType;
import com.vishra.job.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyServiceRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByOwnerId(Long ownerId);

    boolean existsByOwnerId(Long ownerId);
    boolean existsByName(String name);
    boolean existsBySlug(String slug);
    boolean existsByRegistrationNumber(String registrationNumber);

    @Query("""
            select c from Company c
            where (:companyType is null or c.companyType = :companyType)
              and (:industryType is null or c.industryType = :industryType)
              and (:companyStatus is null or c.status = :companyStatus)
            """)
    List<Company> findByFilters(
            @Param("companyType") CompanyType companyType,
            @Param("industryType") IndustryType industryType,
            @Param("companyStatus") CompanyStatus companyStatus
    );
}