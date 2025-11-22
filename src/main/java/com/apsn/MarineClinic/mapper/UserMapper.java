package com.apsn.MarineClinic.mapper;

import com.apsn.MarineClinic.Model.MedicalStaff;
import com.apsn.MarineClinic.Model.PackageEntity;
import com.apsn.MarineClinic.Model.User;
import com.apsn.MarineClinic.dto.response.PackageResponse;
import com.apsn.MarineClinic.dto.response.UserResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")

public interface UserMapper {
    User toUser(UserResponse response);
    UserResponse toUserResponse(User user);
}
