package com.apsn.MarineClinic.dto.response;

import com.apsn.MarineClinic.Model.UserType;

import java.time.LocalDateTime;

public record UserResponse(Integer userId, String name, String email, String password, LocalDateTime createdAt, LocalDateTime updatedAt,
                           UserType userType){
}
