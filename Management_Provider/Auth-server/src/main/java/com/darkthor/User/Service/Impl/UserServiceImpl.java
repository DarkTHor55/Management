package com.darkthor.User.Service.Impl;

import com.darkthor.User.Configuration.JwtUtil;
import com.darkthor.User.Model.User;
import com.darkthor.User.Repository.UserRepository;
import com.darkthor.User.Request.LoginRequest;
import com.darkthor.User.Request.UserRequest;
import com.darkthor.User.Response.TokenResponse;
import com.darkthor.User.Service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil  jwtUtil ;
    @Override
    public User signup(UserRequest request) {
        User user= User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        return userRepository.save(user);
    }

    @Override
    public TokenResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        if(user!=null && passwordEncoder.matches(request.getPassword(),user.getPassword())&&request.getRole().equals(user.getRole())){
            String token = jwtUtil.generateToken(user);
            return TokenResponse.builder().token(token).build();
        }
        return null;

    }


    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
