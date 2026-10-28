package com.vishra.job.service;

import com.vishra.job.dto.EducationResponse;
import com.vishra.job.exception.ResourceNotFoundException;
import com.vishra.job.payload.AddEducationRequest;

import java.util.List;

public interface EducationService {

    EducationResponse addEducation(Long resumeId, Long candidateId, AddEducationRequest req)
            throws Exception;

    List<EducationResponse> getEducations(Long resumeId) throws Exception;

    EducationResponse updateEducation(Long educationId, Long resumeId, Long candidateId,
                                      AddEducationRequest req) throws ResourceNotFoundException;

    void deleteEducation(Long educationId, Long resumeId, Long candidateId)
            throws ResourceNotFoundException;
}

