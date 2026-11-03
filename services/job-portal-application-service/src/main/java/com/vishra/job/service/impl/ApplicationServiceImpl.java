package com.vishra.job.service.impl;

import com.vishra.job.domain.ApplicationStatus;
import com.vishra.job.dto.ApplicationResponse;
import com.vishra.job.dto.response.CompanyResponse;
import com.vishra.job.dto.response.JobResponse;
import com.vishra.job.dto.response.UserResponse;
import com.vishra.job.entity.Application;
import com.vishra.job.exception.ApplicationException;
import com.vishra.job.exception.ResourceNotFoundException;
import com.vishra.job.mapper.ApplicationMapper;
import com.vishra.job.payload.CompanyApplicationFilterRequest;
import com.vishra.job.payload.CreateApplicationRequest;
import com.vishra.job.payload.WithdrawApplicationRequest;
import com.vishra.job.repository.ApplicationRepository;
import com.vishra.job.repository.ApplicationSpecification;
import com.vishra.job.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    @Override
    public ApplicationResponse createApplication(Long candidateId, CreateApplicationRequest req)
            throws ApplicationException {

        if (applicationRepository.existsByCandidateIdAndJobId(candidateId, req.getJobId())) {
            throw new ApplicationException("Application already exists");
        }

        // TODO: fetch job (companyId aur employerId job se aayenge)
        // TODO: fetch resume
        Long companyId = 1L;   // temporary
        Long employerId = 1L;  // temporary

        Application application = ApplicationMapper.toEntity(req, candidateId, companyId, employerId);
        Application saved = applicationRepository.save(application);

        return fullResponse(saved);
    }

    @Override
    public ApplicationResponse getApplicationById(Long id) throws ResourceNotFoundException {
        return fullResponse(getApplicationEntity(id));
    }

    @Override
    public List<ApplicationResponse> getMyApplications(Long candidateId) {
        return applicationRepository.findByCandidateId(candidateId)
                .stream()
                .map(this::fullResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ApplicationResponse> getApplicationsForJob(Long jobId) {
        return applicationRepository.findByJobId(jobId)
                .stream()
                .map(this::fullResponse)
                .collect(Collectors.toList());
    }

    // Interface mein ye overload bhi hai, isliye rakhna zaroori hai


    @Override
    public List<ApplicationResponse> getApplicationsForCompany(Long companyId,
                                                               CompanyApplicationFilterRequest filterRequest)
            throws ResourceNotFoundException {

        Sort sort = builderSort(filterRequest.getSortBy());

        return applicationRepository
                .findAll(
                        ApplicationSpecification.forCompanyWithFilters(
                                companyId,
                                filterRequest.getJobId(),
                                filterRequest.getStatus(),
                                filterRequest.getIsStarred(),
                                filterRequest.getAiShortlistStatus(),
                                filterRequest.getMinAiScore()),
                        sort)
                .stream()
                .map(this::fullResponse)
                .collect(Collectors.toList());
    }

    private Sort builderSort(String sortBy) {
        if ("AI_SCORE_DESC".equals(sortBy)) {
            return Sort.by(Sort.Order.desc("aiScore").with(Sort.NullHandling.NULLS_LAST));
        } else if ("AI_SCORE_ASC".equals(sortBy)) {
            return Sort.by(Sort.Order.asc("aiScore").with(Sort.NullHandling.NULLS_LAST));
        }
        return Sort.by(Sort.Direction.DESC, "appliedAt");
    }

    @Override
    public ApplicationResponse updateStatus(Long applicationId, Long employerId, ApplicationStatus newStatus)
            throws ResourceNotFoundException, ApplicationException {

        Application application = getApplicationEntity(applicationId);
        assertEmployer(application, employerId);

        if (application.getStatus() == ApplicationStatus.WITHDRAWN) {
            throw new ApplicationException("Cannot update status of a withdrawn application");
        }

        application.setStatus(newStatus);
        application = applicationRepository.save(application);

        return fullResponse(application);
    }

    @Override
    public ApplicationResponse withdraw(Long applicationId, Long candidateId, WithdrawApplicationRequest req)
            throws ResourceNotFoundException, ApplicationException {

        Application application = getApplicationEntity(applicationId);
        assertCandidate(application, candidateId);

        if (application.getStatus() == ApplicationStatus.WITHDRAWN) {
            throw new ApplicationException("Application is already withdrawn");
        }
        if (application.getStatus() == ApplicationStatus.HIRED) {
            throw new ApplicationException("Cannot withdraw an accepted offer");
        }

        application.setStatus(ApplicationStatus.WITHDRAWN);
        application.setWithdrawnAt(LocalDateTime.now());
        application.setWithdrawnReason(req.getReason());
        application = applicationRepository.save(application);

        return fullResponse(application);
    }

    @Override
    public ApplicationResponse markAsRead(Long applicationId, Long employerId)
            throws ResourceNotFoundException, ApplicationException {

        Application application = getApplicationEntity(applicationId);
        assertEmployer(application, employerId);

        // NOTE: apni entity ke field ke hisaab se setter ka naam check kar lena
        application.setIsRead(true);
        application = applicationRepository.save(application);

        return fullResponse(application);
    }

    @Override
    public ApplicationResponse toggleStar(Long applicationId, Long employerId)
            throws ResourceNotFoundException, ApplicationException {

        Application application = getApplicationEntity(applicationId);
        assertEmployer(application, employerId);

        boolean current = Boolean.TRUE.equals(application.getIsStarred());
        application.setIsStarred(!current);
        application = applicationRepository.save(application);

        return fullResponse(application);
    }

    @Override
    public void deleteApplication(Long applicationId, Long candidateId)
            throws ResourceNotFoundException, ApplicationException {

        Application application = getApplicationEntity(applicationId);
        assertCandidate(application, candidateId);

        applicationRepository.delete(application);
    }

    @Override
    public Application getApplicationEntity(Long id) throws ResourceNotFoundException {
        return applicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));
    }

    @Override
    public void markScreeningsStaleForJob(Long jobId) {
        // TODO: implement
    }

    private void assertEmployer(Application application, Long employerId) throws ApplicationException {
        if (!application.getEmployerId().equals(employerId)) {
            throw new ApplicationException("You are not the employer for this application");
        }
    }

    private void assertCandidate(Application application, Long candidateId) throws ApplicationException {
        if (!application.getCandidateId().equals(candidateId)) {
            throw new ApplicationException("You are not the owner of this application");
        }
    }

    public ApplicationResponse fullResponse(Application application) {
        JobResponse job = JobResponse.builder()
                .id(application.getJobId())
                .build();

        CompanyResponse companyResponse = CompanyResponse.builder()
                .id(application.getCompanyId())
                .build();

        UserResponse userResponse = UserResponse.builder()
                .id(application.getCandidateId())
                .build();

        return ApplicationMapper.toResponse(application, job, userResponse, companyResponse);
    }
}