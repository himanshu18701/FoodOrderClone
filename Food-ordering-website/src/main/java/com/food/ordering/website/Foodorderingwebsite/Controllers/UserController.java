package com.food.ordering.website.Foodorderingwebsite.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.food.ordering.website.Foodorderingwebsite.Services.LoginResponse;
import com.food.ordering.website.Foodorderingwebsite.Services.UserService;
import com.food.ordering.website.Foodorderingwebsite.Utils.JwtTokenUtil;
import com.food.ordering.website.Foodorderingwebsite.models.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest loginRequest) {
        LoginResponse loginResponse = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (loginResponse != null) {
            return ResponseEntity.ok(loginResponse);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        User newUser = userService.register(user);
        if (newUser != null) {
            UserDetails userDetails = new org.springframework.security.core.userdetails.User(newUser.getEmail(), newUser.getPassword(), new ArrayList<>());
            String token = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new AuthResponse(token));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists, please login!");
        }
    }


    @PutMapping("/updateProfile")
    public ResponseEntity<?> updateProfile(@RequestBody User user) {
        User updatedUser = userService.updateUserProfile(user);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @PutMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChangeRequest request) {
        boolean success = userService.changePassword(request.getUserId(), request.getCurrentPassword(), request.getNewPassword());
        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Current Password Is Not Correct!");
        }
    }

    @GetMapping("/getall/{searchTerm}")
    public List<User> getAllUsers(@PathVariable(required = false) String searchTerm) {
        return userService.getAllUsers(searchTerm);
    }

    @PutMapping("/toggleBlock/{userId}")
    public ResponseEntity<?> toggleBlockUser(@PathVariable String userId) {
        boolean isBlocked = userService.toggleBlockUser(userId);
        return ResponseEntity.ok(isBlocked);
    }

    @GetMapping("/getById/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable String userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        User updatedUser = userService.updateUser(user);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User update failed");
        }
    }

    static class AuthResponse {
        private String token;

        public AuthResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    static class UserLoginRequest {
        private String email;
        private String password;

        public UserLoginRequest() {}

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
