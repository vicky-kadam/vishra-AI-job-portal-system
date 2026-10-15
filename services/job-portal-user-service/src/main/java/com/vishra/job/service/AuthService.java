package com.vishra.job.service;


import com.vishra.job.payload.AuthResponse;
import com.vishra.job.payload.LoginRequest;
import com.vishra.job.payload.SignupRequest;

public interface AuthService {
    AuthResponse signup(SignupRequest request) throws Exception;
    AuthResponse login(LoginRequest request) throws Exception;
}
