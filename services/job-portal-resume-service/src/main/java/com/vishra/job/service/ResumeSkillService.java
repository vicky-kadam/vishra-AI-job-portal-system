package com.vishra.job.service;

import com.vishra.job.dto.ResumeSkillResponse;
import com.vishra.job.exception.ResourceNotFoundException;
import com.vishra.job.payload.AddResumeSkillRequest;

import java.util.List;

public interface ResumeSkillService {

    ResumeSkillResponse addSkill(Long resumeId, Long candidateId, AddResumeSkillRequest req)
            throws Exception;

    List<ResumeSkillResponse> getSkills(Long resumeId) throws Exception;

    ResumeSkillResponse updateSkill(Long skillId, Long resumeId, Long candidateId,
                                    AddResumeSkillRequest req) throws ResourceNotFoundException;

    void deleteSkill(Long skillId, Long resumeId, Long candidateId) throws ResourceNotFoundException;
}

