package com.vishra.job.service;

import com.vishra.job.domain.ApplicationStatus;
import com.vishra.job.dto.ApplicationResponse;
import com.vishra.job.entity.Application;
import com.vishra.job.exception.ApplicationException;
import com.vishra.job.exception.ResourceNotFoundException;
import com.vishra.job.payload.CompanyApplicationFilterRequest;
import com.vishra.job.payload.CreateApplicationRequest;
import com.vishra.job.payload.WithdrawApplicationRequest;

import java.util.List;

public interface ApplicationService {

    ApplicationResponse createApplication(Long candidateId,
                                          CreateApplicationRequest req)
            throws ApplicationException;

    ApplicationResponse getApplicationById(Long id) throws ResourceNotFoundException, ResourceNotFoundException;

    List<ApplicationResponse> getMyApplications(Long candidateId);

    List<ApplicationResponse> getApplicationsForJob(Long jobId);

    List<ApplicationResponse> getApplicationsForCompany(Long userId,
                                                        CompanyApplicationFilterRequest filterRequest
    ) throws ResourceNotFoundException;

    ApplicationResponse updateStatus(Long applicationId,
                                     Long employerId, ApplicationStatus applicationStatus)
            throws ResourceNotFoundException, ApplicationException;

    ApplicationResponse withdraw(Long applicationId, Long candidateId,
                                WithdrawApplicationRequest req)
            throws ResourceNotFoundException, ApplicationException;

    ApplicationResponse markAsRead(Long applicationId, Long employerId)
            throws ResourceNotFoundException, ApplicationException;

    ApplicationResponse toggleStar(Long applicationId, Long employerId)
            throws ResourceNotFoundException, ApplicationException;

    void deleteApplication(Long applicationId, Long candidateId)
            throws ResourceNotFoundException, ApplicationException;


    Application getApplicationEntity(Long id) throws ResourceNotFoundException;


    void markScreeningsStaleForJob(Long jobId);
}