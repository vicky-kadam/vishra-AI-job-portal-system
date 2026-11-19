package com.vishra.job.controller;
import com.vishra.job.dto.response.ApiResponse;
import com.vishra.job.payload.AiTextResponse;
import com.vishra.job.payload.request.*;
import com.vishra.job.payload.response.CareerFeedbackResponse;
import com.vishra.job.payload.response.ResumeImprovementResponse;
import com.vishra.job.payload.response.ResumeParseResponse;
import com.vishra.job.payload.response.WorkExperienceBulletsResponse;
import com.vishra.job.service.ResumeAiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai/resume")
@RequiredArgsConstructor
public class ResumeAiController {

    private final ResumeAiService resumeAiService;


    @PostMapping("/summary")
    public ResponseEntity<ApiResponse<AiTextResponse>> generateSummary(
            @RequestBody ResumeSummaryRequest request) {
        AiTextResponse response = resumeAiService.generateProfessionalSummary(request);
        return ResponseEntity.ok(ApiResponse.success("Professional summary generated", response));
    }


    @PostMapping("/experience-bullets")
    public ResponseEntity<ApiResponse<WorkExperienceBulletsResponse>> generateBullets(
            @Valid @RequestBody WorkExperienceBulletRequest request) {
        WorkExperienceBulletsResponse response = resumeAiService.generateWorkExperienceBullets(request);
        return ResponseEntity.ok(ApiResponse.success("Bullet points generated", response));
    }


    @PostMapping("/parse")
    public ResponseEntity<ApiResponse<ResumeParseResponse>> parseResume(
            @Valid @RequestBody ResumeParseRequest request) {
        ResumeParseResponse response = resumeAiService.parseResume(request);
        return ResponseEntity.ok(ApiResponse.success("Resume parsed successfully", response));
    }


    @PostMapping("/improvements")
    public ResponseEntity<ApiResponse<ResumeImprovementResponse>> getImprovements(
            @Valid @RequestBody ResumeImprovementRequest request) {
        ResumeImprovementResponse response = resumeAiService.getResumeImprovementTips(request);
        return ResponseEntity.ok(ApiResponse.success("Resume improvements analyzed", response));
    }


    @PostMapping("/career-feedback")
    public ResponseEntity<ApiResponse<CareerFeedbackResponse>> getCareerFeedback(
            @Valid @RequestBody CareerFeedbackRequest request) {
        CareerFeedbackResponse response = resumeAiService
                .getCareerFeedback(request);
        return ResponseEntity.ok(ApiResponse.success("Career feedback generated", response));
    }
}

