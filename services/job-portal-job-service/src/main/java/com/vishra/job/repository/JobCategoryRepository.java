package com.vishra.job.repository;

import com.vishra.job.model.JobCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobCategoryRepository extends JpaRepository<JobCategory, Long> {

    boolean existsBySlug(String slug);

    boolean existsByName(String name);

    JobCategory findByParentId(Long parentId);

    List<JobCategory> findByActiveTrue();


}
