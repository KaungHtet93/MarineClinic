package com.apsn.MarineClinic.Controllers;

import com.apsn.MarineClinic.Model.User;
import com.apsn.MarineClinic.Model.UserType;
import com.apsn.MarineClinic.Repository.UserRepository;
import com.apsn.MarineClinic.Repository.UserTypeRepository;
import com.apsn.MarineClinic.Service.UserDetailsServiceImpl;
import com.apsn.MarineClinic.dto.Input.LoginInput;
import com.apsn.MarineClinic.dto.Input.RegisterInput;
import com.apsn.MarineClinic.dto.response.LoginResponse;
import com.apsn.MarineClinic.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginInput req) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.email(), req.password())
        );

        UserDetails user = (UserDetails) auth.getPrincipal();
        String token = jwtUtil.generateToken(user);

        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterInput req) {

        UserType type = userTypeRepository.findById(req.userTypeId())
                .orElseThrow(() -> new RuntimeException("Invalid user type"));

        User user = new User();
        user.setName(req.name());
        user.setEmail(req.email());
        user.setPassword(passwordEncoder.encode(req.password()));
        user.setUserType(type);
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);

        return ResponseEntity.ok("User registered");
    }
}
