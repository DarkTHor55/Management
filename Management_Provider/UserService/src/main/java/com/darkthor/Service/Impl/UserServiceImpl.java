package com.darkthor.Service.Impl;

import com.darkthor.Model.User;
import com.darkthor.Repository.UserRepository;
import com.darkthor.Request.UserRequest;
import com.darkthor.Service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    @Autowired PasswordEncoder passwordEncoder ;
    private final UserRepository userRepository;

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    public User updateUser(Long id,User request) {
        User user=userRepository.findById(id).orElseThrow(null);
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}