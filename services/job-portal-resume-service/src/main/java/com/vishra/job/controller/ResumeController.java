package com.vishra.job.controller;

import com.vishra.job.dto.PersonalInfoResponse;
import com.vishra.job.dto.response.ResumeResponse;
import com.vishra.job.payload.CreateResumeRequest;
import com.vishra.job.service.ResumeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;

    @PostMapping
    public ResponseEntity<ResumeResponse> createResume(@RequestHeader("X-User-Id")Long candidateId, @RequestBody @Valid CreateResumeRequest createResumeRequest){
        ResumeResponse resumeResponse = resumeService.createResume(candidateId,createResumeRequest);
        return ResponseEntity.ok(resumeResponse);
    }

    @GetMapping("/{resumeId}")
    public ResponseEntity<ResumeResponse> getResumeById(@PathVariable Long resumeId,@RequestHeader("X-User-Id")Long candidateId) throws Exception {
        ResumeResponse resumeResponse = resumeService.getResumeById(resumeId,candidateId );
        return ResponseEntity.ok(resumeResponse);
    }

//    @PutMapping("/{resumeId}")
//    public ResponseEntity<ResumeResponse> updateResume(@PathVariable Long resumeId, @RequestBody @Valid CreateResumeRequest createResumeRequest){
//        ResumeResponse resumeResponse = resumeService.updateResume(resumeId,createResumeRequest);
//
//
//      return ResponseEntity.ok(resumeResponse);

    @GetMapping("/my")
    public ResponseEntity<List<ResumeResponse>> getMyResume(@RequestHeader("X-User-Id")Long candidateId){
        return ResponseEntity.ok(resumeService.getMyResumes(candidateId));
    }

    @DeleteMapping("/{resumeId}")
    public ResponseEntity<Void> deleteResume(@PathVariable Long resumeId, @RequestHeader("X-User-Id")Long candidateId) throws Exception {
        resumeService.deleteResume(candidateId, resumeId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{resumeId}/personal-info")
    public ResponseEntity<ResumeResponse> updatePersonalInfo(@PathVariable Long resumeId, @RequestHeader("X-User-Id")Long candidateId, @RequestBody @Valid PersonalInfoResponse personalInfo) throws Exception {
        ResumeResponse resumeResponse = resumeService.updatePersonalInfo(candidateId, resumeId, personalInfo);
        return ResponseEntity.ok(resumeResponse);
    }

    @PutMapping("/{resumeId}/set-summary")
    public ResponseEntity<ResumeResponse> updateSummary(@PathVariable Long resumeId, @RequestHeader("X-User-Id")Long candidateId, @RequestBody @Valid String summary) throws Exception {
        ResumeResponse resumeResponse = resumeService.updateSummary(candidateId, resumeId, summary);
        return ResponseEntity.ok(resumeResponse);
    }

    @PutMapping("/{resumeId}/set-default")
    public ResponseEntity<ResumeResponse> setDefaultResume(@PathVariable Long resumeId, @RequestHeader("X-User-Id")Long candidateId) throws Exception {
        ResumeResponse resumeResponse = resumeService.setDefaultResume(candidateId, resumeId);
        return ResponseEntity.ok(resumeResponse);
    }


}
