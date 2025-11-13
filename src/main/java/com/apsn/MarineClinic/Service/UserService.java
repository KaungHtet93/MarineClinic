package com.apsn.MarineClinic.Service;

import com.apsn.MarineClinic.Model.Disease;
import com.apsn.MarineClinic.Model.Role;
import com.apsn.MarineClinic.Model.User;
import com.apsn.MarineClinic.Repository.RoleRepository;
import com.apsn.MarineClinic.Repository.UserRepository;
import com.apsn.MarineClinic.dto.response.UserResponse;
import com.apsn.MarineClinic.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    private UserMapper mapper;
    public List<UserResponse> getAllRole(){
        return mapper.toUserResponseList(repository.findAll());
    }
    public UserResponse getUserById(Long id){
        return mapper.toUserResponse(repository.findById(id).orElseThrow(RuntimeException::new));
    }
    public UserResponse saveUser(User input){
        return mapper.toUserResponse(repository.save(input)) ;
    }
    public List<UserResponse> findUserByName(String name){
        return mapper.toUserResponseList(repository.findByName(name)) ;
    }
    public UserResponse updateUser(Long id,User input){
        Optional<User> optional=repository.findById(id);
        if(optional.isPresent()) {
            User user= optional.get();
             repository.save(user);
             return mapper.toUserResponse(input);
        } else throw new RuntimeException("User not found");
    }
}
