package com.vishra.job.service;

import com.vishra.job.dto.WorkExperienceResponse;
import com.vishra.job.exception.ResourceNotFoundException;
import com.vishra.job.payload.AddWorkExperienceRequest;


import java.util.List;

public interface WorkExperienceService {

    WorkExperienceResponse addWorkExperience(Long resumeId,
                                             Long candidateId,
                                             AddWorkExperienceRequest req)
            throws Exception;

    List<WorkExperienceResponse> getWorkExperiences(Long resumeId) throws Exception;

    WorkExperienceResponse updateWorkExperience(
            Long experienceId, Long resumeId, Long candidateId,
            AddWorkExperienceRequest req) throws ResourceNotFoundException;

    void deleteWorkExperience(Long experienceId, Long resumeId,
                              Long candidateId)
            throws ResourceNotFoundException;
}
