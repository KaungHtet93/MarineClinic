package com.apsn.MarineClinic.Controllers;

import com.apsn.MarineClinic.Model.CustomUserDetails;
import com.apsn.MarineClinic.Model.User;
import com.apsn.MarineClinic.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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
    public ResponseEntity<User> updateUser(Authentication authentication, @RequestBody User updatedUser) {
        if (authentication == null || authentication.getPrincipal() == null) {
            return ResponseEntity.status(401).build();
        }

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser(); // now safe
        // Update fields (example: name, email, password)
        if (updatedUser.getName() != null) user.setName(updatedUser.getName());
        if (updatedUser.getEmail() != null) user.setEmail(updatedUser.getEmail());
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            String encoded = passwordEncoder.encode(updatedUser.getPassword());
            user.setPassword(encoded);
        }
        userRepository.save(user);

        return ResponseEntity.ok(user);
    }
}
