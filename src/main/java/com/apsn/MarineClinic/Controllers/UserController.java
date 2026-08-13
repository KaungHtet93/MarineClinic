package com.apsn.MarineClinic.Controllers;

import com.apsn.MarineClinic.Model.CustomUserDetails;
import com.apsn.MarineClinic.Model.User;
import com.apsn.MarineClinic.Repository.UserRepository;
import com.apsn.MarineClinic.dto.Input.UserUpdateInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(Authentication authentication) {
        if (authentication == null || authentication.getPrincipal() == null) {
            return ResponseEntity.status(401).build();
        }

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser(); // now safe
        return ResponseEntity.ok(user);
    }

    // PUT /user/update
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(Authentication authentication, @RequestBody  UserUpdateInput updatedUser) {
        if (authentication == null || authentication.getPrincipal() == null) {
            return ResponseEntity.status(401).build();
        }
        if(updatedUser == null){
            return ResponseEntity.badRequest().body("Request Body cannot br null");
        }
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser(); // now safe
        // Update fields (example: name, email, password)
        if(updatedUser.newPassword() != null && !updatedUser.newPassword().trim().isEmpty()){
            if(updatedUser.oldPassword()== null || updatedUser.oldPassword().trim().isEmpty()){
                return ResponseEntity.badRequest().body("OldPassword is required to set new password.");
            }
            if(!passwordEncoder.matches(updatedUser.oldPassword(),user.getPassword())){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Old password is incorrect");
            }
            if(updatedUser.newPassword().length()<5){
                return ResponseEntity.badRequest().body("New password must be at least 5 characters long");
            }
            String encodedPassword = passwordEncoder.encode(updatedUser.newPassword());
            user.setPassword(encodedPassword);
        }
        boolean hasChanges = false;
        if (updatedUser.name() != null  && !updatedUser.name().trim().isEmpty() &&
                !updatedUser.name().equals(user.getName())) {
            user.setName(updatedUser.name().trim());
            hasChanges = true;
        }
        if (updatedUser.email() != null && !updatedUser.email().trim().isEmpty() &&
                !updatedUser.email().equals(user.getEmail())) {
            // Check if email is already taken by another user
            if (userRepository.existsByEmailAndUserIdNot(updatedUser.email().trim(), user.getUserId())) {
                return ResponseEntity.badRequest().body("Email is already taken");
            }
            user.setEmail(updatedUser.email().trim());
            hasChanges = true;
        }
        if (hasChanges || (updatedUser.newPassword() != null && !updatedUser.newPassword().trim().isEmpty())) {
            User savedUser = userRepository.save(user);

            // Return user without sensitive data
            return ResponseEntity.ok(savedUser);
        } else {
            return ResponseEntity.ok("No changes detected");
        }
    }
}
