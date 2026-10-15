package com.vishra.job.service.impl;

import com.vishra.job.domain.UserStatus;
import com.vishra.job.dto.response.UserResponse;
import com.vishra.job.mapper.UserMapper;
import com.vishra.job.model.User;
import com.vishra.job.payload.UpdateUserRequest;
import com.vishra.job.repository.UserRepository;
import com.vishra.job.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUserById(Long id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() -> new Exception("User not found with id: " + id));
        return user;
    }

    @Override
    public User getUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new Exception("User not found with email: " + email);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() throws Exception {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public UserResponse updateProfile(String email, UpdateUserRequest req) throws Exception {
        User user = getUserByEmail(email);
        if(req.getFullName() != null){
            user.setFullName(req.getFullName());

        }
        if (req.getPhone() != null){
            user.setPhone(req.getPhone());
        }
        if( req.getProfileImage() != null){
            user.setProfileImage(req.getProfileImage());
        }
        userRepository.save(user);

        return UserMapper.todto(user);
    }

    @Override
    public UserResponse suspendUser(Long userId) throws Exception {
        User user = getUserById(userId);
        user.setStatus(UserStatus.SUSPENDED);
        user.setSuspendedAt(java.time.LocalDateTime.now());
        userRepository.save(user);


        return UserMapper.todto(user);
    }

    @Override
    public UserResponse activateUser(Long userId) throws Exception {
        User user = getUserById(userId);
        user.setStatus(UserStatus.ACTIVE);
        user.setSuspendedAt(null);
        return UserMapper.todto(user);
    }

    @Override
    public UserResponse deleteUser(Long userId) throws Exception {
        User user = getUserById(userId);
        user.setStatus(UserStatus.DELETED);
        user.setSuspendedAt(null);
        user.setDeletedAt(java.time.LocalDateTime.now());
        userRepository.delete(user);
        return UserMapper.todto(user);
    }
}
