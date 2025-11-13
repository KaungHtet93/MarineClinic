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
    public List<UserType> getAllRole(){
        return repository.findAll();
    }
    public Optional<UserType> getUserById(Long id){
        return repository.findById(id);
    }
    public UserType saveUser(UserType input){
        return repository.save(input);
    }
    public List<UserType> findUserTypeByName(String name){
        return repository.findByName(name);
    }
    public UserType updateUser(Long id,UserType input){
        Optional<UserType> optional=repository.findById(id);
        if(optional.isPresent()) {
            UserType type=optional.get();
            return repository.save(type);
        } else throw new RuntimeException("User not found");
    }
}
