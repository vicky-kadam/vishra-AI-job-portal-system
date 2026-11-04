package com.vishra.job.service.impl;

import com.vishra.job.dto.response.ApplicationNoteResponse;
import com.vishra.job.entity.Application;
import com.vishra.job.entity.ApplicationNotes;
import com.vishra.job.exception.ApplicationException;
import com.vishra.job.exception.ResourceNotFoundException;
import com.vishra.job.mapper.ApplicationMapper;
import com.vishra.job.payload.ApplicationNoteRequest;
import com.vishra.job.repository.ApplicationNoteRepository;
import com.vishra.job.service.ApplicationNoteService;
import com.vishra.job.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationNoteServiceImpl implements ApplicationNoteService {
    private final ApplicationService applicationService;
    private final ApplicationNoteRepository applicationNoteRepository;
    private final ApplicationMapper applicationMapper;
    @Override
    public ApplicationNoteResponse addNote(Long applicationId, Long employerId, ApplicationNoteRequest request) throws ResourceNotFoundException, ApplicationException {
        Application application = applicationService.getApplicationEntity(applicationId);
        assertEmployer(application,employerId);
        ApplicationNotes applicationNotes = ApplicationNotes.builder()
                .application(application)
                .addedByUserId(employerId)
                .content(request.getContent())
                .build();

        ApplicationNotes savedApplicationNote = applicationNoteRepository.save(applicationNotes);


        return applicationMapper.toNoteResponse(savedApplicationNote);
    }

    @Override
    public List<ApplicationNoteResponse> getNotesApplication(Long applicationId, Long employerId) {
        return applicationNoteRepository.findByApplicationId(applicationId)
                .stream()
                .map(applicationMapper::toNoteResponse)
                .toList();
    }

    @Override
    public void deleteNote(Long applicationOId, Long noteId, Long employerId) throws Exception {
        Application application = applicationService.getApplicationEntity(applicationOId);
        assertEmployer(application,employerId);
        ApplicationNotes applicationNote = applicationNoteRepository.findById(noteId).orElseThrow(() -> new Exception("not doest not belong to application"));
        applicationNoteRepository.deleteById(noteId);

    }

    private void assertEmployer(Application application, Long employerId) throws ApplicationException {
        if (!application.getEmployerId().equals(employerId)) {
            throw new ApplicationException("You are not the employer for this application");
        }
    }
}
