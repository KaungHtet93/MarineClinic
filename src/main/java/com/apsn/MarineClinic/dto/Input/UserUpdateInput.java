package com.apsn.MarineClinic.dto.Input;

public record UserUpdateInput(String name,String oldPassword,String newPassword,String email) {
}
