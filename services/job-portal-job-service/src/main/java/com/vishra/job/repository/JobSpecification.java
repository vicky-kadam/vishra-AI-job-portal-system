package com.vishra.job.repository;

import com.vishra.job.domain.JobStatus;
import com.vishra.job.model.Job;
import com.vishra.job.payload.JobSearchRequest;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class JobSpecification {
    public static Specification<Job> build(JobSearchRequest request){
        return (root, query,cb) -> {
            List<Predicate>predicates = new ArrayList<>();
            predicates.add(cb.isTrue(root.get("active")));
            JobStatus status = request.getJobStatus() != null ? request.getJobStatus() : JobStatus.OPEN;
            predicates.add(cb.equal(root.get("jobStatus"),status));

            if (request.getJobType() != null){
                predicates.add(cb.equal(root.get("jobType"),request.getJobType()));

            }
            if (request.getWorkMode() != null){
                predicates.add(cb.equal(root.get("workMode"),request.getWorkMode()));
            }
            if (request.getExperienceLevel() != null){
                predicates.add(cb.equal(root.get("experienceLevel"),request.getExperienceLevel()));
            }
            if (request.getCompanyId() != null){
                predicates.add(cb.equal(root.get("companyId"),request.getCompanyId()));
            }
            if (request.getCategoryId() != null){
                predicates.add(cb.equal(root.get("categoryId"),request.getCategoryId()));
            }
            if (request.getLocation() != null && !request.getLocation().isEmpty()) {
                String pattern  = "%" + request.getLocation().toLowerCase() + "%";
                Path<String> city=root.get("location").get("city");
                Path<String> state=root.get("location").get("state");
                Path<String> country=root.get("location").get("country");
                Path<String> address=root.get("location").get("address");
                predicates.add(cb.or(
                        cb.like(cb.lower(city),pattern),
                        cb.like(cb.lower(state),pattern),
                        cb.like(cb.lower(address),pattern)
                ));

            }
            if(request.getMinSalary() != null){
                predicates.add(cb.greaterThanOrEqualTo(root.get("salaryRange").get("minSalary"),request.getMinSalary()));
            }
            if(request.getMaxSalary() != null){
                predicates.add(cb.lessThanOrEqualTo(root.get("salaryRange").get("maxSalary"),request.getMaxSalary()));
            }
            if(request.getMinOpenings() != null){
                predicates.add(cb.greaterThanOrEqualTo(root.get("openings"),request.getMinOpenings()));
            }
            if (request.getMaxOpenings() != null){
                predicates.add(cb.lessThanOrEqualTo(root.get("openings"),request.getMaxOpenings()));
            }
            if (request.getSkillIds() != null && !request.getSkillIds().isEmpty()){
                predicates.add(root.join("skills").get("id").in(request.getSkillIds()));

            }
            return cb.and(predicates.toArray(new Predicate[0]));


        };


    }
}
