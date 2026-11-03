package com.vishra.job.mapper;

import com.vishra.job.dto.ApplicationResponse;
import com.vishra.job.dto.response.CompanyResponse;
import com.vishra.job.dto.response.JobResponse;
import com.vishra.job.dto.response.UserResponse;
import com.vishra.job.entity.Application;
import com.vishra.job.payload.CreateApplicationRequest;

public class ApplicationMapper {

    private ApplicationMapper() {
    }
    public static Application toEntity(CreateApplicationRequest req,
                                       Long candidateId,
                                       Long companyId,
                                       Long employerId) {
        return Application.builder()
                .candidateId(candidateId)
                .jobId(req.getJobId())
                .companyId(companyId)
                .employerId(employerId)
                .resumeId(req.getResumeId())
                .coverLetter(req.getCoverLetter())
                .expectedSalary(req.getExpectedSalary())
                .availableFrom(req.getAvailableFrom())
                .build();
    }

    public static ApplicationResponse toResponse(Application application
    , JobResponse job, UserResponse candidate, CompanyResponse company) {
        return ApplicationResponse.builder()
                .id(application.getId())
                .candidate(candidate)
                .employerId(application.getEmployerId())
                .job(job)
                .company(company)
                .status(application.getStatus())

                .resumeId(application.getResumeId())
                .coverLetter(application.getCoverLetter())

                .expectedSalary(application.getExpectedSalary())

                .availableFrom(application.getAvailableFrom())
                .isRead(application.getIsRead())
                .isStarred(application.getIsStarred())


//                .notes(toNoteResponseList(notes))
                .withdrawnAt(application.getWithdrawnAt())
                .withdrawnReason(application.getWithdrawnReason())
                .appliedAt(application.getAppliedAt())
                .updatedAt(application.getUpdatedAt())
//                .screening(toScreeningResponse(screening))
                .build();
    }
}