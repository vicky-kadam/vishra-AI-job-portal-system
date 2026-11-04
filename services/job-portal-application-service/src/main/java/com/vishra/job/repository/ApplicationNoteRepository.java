package com.vishra.job.repository;

import com.vishra.job.entity.Application;
import com.vishra.job.entity.ApplicationNotes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationNoteRepository extends JpaRepository<ApplicationNotes,Long> {
    List<ApplicationNotes> findByApplicationId(Long applicationId);
}
