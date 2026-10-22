package com.vishra.job.controller;

import com.vishra.job.dto.JobSkillResponse;
import com.vishra.job.dto.response.ApiResponse;
import com.vishra.job.model.JobSkill;
import com.vishra.job.payload.JobSkillRequest;
import com.vishra.job.service.JobSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/job-skills")
@RequiredArgsConstructor
public class JobSkillController {
    private final JobSkillService jobSkillService;

    @PostMapping
    public ResponseEntity<JobSkillResponse> createJobSkill(@RequestBody JobSkillRequest jobSkill) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobSkillService.createJobSkill(jobSkill));
    }

    @GetMapping
    public ResponseEntity<List<JobSkillResponse>> getAllJobSkills() {
        return ResponseEntity.ok(jobSkillService.getAllJobSkills());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobSkillResponse> getJobSkillById(@PathVariable Long id) {
        return ResponseEntity.ok(jobSkillService.getJobSkillById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobSkillResponse> updateJobSkill(@PathVariable Long id, @RequestBody JobSkillRequest jobSkill) throws Exception {
        return ResponseEntity.ok(jobSkillService.updateJobSkill(id, jobSkill));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteJobSkill(@PathVariable Long id) throws Exception {
        jobSkillService.deleteJobSkill(id);
        return ResponseEntity.ok(new ApiResponse("skill deleted successfully",true));
    }

//    @GetMapping("/ids")
//    public ResponseEntity<Set<JobSkill>> getJobSkillsByIds(@RequestParam Set<Long> ids) {
//        return ResponseEntity.ok(jobSkillService.getJobSkillByIds(ids));
//    }
}
