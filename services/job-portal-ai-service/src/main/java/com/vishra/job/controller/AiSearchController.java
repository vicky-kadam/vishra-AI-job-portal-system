package com.vishra.job.controller;

import com.vishra.job.dto.response.ApiResponse;
import com.vishra.job.payload.request.JobAlertSuggestRequest;
import com.vishra.job.payload.request.JobMatchRequest;
import com.vishra.job.payload.request.SearchEnhanceRequest;
import com.vishra.job.payload.response.JobAlertSuggestResponse;
import com.vishra.job.payload.response.JobMatchResponse;
import com.vishra.job.payload.response.SearchEnhanceResponse;
import com.vishra.job.service.SearchAiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai/search")
@RequiredArgsConstructor
public class AiSearchController {

    private final SearchAiService searchAiService;


    @PostMapping("/enhance")
    public ResponseEntity<ApiResponse<SearchEnhanceResponse>> enhanceSearch(
            @Valid @RequestBody SearchEnhanceRequest request) {
        SearchEnhanceResponse response = searchAiService.enhanceSearch(request);
        return ResponseEntity.ok(ApiResponse.success("Search enhanced", response));
    }


    @PostMapping("/job-match")
    public ResponseEntity<ApiResponse<JobMatchResponse>> calculateJobMatch(
            @RequestBody JobMatchRequest request) {
        JobMatchResponse response = searchAiService.calculateJobMatch(request);
        return ResponseEntity.ok(ApiResponse.success("Job match calculated", response));
    }

    @PostMapping("/alert-suggestion")
    public ResponseEntity<ApiResponse<JobAlertSuggestResponse>> suggestAlertCriteria(
            @RequestBody JobAlertSuggestRequest request) {
        JobAlertSuggestResponse response = searchAiService.suggestJobAlertCriteria(request);
        return ResponseEntity.ok(ApiResponse.success("Alert criteria suggested", response));
    }
}
