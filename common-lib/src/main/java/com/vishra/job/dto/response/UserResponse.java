package com.vishra.job.dto.response;

import com.vishra.job.domain.UserRole;
import com.vishra.job.domain.UserStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserResponse {

    private Long id;
    private String fullName;

    private String email;
    private String phone;
    private String profileImage;
    private UserRole role;
    private UserStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime LastLogin;


}