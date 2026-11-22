package com.vishra.job.controller;

import com.vishra.job.dto.response.ApiResponse;
import com.vishra.job.dto.response.ApplicationNoteResponse;
import com.vishra.job.exception.ApplicationException;
import com.vishra.job.exception.ResourceNotFoundException;
import com.vishra.job.payload.ApplicationNoteRequest;
import com.vishra.job.service.ApplicationNoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/applications/{applicationId}/notes")
@RequiredArgsConstructor
public class ApplicationNoteController {
    private final ApplicationNoteService applicationNoteService;

    @PostMapping
    public ResponseEntity<ApplicationNoteResponse> addNote(@PathVariable Long applicationId, @RequestHeader("X-Use-Id") Long employerId, @RequestBody ApplicationNoteRequest request) throws ApplicationException, ResourceNotFoundException {
        ApplicationNoteResponse response = applicationNoteService.addNote(applicationId, employerId, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ApplicationNoteResponse>> getNotesApplication(@PathVariable Long applicationId, @RequestHeader("X-Use-Id") Long employerId) {
        return ResponseEntity.ok(applicationNoteService.getNotesApplication(applicationId, employerId));
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<ApiResponse<?>> deleteNote(@PathVariable Long applicationId, @PathVariable Long noteId, @RequestHeader("X-Use-Id") Long employerId) throws Exception {
        applicationNoteService.deleteNote(applicationId, noteId, employerId);
        return ResponseEntity.ok(new ApiResponse<>("ApplicationNote deleted successfully", true));
    }

}


