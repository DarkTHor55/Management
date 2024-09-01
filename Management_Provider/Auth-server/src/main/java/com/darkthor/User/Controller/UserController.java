package com.darkthor.User.Controller;

import com.darkthor.User.Model.User;
import com.darkthor.User.Request.LoginRequest;
import com.darkthor.User.Request.UserRequest;
import com.darkthor.User.Response.TokenResponse;
import com.darkthor.User.Response.UserRespose;
import com.darkthor.User.Service.Impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;
//    signup user
    @PostMapping("/signin")
    public ResponseEntity<UserRespose>createUser(@Valid @RequestBody final UserRequest user){
        User u =userService.signup(user);
        if (!Objects.isNull(u)){
            return new ResponseEntity<>(UserRespose.builder().message("User created").status(true).build(), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(UserRespose.builder().message("Failed to create user").status(false).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> loginUser(@Valid @RequestBody final LoginRequest user){
        TokenResponse user1 = userService.login(user);
        if (!Objects.isNull(user1)){
            return new ResponseEntity<>(user1, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(TokenResponse.builder().token("Invalid credentials").build(), HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        boolean t = userService.validateToken(token);
        if(t){
            return "Valid token";
        }else {
            return "Invalid token";
        }
    }

}
