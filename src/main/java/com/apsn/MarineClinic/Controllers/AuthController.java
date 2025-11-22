package com.apsn.MarineClinic.Controllers;

import com.apsn.MarineClinic.Model.CustomUserDetails;
import com.apsn.MarineClinic.Model.User;
import com.apsn.MarineClinic.Repository.UserRepository;
import com.apsn.MarineClinic.dto.Input.LoginInput;
import com.apsn.MarineClinic.dto.Input.RegisterInput;
import com.apsn.MarineClinic.dto.response.LoginResponse;
import com.apsn.MarineClinic.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginInput req) {

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.email(), req.password())
            );

            UserDetails user = (UserDetails) auth.getPrincipal();
            String token = jwtUtil.generateToken(user);

            return ResponseEntity.ok(new LoginResponse(token));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Invalid email or password");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(404).body("User account does not exist");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterInput req) {

        User user = new User();
        user.setName(req.name());
        user.setEmail(req.email());
        user.setPassword(passwordEncoder.encode(req.password()));
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);

        return ResponseEntity.ok(Map.of("message", "User registered successfully"));

    }

    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(Authentication authentication) {
        if (authentication == null || authentication.getPrincipal() == null) {
            return ResponseEntity.status(401).build();
        }
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();
        return ResponseEntity.ok(user);
    }

    // PUT /user/update
    @PutMapping("/update")
    public ResponseEntity<User> updateUser(Authentication authentication, @RequestBody User updatedUser) {
        if (authentication == null || authentication.getPrincipal() == null) {
            return ResponseEntity.status(401).build();
        }

        User user = (User) authentication.getPrincipal();

        // Update fields (example: name, email, password)
        if (updatedUser.getName() != null) user.setName(updatedUser.getName());
        if (updatedUser.getEmail() != null) user.setEmail(updatedUser.getEmail());
        if (updatedUser.getPassword() != null) user.setPassword(updatedUser.getPassword()); // encode in service

        userRepository.save(user);

        return ResponseEntity.ok(user);
    }
}
