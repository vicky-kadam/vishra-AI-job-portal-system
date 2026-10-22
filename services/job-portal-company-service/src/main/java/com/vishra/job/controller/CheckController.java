package com.vishra.job.controller;

import com.vishra.job.domain.UserRole;
import com.vishra.job.dto.JobSkillResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CheckController {

    @GetMapping
    public String check(){
        return "This is out company service which is running successfully" + UserRole.ROLE_ADMIN;
    }

    @RestController
    @RequestMapping("/api/job-skill")
    @RequiredArgsConstructor
    public static class JobSkillController {

        private final JobSkillService jobSkillService;

        @PostMapping
        public ResponseEntity<JobSkillResponse> createJobSkill(@RequestBody @Valid JobSkillRequest jobSkillRequest) {
            JobSkillResponse jobSkillResponse = jobSkillService.createJobSkill(jobSkillRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(jobSkillResponse);

        }



    }
}
