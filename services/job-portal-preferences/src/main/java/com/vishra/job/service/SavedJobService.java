package com.vishra.job.service;

import com.vishra.job.dto.SavedJobResponse;
import com.vishra.job.exception.ApplicationException;
import com.vishra.job.exception.ResourceNotFoundException;
import com.vishra.job.payload.SavedJobRequest;

import java.util.List;

public interface SavedJobService {

    SavedJobResponse saveJob(Long candidateId, SavedJobRequest req) throws ApplicationException;

    void unsaveJob(Long candidateId, Long savedJobId) throws ResourceNotFoundException;

    List<SavedJobResponse> getMySavedJobs(Long candidateId);

    boolean isSaved(Long candidateId, Long jobId);
}