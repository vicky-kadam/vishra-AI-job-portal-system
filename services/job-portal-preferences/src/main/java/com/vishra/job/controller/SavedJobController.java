package com.vishra.job.controller;

import com.vishra.job.dto.SavedJobResponse;
import com.vishra.job.dto.response.ApiResponse;
import com.vishra.job.exception.ApplicationException;
import com.vishra.job.exception.ResourceNotFoundException;
import com.vishra.job.payload.SavedJobRequest;
import com.vishra.job.service.SavedJobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/preferences/saved-jobs")
@RequiredArgsConstructor
public class SavedJobController {

    private final SavedJobService savedJobService;

    @PostMapping
    public ResponseEntity<SavedJobResponse> saveJob(
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody SavedJobRequest req) throws ApplicationException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedJobService.saveJob(candidateId, req));
    }

    @GetMapping
    public ResponseEntity<List<SavedJobResponse>> getMySavedJobs(
            @RequestHeader("X-User-Id") Long candidateId) {
        return ResponseEntity.ok(savedJobService.getMySavedJobs(candidateId));
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> isSaved(
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestParam Long jobId) {
        return ResponseEntity.ok(savedJobService.isSaved(candidateId, jobId));
    }

    @DeleteMapping("/{savedJobId}")
    public ResponseEntity<ApiResponse<A>> unsaveJob(
            @PathVariable Long savedJobId,
            @RequestHeader("X-User-Id") Long candidateId) throws ResourceNotFoundException {
        savedJobService.unsaveJob(candidateId, savedJobId);
        return ResponseEntity.ok(new ApiResponse<A>("Job removed from saved list", true));
    }
}
