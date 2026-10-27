package com.vishra.job.service.impl;

import com.vishra.job.dto.PersonalInfoResponse;
import com.vishra.job.dto.response.ResumeResponse;
import com.vishra.job.mapper.ResumeMapper;
import com.vishra.job.model.PersonalInfo;
import com.vishra.job.model.Resume;
import com.vishra.job.payload.CreateResumeRequest;
import com.vishra.job.repository.ResumeRepository;
import com.vishra.job.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private ResumeMapper resumeMapper;
    private final ResumeRepository resumeRepository;
    @Override
    public ResumeResponse createResume(Long candidateId, CreateResumeRequest createResumeRequest) {
        if(Boolean.TRUE.equals(createResumeRequest.getIsDefault())){
            resumeRepository.findByCandidateIdAndIsDefaultTrue(candidateId)
                    .ifPresent(existing -> existing.setIsDefault(false));
        }
        Resume resume = Resume.builder()
                .candidateId(candidateId)
                .title(createResumeRequest.getTitle())
                .resumeTemplate(createResumeRequest.getResumeTemplate())
                .resumeVisibility(createResumeRequest.getResumeVisibility())
                .isDefault(createResumeRequest.getIsDefault())
                .isActive(true)
                .build();
        Resume save = resumeRepository.save(resume);

        return null;
    }

    @Override
    public ResumeResponse updateResume(Long candidateId, Long resumeId, CreateResumeRequest createResumeRequest) {

        return null;
    }

    @Override
    public ResumeResponse getResumeById(Long candidateId, Long resumeId) throws Exception {
        Resume resume = getResumeEntity(resumeId);
        assertOwner(resume, candidateId);
        return buildFullResponse(resume);
    }



    @Override
    public void deleteResume(Long candidateId, Long resumeId) throws Exception {
        Resume resume = getResumeEntity(resumeId);
        assertOwner(resume, candidateId);
        resume.setIsActive(false);
        resume.setIsDefault(false);
        resumeRepository.save(resume);

    }

    @Override
    public List<ResumeResponse> getMyResumes(Long candidateId) {

        return resumeRepository.findByCandidateIdAndIsActiveTrue(candidateId).stream()
                .map(this::buildFullResponse)
                .toList();
    }

    @Override
    public ResumeResponse updatePersonalInfo(Long candidateId, Long resumeId, PersonalInfoResponse personalInfo) throws Exception {
        Resume resume = getResumeEntity(resumeId);
        assertOwner(resume,candidateId);
        PersonalInfo info = resume.getPersonalInfo();
        if(info.getFirstName()!= null)info.setFirstName(personalInfo.getFirstName());
        if (info.getLastName() != null)info.setLastName(personalInfo.getLastName());
        if (info.getHeadLine() != null)info.setHeadLine(personalInfo.getHeadLine());
        if (info.getCity() != null)info.setCity(personalInfo.getCity());
        if (info.getCountry() != null)info.setCountry(personalInfo.getCountry());
        if(info.getEmail() != null)info.setEmail(personalInfo.getEmail());
        if (info.getPhone() != null)info.setPhone(personalInfo.getPhone());
        if (info.getGithubUrl() != null)info.setGithubUrl(personalInfo.getGithubUrl());
        if (info.getPortfolioUrl() != null)info.setPortfolioUrl(personalInfo.getPortfolioUrl());
        if (info.getLinkedInUrl() != null)info.setLinkedInUrl(personalInfo.getLinkedInUrl());
        if (info.getWebsiteUrl() != null)info.setWebsiteUrl(personalInfo.getWebsiteUrl());

        Resume updatedResume = resumeRepository.save(resume);

        return buildFullResponse(updatedResume);
    }

    @Override
    public ResumeResponse updateSummary(Long candidateId, Long resumeId, String summary) throws Exception {
        Resume resume = getResumeEntity(resumeId);
        assertOwner(resume,candidateId);

        resume.setSummary(summary);
        Resume updated = resumeRepository.save(resume);

        return buildFullResponse(updated);
    }

    @Override
    public ResumeResponse setDefaultResume(Long candidateId, Long resumeId) throws Exception {
        Resume resume = getResumeEntity(resumeId);
        assertOwner(resume,candidateId);
        resumeRepository.findByCandidateIdAndIsDefaultTrue(candidateId)
                .ifPresent(existing ->{
                            existing.setIsDefault(false);
                    resumeRepository.save(existing);

                        }
                        );
        ;

        resume.setIsDefault(true);
        return null;
    }

    @Override
    public Resume getResumeEntity(Long resumeId) throws Exception {
        return resumeRepository.findById(resumeId).orElseThrow(() -> new Exception("Resume not found"));
    }

    private ResumeResponse buildFullResponse(Resume resume){
        return resumeMapper.toResumeResponse(resume);
    }

    private void assertOwner(Resume resume, Long candidateId) throws Exception {
        if (!resume.getCandidateId().equals(candidateId)) {
            throw new Exception("Resume not found with this id");
        }

    }
}
