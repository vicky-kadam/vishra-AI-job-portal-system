package com.vishra.job.controller;

import com.vishra.job.dto.response.UserResponse;
import com.vishra.job.mapper.UserMapper;
import com.vishra.job.model.User;
import com.vishra.job.payload.UpdateUserRequest;
import com.vishra.job.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/api/users/profile")
    public ResponseEntity<UserResponse> getProfile(@RequestHeader("X-User-Email") String email) throws Exception {
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(UserMapper.todto(user));
    }

    @PutMapping("/api/users/profile")
    public ResponseEntity<UserResponse> updateProfile(@RequestHeader("X-User-Email") String email, @RequestBody UpdateUserRequest req) throws Exception {
        UserResponse userResponse = userService.updateProfile(email, req);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) throws Exception {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(UserMapper.todto(user));
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() throws Exception {
        return ResponseEntity.ok(UserMapper.toDtoList(userService.getAllUsers()));
    }

    @PatchMapping("/api/users/{userId}/suspend")
    public ResponseEntity<UserResponse> suspendUser(@PathVariable Long userId) throws Exception {
        UserResponse userResponse = userService.suspendUser(userId);
        return ResponseEntity.ok(userResponse);
    }

    @PatchMapping("/api/users/{userId}/activate")
    public ResponseEntity<UserResponse> activateUser(@PathVariable Long userId) throws Exception {
        UserResponse userResponse = userService.activateUser(userId);
        return ResponseEntity.ok(userResponse);


    }

    @DeleteMapping("/api/users/{userId}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable Long userId) throws Exception {
        UserResponse userResponse = userService.deleteUser(userId);
        return ResponseEntity.ok(userResponse);
    }
}
