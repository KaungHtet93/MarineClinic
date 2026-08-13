package com.apsn.MarineClinic.Controllers;

import com.apsn.MarineClinic.Model.AppUserDetails;
import com.apsn.MarineClinic.Model.CustomUserDetails;
import com.apsn.MarineClinic.Model.User;
import com.apsn.MarineClinic.Model.UserRole;
import com.apsn.MarineClinic.Repository.RoleRepository;
import com.apsn.MarineClinic.Repository.UserRepository;
import com.apsn.MarineClinic.Repository.UserRoleRepository;
import com.apsn.MarineClinic.dto.Input.LoginInput;
import com.apsn.MarineClinic.dto.Input.RegisterInput;
import com.apsn.MarineClinic.dto.Input.UserUpdateInput;
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
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginInput req) {

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.email(), req.password())
            );

            UserDetails user = (UserDetails) auth.getPrincipal();
            System.out.println("UserDetails class: " + user.getClass().getName());
            System.out.println("Authorities: " + user.getAuthorities());
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
        String roleName = "ROLE_USER";//Default
//        if ("ADMIN".equalsIgnoreCase(req.role())) {
//            // Option 1: disable completely
//            throw new RuntimeException("Admin role cannot be self-assigned");
//
//            // Option 2 (advanced): allow only if a secret key is provided
//        }
        UserRole role = userRoleRepository.findByName(roleName).orElseThrow(()->new RuntimeException("RoleNot found"));
        User user = new User();
        user.setName(req.name());
        user.setEmail(req.email());
        user.setPassword(passwordEncoder.encode(req.password()));
        user.setRole(role);
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);

        return ResponseEntity.ok(Map.of("message", "User registered successfully"));

    }
}
