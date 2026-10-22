package com.vishra.job.service;

import com.vishra.job.dto.response.JobTagResponse;
import com.vishra.job.mapper.JobTagMapper;
import com.vishra.job.model.JobTag;
import com.vishra.job.payload.JobTagRequest;
import com.vishra.job.repository.JobTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class JobTagServiceImpl implements JobTagService{
    private final JobTagMapper jobTagMapper;
    private final JobTagRepository jobTagRepository;
    @Override
    public JobTagResponse createJobTag(JobTagRequest jobTag) throws Exception {
        if (jobTagRepository.existsByName(jobTag.getName())){
            throw new Exception("Tag name already exist" + jobTag);
        }

        String slug  = generateUniqueSlug(jobTag.getName());
        JobTag tag = JobTag.builder()
                .name(jobTag.getName())
                .slug(slug)
                .build();


        JobTag saveTag = jobTagRepository.save(tag);
        return jobTagMapper.toJobTagResponse(saveTag);
    }



    @Override
    public List<JobTagResponse> getAllJobTags() {
        return jobTagRepository.findAll().stream().map(jobTagMapper::toJobTagResponse).toList();
    }

    @Override
    public JobTagResponse getJobTagById(Long id) {
        return jobTagMapper.toJobTagResponse(jobTagRepository.findById(id).orElseThrow(() -> new RuntimeException("Tag not found")));
    }

    @Override
    public JobTagResponse updateJobTag(Long id, JobTagRequest jobTag) throws Exception {

        JobTag tag = jobTagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found"));

        if (jobTagRepository.existsByName(jobTag.getName())
                && !tag.getName().equalsIgnoreCase(jobTag.getName())) {
            throw new Exception("Tag name already exists: " + jobTag.getName());
        }

        tag.setName(jobTag.getName());
        tag.setSlug(generateUniqueSlug(jobTag.getName()));

        JobTag updatedTag = jobTagRepository.save(tag);

        return jobTagMapper.toJobTagResponse(updatedTag);
    }
    @Override
    public void deleteJobTag(Long id) throws Exception {
        JobTag tag = getJobTagByIds(id);
        jobTagRepository.deleteById(id);


    }

    @Override
    public JobTag getJobTagByIds(Long id) throws Exception {
        return jobTagRepository.findById(id).orElseThrow(()-> new Exception("Job tag not found"));
    }

    @Override
    public Set<JobTag> getJobTagByIds(Set<Long> ids) throws Exception {
        List<JobTag> tags = jobTagRepository.findAllById(ids);
        return new HashSet<>(tags);
    }

    private String generateUniqueSlug( String name) {
        String base = name.toLowerCase().replaceAll("[^a-z0-9\\s-]","").trim().replaceAll("[\\s-]+","-");
        if(!jobTagRepository.existsBySlug(base) ) {
            return base;
        }
        int counter = 1;
        while (jobTagRepository.existsBySlug(base+"-"+counter)){
            counter++;
        }
        return base+"-"+counter;

    }
}
