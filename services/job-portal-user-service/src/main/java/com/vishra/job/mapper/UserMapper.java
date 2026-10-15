package com.vishra.job.mapper;

import com.vishra.job.dto.response.UserResponse;
import com.vishra.job.model.User;
import lombok.Data;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserMapper {
    public static UserResponse todto(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setFullName(user.getFullName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhone(user.getPhone());
        userResponse.setProfileImage(user.getProfileImage());
        userResponse.setRole(user.getRole());
        userResponse.setStatus(user.getStatus());
        userResponse.setCreatedAt(user.getCreatedAt());
        userResponse.setLastLogin(user.getLastLogin());
        return userResponse;


    }

    public static @Nullable List<UserResponse> toDtoList(List<User> allUsers) {
        return allUsers.stream()
                .map(UserMapper::todto)
                .collect(Collectors.toList());
    }
}
