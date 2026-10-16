package com.vishra.job.model;

import com.vishra.job.domain.CompanySize;
import com.vishra.job.domain.CompanyStatus;
import com.vishra.job.domain.CompanyType;
import com.vishra.job.domain.IndustryType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;
    @Column(unique = true)
    private String slug;

    private String tagline;

    private String description;

    private String logoUrl;
    private String coverImageUrl;
    private String website;
    private String email;
    private String phone;
    private Integer founderYear;
    @Enumerated(EnumType.STRING)
    private CompanySize companySize;

    @Enumerated(EnumType.STRING)
    private CompanyType companyType;

    @Enumerated(EnumType.STRING)
    private IndustryType industryType;

    @Enumerated(EnumType.STRING)
    private CompanyStatus status;

    private Boolean isVerified =false;

    @Column(unique = true)
    private String registrationNumber;

    private Long ownerId;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<SocialLink> socialLink = new ArrayList<>();

    private Boolean active = true;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    private LocalDateTime verifiedAt;






}
