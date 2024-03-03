package com.food.ordering.website.Foodorderingwebsite.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.food.ordering.website.Foodorderingwebsite.Repository.UserRepository;
import com.food.ordering.website.Foodorderingwebsite.Utils.JwtTokenUtil;
import com.food.ordering.website.Foodorderingwebsite.models.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService  {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

    public LoginResponse login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && bCryptPasswordEncoder.matches(password, user.getPassword())) {
            UserDetails userDetails = new org.springframework.security.core.userdetails.User (user.getEmail(), user.getPassword(), new ArrayList<>());
            String token= jwtTokenUtil.generateToken(userDetails);
            LoginResponse response=new LoginResponse();
            response.setToken(token);
            response.setName(user.getName());
            response.setId(user.getId());
            response.setAddress(user.getAddress());
            return response;
        }
        return null;
    }

    public User register(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return null;
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User updateUserProfile(User user) {
        Optional<User> existingUser = userRepository.findById(user.getId());
        if (existingUser.isPresent()) {
            User updatedUser = existingUser.get();
            updatedUser.setName(user.getName());
            updatedUser.setAddress(user.getAddress());
            return userRepository.save(updatedUser);
        }
        return null;
    }

    public boolean changePassword(String userId, String currentPassword, String newPassword) {
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isPresent() && bCryptPasswordEncoder.matches(currentPassword, existingUser.get().getPassword())) {
            User user = existingUser.get();
            user.setPassword(bCryptPasswordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public List<User> getAllUsers(String searchTerm) {
        if (searchTerm != null && !searchTerm.isEmpty()) {
            Pattern pattern = Pattern.compile(searchTerm, Pattern.CASE_INSENSITIVE);
            return userRepository.findAll().stream()
                    .filter(user -> pattern.matcher(user.getName()).find())
                    .collect(Collectors.toList());
        } else {
            return userRepository.findAll();
        }
    }

    public boolean toggleBlockUser(String userId) {
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setIsBlocked(!user.getIsBlocked());
            userRepository.save(user);
            return user.getIsBlocked();
        }
        return false;
    }

    public User getUserById(String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User updateUser(User user) {
        return userRepository.findById(user.getId())
                .map(existingUser -> {
                    existingUser.setName(user.getName());
                    existingUser.setEmail(user.getEmail());
                    existingUser.setAddress(user.getAddress());
                    existingUser.setIsAdmin(user.getIsAdmin());
                    return userRepository.save(existingUser);
                }).orElse(null);
    }
}
