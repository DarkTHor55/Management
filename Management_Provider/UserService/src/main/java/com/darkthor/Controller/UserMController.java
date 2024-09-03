package com.darkthor.Controller;

import com.darkthor.Model.User;
import com.darkthor.Service.Impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/management")
@RequiredArgsConstructor
public class UserMController {
    private final UserServiceImpl userService;
    private static final Logger logger = LoggerFactory.getLogger(UserMController.class);

    @GetMapping("/{id}")
    public ResponseEntity<User> user(@PathVariable("id") Long id) {
        User user = userService.getUser(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        logger.info("User email: {}", user.getEmail());
        return ResponseEntity.ok(user);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> Updateuser(@PathVariable("id") Long id,@RequestBody User user) {
        User userToUpdate = userService.getUser(id);
        if (userToUpdate == null) {
            return ResponseEntity.notFound().build();
        }
        User updatedUser=userService.updateUser(id,user);
        logger.info("User email updated: {}", updatedUser.getEmail());
        return ResponseEntity.ok(updatedUser);

    }

    @GetMapping
    public ResponseEntity<List<User>> users() {
        List<User> users = userService.getUsers();
        if (users == null || users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(users);
    }
}
