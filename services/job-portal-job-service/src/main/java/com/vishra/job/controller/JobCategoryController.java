package com.vishra.job.controller;

import com.vishra.job.dto.JobCategoryResponse;
import com.vishra.job.dto.response.ApiResponse;
import com.vishra.job.payload.JobCategoryRequest;
import com.vishra.job.service.JobCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-categories")
@RequiredArgsConstructor
public class JobCategoryController {
    private final JobCategoryService jobCategoryService;

    @PostMapping
    public ResponseEntity<JobCategoryResponse> createJobCategory(@RequestBody @Valid JobCategoryRequest jobCategory) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobCategoryService.createJobCategory(jobCategory));
    }

    @GetMapping
    public ResponseEntity<List<JobCategoryResponse>> getAllJobCategories() {
        return ResponseEntity.ok(jobCategoryService.getAllJobCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobCategoryResponse> getJobCategoryById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(jobCategoryService.getJobCategoryById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobCategoryResponse> updateJobCategory(@PathVariable Long id, @RequestBody @Valid JobCategoryRequest jobCategory) throws Exception {
        return ResponseEntity.ok(jobCategoryService.updateJobCategory(id, jobCategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteJobCategory(@PathVariable Long id) {
        jobCategoryService.deleteJobCategory(id);
        return ResponseEntity.ok(new ApiResponse("category deleted successfully",true));
    }






}
