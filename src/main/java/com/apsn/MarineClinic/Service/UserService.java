package com.apsn.MarineClinic.Service;

import com.apsn.MarineClinic.Model.User;
import com.apsn.MarineClinic.Model.UserRole;
import com.apsn.MarineClinic.Repository.UserRepository;
import com.apsn.MarineClinic.Repository.UserRoleRepository;
import com.apsn.MarineClinic.dto.Input.RegisterInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public void createUserByAdmin(RegisterInput req) {

        String roleName = switch (req.role().toUpperCase()) {
            case "ADMIN" -> "ROLE_ADMIN";
            default -> "ROLE_USER";
        };

        UserRole role = roleRepository
                .findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = new User();
        user.setName(req.name());
        user.setEmail(req.email());
        user.setPassword(passwordEncoder.encode(req.password()));
        user.setRole(role);
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);
    }
}
