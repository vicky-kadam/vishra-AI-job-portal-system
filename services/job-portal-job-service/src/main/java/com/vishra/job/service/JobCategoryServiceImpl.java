package com.vishra.job.service;

import com.vishra.job.dto.JobCategoryResponse;
import com.vishra.job.dto.response.ApiResponse;
import com.vishra.job.mapper.JobCategoryMapper;
import com.vishra.job.model.JobCategory;
import com.vishra.job.payload.JobCategoryRequest;
import com.vishra.job.repository.JobCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobCategoryServiceImpl implements JobCategoryService{
    private final JobCategoryMapper jobCategoryMapper;
    private final JobCategoryRepository jobCategoryRepository;
    @Override
    public JobCategoryResponse createJobCategory(JobCategoryRequest jobCategory) throws Exception {
        if(jobCategoryRepository.existsByName(jobCategory.getName())){
            throw new Exception("This Category name already exist");
        }

        JobCategory parent = null;
        if(jobCategory.getParentId() != null){
            parent = getCategoryEntityById(jobCategory.getParentId());
        }
        String slug = genrateUniqueSlug(jobCategory.getName());
        JobCategory jobCategory1 = JobCategory.builder()
                .name(jobCategory.getName())
                .slug(slug)
                .description(jobCategory.getDescription())
                .iconUrl(jobCategory.getIconUrl())
                .parent(parent)
                .active(jobCategory.getActive() != null ? jobCategory.getActive() : true)
                .build();

        JobCategory saved = jobCategoryRepository.save(jobCategory1);



        return jobCategoryMapper.toCategoryResponse(saved, true);
    }

    @Override
    public List<JobCategoryResponse> getAllJobCategories() {
    return jobCategoryRepository.findByActiveTrue().stream()
            .map(c -> jobCategoryMapper.toCategoryResponse(c,false))
            .collect(Collectors.toList());
    }

    @Override
    public JobCategoryResponse getJobCategoryById(Long id) throws Exception {

        return jobCategoryRepository.findById(id).map(c -> jobCategoryMapper.toCategoryResponse(c,true)).
                orElseThrow(() -> new Exception("Job categories is not found with this id"));
    }

    @Override
    public JobCategoryResponse updateJobCategory(Long id, JobCategoryRequest jobCategory) throws Exception {
        JobCategory category = getCategoryEntityById(id);

        if (!category.getName().equals(jobCategory.getName())
                && jobCategoryRepository.existsByName(jobCategory.getName())) {
            throw new Exception("This Category name already exist");
        }

        JobCategory parent = null;
        if (jobCategory.getParentId() != null) {
            if (jobCategory.getParentId().equals(id)) {
                throw new Exception("A category cannot be its own parent");
            }
            parent = getCategoryEntityById(jobCategory.getParentId());
        }

        category.setName(jobCategory.getName());
        category.setDescription(jobCategory.getDescription());
        category.setIconUrl(jobCategory.getIconUrl());
        category.setParent(parent);
        if (jobCategory.getActive() != null) {
            category.setActive(jobCategory.getActive());
        }

        JobCategory saved = jobCategoryRepository.save(category);
        return jobCategoryMapper.toCategoryResponse(saved, true);
    }
    @Override
    public void deleteJobCategory(Long id){
        JobCategory category = getCategoryEntityById(id);
        category.setActive(false);
        jobCategoryRepository.save(category);


    }

    @Override
    public JobCategory getCategoryEntityById(Long id) {
        return jobCategoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));

    }

    private String genrateUniqueSlug( String name) {
        String base = name.toLowerCase().replaceAll("[^a-z0-9\\s-]","").trim().replaceAll("[\\s-]+","-");
        if(!jobCategoryRepository.existsBySlug(base) ) {
            return base;
        }
        int counter = 1;
        while(jobCategoryRepository.existsBySlug(base+"-"+counter)){
            counter++;
        }
        return base+"-"+counter;

    }
}
