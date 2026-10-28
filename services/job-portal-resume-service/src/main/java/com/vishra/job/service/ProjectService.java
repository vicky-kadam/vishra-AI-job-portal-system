package com.vishra.job.service;
import com.vishra.job.dto.ProjectResponse;
import com.vishra.job.exception.ResourceNotFoundException;
import com.vishra.job.payload.AddProjectRequest;

import java.util.List;

public interface ProjectService {

    ProjectResponse addProject(Long resumeId, Long candidateId, AddProjectRequest req)
            throws Exception;

    List<ProjectResponse> getProjects(Long resumeId) throws Exception;

    ProjectResponse updateProject(Long projectId, Long resumeId, Long candidateId,
                                  AddProjectRequest req) throws ResourceNotFoundException;

    void deleteProject(Long projectId, Long resumeId, Long candidateId)
            throws ResourceNotFoundException;
}
