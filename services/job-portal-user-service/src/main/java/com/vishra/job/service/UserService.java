package com.vishra.job.service;

import com.vishra.job.dto.response.UserResponse;
import com.vishra.job.model.User;
import com.vishra.job.payload.UpdateUserRequest;

import java.util.List;

public interface UserService {
    User getUserById(Long id) throws Exception;
    User getUserByEmail(String email) throws Exception;
    List<User> getAllUsers() throws Exception;
    UserResponse updateProfile(String email, UpdateUserRequest req) throws Exception;

    //admin Action admin can anything whatever he wants to do with user
    UserResponse suspendUser(Long userId) throws Exception;
    UserResponse activateUser(Long userId) throws Exception;
    UserResponse deleteUser(Long userId) throws Exception;



}
