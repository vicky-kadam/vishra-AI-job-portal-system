package com.vishra.job.repository;

import com.vishra.job.domain.AiShortlistStatus;
import com.vishra.job.domain.ApplicationStatus;
import com.vishra.job.entity.Application;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class ApplicationSpecification {

    public static Specification<Application> forCompanyWithFilters(
            Long companyId,
            Long jobId,
            ApplicationStatus status,
            Boolean isStarred,
            AiShortlistStatus aiShortlistStatus,
            Integer minAiScore
    ) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.equal(root.get("companyId"), companyId));

            if (jobId != null)             predicates.add(cb.equal(root.get("jobId"), jobId));
            if (status != null)            predicates.add(cb.equal(root.get("status"), status));
            if (isStarred != null)         predicates.add(cb.equal(root.get("isStarred"), isStarred));
            if (aiShortlistStatus != null) predicates.add(cb.equal(root.get("aiShortlistStatus"), aiShortlistStatus));
            if (minAiScore != null)        predicates.add(cb.greaterThanOrEqualTo(root.get("aiScore"), minAiScore));

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

