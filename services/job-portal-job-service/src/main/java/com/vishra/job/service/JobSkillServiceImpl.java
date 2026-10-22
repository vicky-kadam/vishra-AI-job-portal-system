
package com.vishra.job.service;

import com.vishra.job.dto.JobSkillResponse;
import com.vishra.job.mapper.JobSkillMapper;
import com.vishra.job.model.JobSkill;
import com.vishra.job.payload.JobSkillRequest;
import com.vishra.job.repository.JobSkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobSkillServiceImpl implements JobSkillService {

    private final JobSkillMapper jobSkillMapper;
    private final JobSkillRepository jobSkillRepository;

    @Override
    public JobSkillResponse createJobSkill(JobSkillRequest request) {

        if (jobSkillRepository.existsByName(request.getName())) {
            throw new RuntimeException("Skill already exists");
        }

        String slug = generateUniqueSlug(request.getName());

        JobSkill jobSkill = JobSkill.builder()
                .name(request.getName())
                .slug(slug)
                .skillCategory(request.getSkillCategory())
                .active(true)
                .build();

        JobSkill savedSkill = jobSkillRepository.save(jobSkill);

        return jobSkillMapper.toJobSkillResponse(savedSkill);
    }

    @Override
    public List<JobSkillResponse> getAllJobSkills() {
        return jobSkillRepository.findByActiveTrue()
                .stream()
                .map(skill -> jobSkillMapper.toJobSkillResponse(skill))
                .collect(Collectors.toList());
    }

    @Override
    public JobSkillResponse getJobSkillById(Long id) {
        JobSkill skill = jobSkillRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Skill not found with id: " + id)
                );

        return jobSkillMapper.toJobSkillResponse(skill);
    }

    @Override
    public JobSkillResponse updateJobSkill(Long id, JobSkillRequest request)
            throws Exception {

        JobSkill skill = jobSkillRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Skill not found with id: " + id)
                );

        boolean nameChanged =
                !skill.getName().equalsIgnoreCase(request.getName());

        if (nameChanged && jobSkillRepository.existsByName(request.getName())) {
            throw new Exception("Skill already exists");
        }

        skill.setName(request.getName());
        skill.setSkillCategory(request.getSkillCategory());

        if (nameChanged) {
            skill.setSlug(generateUniqueSlug(request.getName()));
        }

        JobSkill updatedSkill = jobSkillRepository.save(skill);

        return jobSkillMapper.toJobSkillResponse(updatedSkill);
    }

    @Override
    @Transactional
    public void deleteJobSkill(Long id) throws Exception {

        JobSkill skill = jobSkillRepository.findById(id)
                .orElseThrow(() ->
                        new Exception("Job skill not found with id: " + id)
                );

        skill.setActive(false);
        jobSkillRepository.save(skill);
    }

    @Override
    public Set<JobSkill> getJobSkillByIds(Set<Long> ids) {

        if (ids == null || ids.isEmpty()) {
            return Set.of();
        }

        Set<JobSkill> skills =
                new HashSet<>(jobSkillRepository.findAllById(ids));

        if (skills.size() != ids.size()) {
            throw new RuntimeException(
                    "One or more skill IDs do not exist"
            );
        }

        return skills;
    }

    private String generateUniqueSlug(String name) {

        String base = name.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .trim()
                .replaceAll("[\\s-]+", "-");

        if (!jobSkillRepository.existsBySlug(base)) {
            return base;
        }

        int counter = 1;

        while (jobSkillRepository.existsBySlug(base + "-" + counter)) {
            counter++;
        }

        return base + "-" + counter;
    }
}

