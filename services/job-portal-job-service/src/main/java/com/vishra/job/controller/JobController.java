//package com.vishra.job.controller;
//
//import com.vishra.job.dto.request.JobRequest;
//import com.vishra.job.dto.response.ApiResponse;
//import com.vishra.job.dto.response.JobResponse;
//import com.vishra.job.payload.JobSearchRequest;
//import com.vishra.job.service.JobService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/jobs")
//@RequiredArgsConstructor
//public class JobController {
//    private final JobService jobService;
//
//    @PostMapping
//    public ResponseEntity<JobResponse> createJob(@RequestHeader("X-User-Id") Long employerId, @RequestBody @Valid JobRequest jobRequest) throws Exception {
//        return ResponseEntity.ok(jobService.createJob(employerId,jobRequest));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<JobResponse> getJobById(@RequestHeader("X-User-Id") Long employerId, @PathVariable Long jobId) throws Exception {
//        return ResponseEntity.ok(jobService.getJobById(jobId));
//    }
//
//    @GetMapping
//    public ResponseEntity<List<JobResponse>> getJobs(@RequestHeader("X-User-Id") Long employerId, @ModelAttribute JobSearchRequest jobSearchRequest) {
//        return ResponseEntity.ok(jobService.getJobs(jobSearchRequest));
//    }
//
//    @GetMapping("/company/{companyId}")
//    public ResponseEntity<List<JobResponse>> getJobByCompany(@RequestHeader("X-User-Id") Long employerId, @PathVariable Long companyId) {
//        return ResponseEntity.ok(jobService.getJobByCompany(companyId));
//    }
//
//    @GetMapping("/Admin")
//    public ResponseEntity<List<JobResponse>> getAllJobsAdmin() {
//        return ResponseEntity.ok(jobService.getAllJobsAdmin());
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<JobResponse> updateJob(@RequestHeader("X-User-Id") Long employerId, @PathVariable Long jobId, @RequestBody @Valid JobRequest jobRequest) throws Exception {
//        return ResponseEntity.ok(jobService.updateJob(employerId, jobId, jobRequest));
//    }
//
//    @PatchMapping("/{id}/publish")
//    public ResponseEntity<JobResponse> publishJob(@RequestHeader("X-User-Id") Long employerId, @PathVariable Long jobId) throws Exception {
//        return ResponseEntity.ok(jobService.publicJobs(employerId, jobId));
//    }
//
//    @PatchMapping("/{id}/close")
//    public ResponseEntity<JobResponse> closeJob(@RequestHeader("X-User-Id") Long employerId, @PathVariable Long jobId) throws Exception {
//        return ResponseEntity.ok(jobService.closeJob(employerId, jobId));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<ApiResponse> deleteJob(@RequestHeader("X-User-Id") Long employerId, @PathVariable Long jobId) throws Exception {
//        jobService.deleteJob(jobId,employerId);
//        return ResponseEntity.ok(new ApiResponse("job delete successfully" , true));
//    }
//
//
//
//
//
//
//
//}
package com.vishra.job.controller;

import com.vishra.job.dto.request.JobRequest;
import com.vishra.job.dto.response.ApiResponse;
import com.vishra.job.dto.response.JobResponse;
import com.vishra.job.payload.JobSearchRequest;
import com.vishra.job.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping
    public ResponseEntity<JobResponse> createJob(
            @RequestHeader("X-User-Id") Long employerId,
            @RequestBody @Valid JobRequest jobRequest
    ) throws Exception {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(jobService.createJob(employerId, jobRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobResponse> getJobById(
            @PathVariable("id") Long jobId
    ) throws Exception {

        return ResponseEntity.ok(jobService.getJobById(jobId));
    }

    @GetMapping
    public ResponseEntity<List<JobResponse>> getJobs(
            @ModelAttribute JobSearchRequest jobSearchRequest
    ) {

        return ResponseEntity.ok(jobService.getJobs(jobSearchRequest));
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<JobResponse>> getJobByCompany(
            @PathVariable("companyId") Long companyId
    ) {

        return ResponseEntity.ok(jobService.getJobByCompany(companyId));
    }

    @GetMapping("/Admin")
    public ResponseEntity<List<JobResponse>> getAllJobsAdmin() {

        return ResponseEntity.ok(jobService.getAllJobsAdmin());
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobResponse> updateJob(
            @RequestHeader("X-User-Id") Long employerId,
            @PathVariable("id") Long jobId,
            @RequestBody @Valid JobRequest jobRequest
    ) throws Exception {

        return ResponseEntity.ok(
                jobService.updateJob(employerId, jobId, jobRequest)
        );
    }

    @PatchMapping("/{id}/publish")
    public ResponseEntity<JobResponse> publishJob(
            @RequestHeader("X-User-Id") Long employerId,
            @PathVariable("id") Long jobId
    ) throws Exception {

        return ResponseEntity.ok(
                jobService.publicJobs(jobId, employerId)
        );
    }

    @PatchMapping("/{id}/close")
    public ResponseEntity<JobResponse> closeJob(
            @RequestHeader("X-User-Id") Long employerId,
            @PathVariable("id") Long jobId
    ) throws Exception {

        return ResponseEntity.ok(
                jobService.closeJob(jobId, employerId)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteJob(
            @RequestHeader("X-User-Id") Long employerId,
            @PathVariable("id") Long jobId
    ) throws Exception {

        jobService.deleteJob(jobId, employerId);

        return ResponseEntity.ok(
                new ApiResponse("Job deleted successfully", true)
        );
    }


}
