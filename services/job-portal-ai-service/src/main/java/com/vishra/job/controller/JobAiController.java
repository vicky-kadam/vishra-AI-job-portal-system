package com.vishra.job.controller;

import com.vishra.job.dto.response.ApiResponse;
import com.vishra.job.payload.AiTextResponse;
import com.vishra.job.payload.request.JobDescriptionRequest;
import com.vishra.job.payload.request.SalaryRangeRequest;
import com.vishra.job.payload.response.SalaryRangeResponse;
import com.vishra.job.service.JobAiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai/job")
@RequiredArgsConstructor
public class JobAiController {

    private final JobAiService jobAiService;


    @PostMapping("/describe")
    public ResponseEntity<ApiResponse<AiTextResponse>>
    generateJobDescription(
            @Valid @RequestBody JobDescriptionRequest request) {
        AiTextResponse response = jobAiService.generateJobDescription(request);
        return ResponseEntity.ok(ApiResponse.success("Job description generated", response));
    }


    @GetMapping("/requirements")
    public ResponseEntity<ApiResponse<AiTextResponse>> generateJobRequirements(
            @RequestParam String title,
            @RequestParam(required = false) String category) {
        AiTextResponse response = jobAiService.generateJobRequirements(title, category);
        return ResponseEntity.ok(ApiResponse.success("Requirements generated", response));
    }


    @PostMapping("/salary-suggestion")
    public ResponseEntity<ApiResponse<SalaryRangeResponse>> suggestSalary(
            @Valid @RequestBody SalaryRangeRequest request) {
        SalaryRangeResponse response = jobAiService.suggestSalaryRange(request);
        return ResponseEntity.ok(ApiResponse.success("Salary range suggested", response));
    }


    @GetMapping("/skills-recommendation")
    public ResponseEntity<ApiResponse<AiTextResponse>> recommendSkills(
            @RequestParam String title,
            @RequestParam(required = false) String description) {
        AiTextResponse response = jobAiService
                .recommendSkillsForJob(title, description);
        return ResponseEntity.ok(ApiResponse.success("Skills recommended", response));
    }


    @GetMapping("/responsibilities")
    public ResponseEntity<ApiResponse<AiTextResponse>> generateResponsibilities(
            @RequestParam String title,
            @RequestParam(required = false) String category) {
        AiTextResponse response = jobAiService.generateJobResponsibilities(title, category);
        return ResponseEntity.ok(ApiResponse.success("Responsibilities generated", response));
    }


    @GetMapping("/benefits")
    public ResponseEntity<ApiResponse<AiTextResponse>> generateBenefits(
            @RequestParam String title,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String jobType) {
        AiTextResponse response = jobAiService.generateJobBenefits(title, category, jobType);
        return ResponseEntity.ok(ApiResponse.success("Benefits generated", response));
    }

    @GetMapping("/tags-recommendation")
    public ResponseEntity<ApiResponse<AiTextResponse>> recommendTags(
            @RequestParam String title,
            @RequestParam(required = false) String description) {
        AiTextResponse response = jobAiService.recommendTagsForJob(title, description);
        return ResponseEntity.ok(ApiResponse.success("Tags recommended", response));
    }

}
