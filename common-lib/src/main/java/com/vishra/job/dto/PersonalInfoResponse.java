package com.vishra.job.dto;


import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonalInfoResponse {
    private String firstName;
    private String LastName;
    private String headLine;

    private String email;
    private String phone;
    private String city;
    private String country;

    private String linkedInUrl;
    private String githubUrl;
    private String portfolioUrl;
    private String websiteUrl;
}
