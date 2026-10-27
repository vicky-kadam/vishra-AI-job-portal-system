package com.vishra.job.mapper;

import com.vishra.job.dto.PersonalInfoResponse;
import com.vishra.job.dto.ResumeSkillResponse;
import com.vishra.job.dto.WorkExperienceResponse;
import com.vishra.job.dto.response.ResumeResponse;
import com.vishra.job.model.PersonalInfo;
import com.vishra.job.model.Resume;
import com.vishra.job.model.ResumeSkill;
import com.vishra.job.model.WorkExperience;
import org.springframework.stereotype.Component;

@Component
public class ResumeMapper {

    public static PersonalInfoResponse personalInfoResponse(PersonalInfo personalInfo){
        return PersonalInfoResponse.builder()
                .firstName(personalInfo.getFirstName())
                .lastName(personalInfo.getLastName())
                .headLine(personalInfo.getHeadLine())
                .email(personalInfo.getEmail())
                .country(personalInfo.getCountry())
                .city(personalInfo.getCity())
                .phone(personalInfo.getPhone())
                .linkedInUrl(personalInfo.getLinkedInUrl())
                .githubUrl(personalInfo.getGithubUrl())
                .portfolioUrl(personalInfo.getPortfolioUrl())
                .websiteUrl(personalInfo.getWebsiteUrl())
                .build();



    }

    public ResumeResponse toResumeResponse(Resume resume){
        if (resume == null){
            return null;
        }
        ResumeResponse response = ResumeResponse.builder()
                .id(resume.getId())
                .candidateId(resume.getCandidateId())
                .completionScore(resume.getCompletionScore())
                .createdAt(resume.getCreatedAt())
                .updatedAt(resume.getUpdatedAt())
                .isDefault(resume.getIsDefault())
                .resumeTemplate(resume.getResumeTemplate())
                .resumeVisibility(resume.getResumeVisibility())
                .summary(resume.getSummary())
                .personalInfo(ResumeMapper.personalInfoResponse(resume.getPersonalInfo()))
                .build();

        return response;
    }

    public static WorkExperienceResponse toWorkExperienceResponse(
            WorkExperience exp) {
        if (exp == null) return null;
        return WorkExperienceResponse.builder()
                .id(exp.getId())
                .companyName(exp.getCompanyName())
                .companyLogoUrl(exp.getCompanyLogoUrl())
                .jobTitle(exp.getJobTitle())
                .employmentType(exp.getEmploymentType())
                .location(exp.getLocation())
                .startDate(exp.getStartDate())
                .endDate(exp.getEndDate())
                .isCurrentJob(exp.getIsCurrentJob())
                .description(exp.getDescription())
                .technologies(exp.getTechnologies())
                .displayOrder(exp.getDisplayOrder())
                .build();
    }

    public static ResumeSkillResponse toSkillResponse(ResumeSkill skill) {
        if (skill == null) return null;
        return ResumeSkillResponse.builder()
                .id(skill.getId())
                .skillName(skill.getSkillName())
                .proficiencyLevel(skill.getProficiencyLevel())
                .yearsOfExperience(skill.getYearsOfExperience())
                .displayOrder(skill.getDisplayOrder())
                .build();
    }


}
