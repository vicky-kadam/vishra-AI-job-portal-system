package com.vishra.job.service.impl;

import com.vishra.job.domain.UserRole;
import com.vishra.job.domain.UserStatus;
import com.vishra.job.mapper.UserMapper;
import com.vishra.job.model.User;
import com.vishra.job.payload.AuthResponse;
import com.vishra.job.payload.LoginRequest;
import com.vishra.job.payload.SignupRequest;
import com.vishra.job.repository.UserRepository;
import com.vishra.job.security.CustomUserDetailService;
import com.vishra.job.security.JwtProvider;
import com.vishra.job.service.AuthService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.vishra.job.model.User.*;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailService customUserDetailService;
    private final JwtProvider jwtProvider;

    @Override
    public AuthResponse signup(SignupRequest request) throws Exception {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new Exception("User already exists with email: " + request.getEmail());
        }

        if (request.getRole() == UserRole.ROLE_ADMIN){
            throw new Exception("Admin role cannot be assigned during signup.");
        }

        User user  = builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .lastLogin(LocalDateTime.now())
                .status(UserStatus.ACTIVE)
                .build();

        User savedUser = userRepository.save(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt  = jwtProvider.genrateToken(authentication, savedUser.getId());



        AuthResponse response = new AuthResponse();
        response.setTitle("welcome " + savedUser.getFullName());
        response.setMessage("User registered successfully");
        response.setJwt(jwt);
        response.setUser(UserMapper.todto(savedUser));


        return response;
    }

    @Override
    public AuthResponse login(LoginRequest request) throws Exception {

        Authentication authentication = Authenticate(request.getEmail(),request.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userRepository.findByEmail(request.getEmail());
        String jwt = jwtProvider.genrateToken(authentication, user.getId());
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        AuthResponse response = new AuthResponse();
        response.setTitle("welcome Back Shera " + user.getFullName());
        response.setMessage("User Login successfully");
        response.setJwt(jwt);
        response.setUser(UserMapper.todto(user));
        return response;
    }

    private Authentication Authenticate(@NotBlank(message = "Email is required") @Email(message = "Email should be valid") String email, @NotBlank(message = "Password is required") String password) throws Exception {
        UserDetails userDetails = customUserDetailService.loadUserByUsername(email);
        if(userDetails == null) {
            throw new Exception("User not found with email: " + email);

        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new Exception("Invalid password");

        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}
