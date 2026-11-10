package com.vishra.job.mapper;

import com.vishra.job.dto.SavedJobResponse;
import com.vishra.job.entity.SavedJob;

public class PreferenceMapper {

    private PreferenceMapper() {}



    public static SavedJobResponse toSavedJobResponse(SavedJob savedJob) {
        if (savedJob == null) return null;
        return SavedJobResponse.builder()
                .id(savedJob.getId())
                .candidateId(savedJob.getCandidateId())
                .jobId(savedJob.getJobId())
                .companyId(savedJob.getCompanyId())
                .notes(savedJob.getNotes())
                .savedAt(savedJob.getSavedAt())
                .build();
    }
}
