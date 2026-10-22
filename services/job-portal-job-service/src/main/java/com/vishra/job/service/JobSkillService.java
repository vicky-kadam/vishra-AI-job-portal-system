package com.vishra.job.service;

import com.vishra.job.dto.JobSkillResponse;
import com.vishra.job.model.JobSkill;
import com.vishra.job.payload.JobSkillRequest;

import java.util.List;
import java.util.Set;

public interface JobSkillService {

    JobSkillResponse createJobSkill(JobSkillRequest jobSkill);
    List<JobSkillResponse> getAllJobSkills();
    JobSkillResponse getJobSkillById(Long id);
    JobSkillResponse updateJobSkill(Long id, JobSkillRequest jobSkill) throws Exception;
    void deleteJobSkill(Long id) throws Exception;
    Set<JobSkill> getJobSkillByIds(Set<Long> id);
}
