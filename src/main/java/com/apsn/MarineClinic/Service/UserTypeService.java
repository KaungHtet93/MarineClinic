package com.apsn.MarineClinic.Service;

import com.apsn.MarineClinic.Model.Disease;
import com.apsn.MarineClinic.Model.UserType;
import com.apsn.MarineClinic.Repository.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserTypeService {
    @Autowired
    private UserTypeRepository repository;

    public List<UserType> getAll() {
        return repository.findAll();
    }

    public Optional<UserType> getById(Long id) {
        return repository.findById(id);
    }

    public UserType saveUser(UserType input) {
        return repository.save(input);
    }

    public List<UserType> findByName(String name) {
        return repository.findByName(name);
    }

    public UserType update(Long id, UserType input) {
        Optional<UserType> optional = repository.findById(id);
        if (optional.isPresent()) {
            UserType type = optional.get();
            type.setUserTypeName(input.getUserTypeName());
            return repository.save(type);
        } else throw new RuntimeException("User not found");
    }
}
