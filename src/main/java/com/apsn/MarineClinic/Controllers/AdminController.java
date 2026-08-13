package com.apsn.MarineClinic.Controllers;

import com.apsn.MarineClinic.Service.UserService;
import com.apsn.MarineClinic.dto.Input.RegisterInput;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;

    // constructor injection (recommended)
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create-user")
    public ResponseEntity<?> createUserByAdmin(
            @RequestBody RegisterInput req
    ) {
        userService.createUserByAdmin(req); // 👈 HERE
        return ResponseEntity.ok(Map.of("message", "User created successfully"));
    }
}