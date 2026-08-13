package com.apsn.MarineClinic.dto.response;

import java.time.LocalDateTime;

public record UserResponse(Long user_Id, String name, String email, String password, LocalDateTime createdAt, LocalDateTime updatedAt){
}
