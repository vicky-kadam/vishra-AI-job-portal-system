package com.vishra.job.repository;

import com.vishra.job.dto.response.ResumeResponse;
import com.vishra.job.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume,Long> {
    List<Resume> findByCandidateIdAndIsActiveTrue(Long candidateId);
    Optional<Resume> findByCandidateIdAndIsDefaultTrue(Long resumeId);
}

