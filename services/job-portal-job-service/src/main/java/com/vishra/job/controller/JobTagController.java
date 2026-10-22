package com.vishra.job.controller;

import com.vishra.job.dto.response.ApiResponse;
import com.vishra.job.dto.response.JobTagResponse;
import com.vishra.job.payload.JobTagRequest;
import com.vishra.job.service.JobTagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
@RequestMapping("/api/job-tags")
@RequiredArgsConstructor
public class JobTagController {
    private final JobTagService jobTagService;

    @PostMapping
    public ResponseEntity<JobTagResponse> createJobTag(@RequestBody @Valid JobTagRequest jobTag) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobTagService.createJobTag(jobTag));
    }

    @GetMapping
    public ResponseEntity<List<JobTagResponse>> getAllJobTags() {
        return ResponseEntity.ok(jobTagService.getAllJobTags());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobTagResponse> getJobTagById(@PathVariable Long id) {
        return ResponseEntity.ok(jobTagService.getJobTagById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobTagResponse> updateJobTag(@PathVariable Long id, @RequestBody @Valid JobTagRequest jobTag) throws Exception {
        return ResponseEntity.ok(jobTagService.updateJobTag(id, jobTag));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteJobTag(@PathVariable Long id) throws Exception {
        jobTagService.deleteJobTag(id);
        return ResponseEntity.ok(ApiResponse.builder().message("Job tag deleted successfully").build());
    }







}
