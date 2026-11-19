package com.vishra.job.controller;


import com.vishra.job.dto.response.ApiResponse;
import com.vishra.job.payload.AiTextResponse;
import com.vishra.job.payload.request.CompanyDescriptionRequest;
import com.vishra.job.payload.request.CompanyTaglineRequest;
import com.vishra.job.payload.response.CompanyTaglineResponse;
import com.vishra.job.service.CompanyAiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai/company")
@RequiredArgsConstructor
public class AiCompanyController {

    private final CompanyAiService companyAiService;


    @PostMapping("/describe")
    public ResponseEntity<ApiResponse<AiTextResponse>> generateCompanyDescription(
            @Valid @RequestBody CompanyDescriptionRequest request) {
        AiTextResponse response = companyAiService.generateCompanyDescription(request);
        return ResponseEntity.ok(ApiResponse.success("Company description generated", response));
    }
    @PostMapping("/taglines")
    public ResponseEntity<ApiResponse<CompanyTaglineResponse>> generateTaglines(
            @Valid @RequestBody CompanyTaglineRequest request) {
        com.vishra.job.payload.response.CompanyTaglineResponse response = companyAiService.generateCompanyTaglines(request);
        return ResponseEntity.ok(ApiResponse.success("Taglines generated", response));
    }
}

