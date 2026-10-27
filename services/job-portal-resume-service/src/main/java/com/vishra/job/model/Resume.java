package com.vishra.job.model;

import com.vishra.job.domain.ResumeTemplate;
import com.vishra.job.domain.ResumeVisibility;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "resumes")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long candidateId;

    @Column(nullable = false)
    private String title;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ResumeTemplate resumeTemplate =ResumeTemplate.PROFESSIONAL;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ResumeVisibility resumeVisibility = ResumeVisibility.PUBLIC;

    @Column(nullable = false)
    private Boolean isDefault = false;

    @Embedded
    private PersonalInfo personalInfo;

    private String summary;

    private Integer completionScore;

    private Boolean isActive = true;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;








}
