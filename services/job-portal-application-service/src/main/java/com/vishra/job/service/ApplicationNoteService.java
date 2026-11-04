package com.vishra.job.service;


import com.vishra.job.dto.response.ApplicationNoteResponse;
import com.vishra.job.exception.ApplicationException;
import com.vishra.job.exception.ResourceNotFoundException;
import com.vishra.job.payload.ApplicationNoteRequest;

import java.util.List;

public interface ApplicationNoteService {
    ApplicationNoteResponse addNote(Long applicationId, Long employerId, ApplicationNoteRequest request) throws ResourceNotFoundException, ApplicationException;

    List<ApplicationNoteResponse> getNotesApplication(Long applicationId, Long employerId);
    void deleteNote(Long applicationOId,Long noteId, Long employerId) throws Exception;
}
