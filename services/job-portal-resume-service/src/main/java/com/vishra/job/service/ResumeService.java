package com.vishra.job.service;

import com.vishra.job.dto.PersonalInfoResponse;
import com.vishra.job.dto.response.ResumeResponse;
import com.vishra.job.model.PersonalInfo;
import com.vishra.job.model.Resume;
import com.vishra.job.payload.CreateResumeRequest;

import java.util.List;

public interface ResumeService {
    ResumeResponse createResume(Long candidateId, CreateResumeRequest createResumeRequest);
    ResumeResponse updateResume(Long candidateId, Long resumeId, CreateResumeRequest createResumeRequest);
    ResumeResponse getResumeById(Long candidateId, Long resumeId) throws Exception;
    void deleteResume(Long candidateId, Long resumeId) throws Exception;
    List<ResumeResponse> getMyResumes(Long candidateId);
    ResumeResponse updatePersonalInfo(Long candidateId, Long resumeId, PersonalInfoResponse personalInfo) throws Exception;
    ResumeResponse updateSummary(Long candidateId, Long resumeId, String summary) throws Exception;
    ResumeResponse setDefaultResume(Long candidateId, Long resumeId) throws Exception;
    Resume getResumeEntity(Long resumeId) throws Exception;
}
