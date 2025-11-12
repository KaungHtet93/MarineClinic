package com.apsn.MarineClinic.Service;

import com.apsn.MarineClinic.Model.Disease;
import com.apsn.MarineClinic.Model.MedicalStaff;
import com.apsn.MarineClinic.Model.Role;
import com.apsn.MarineClinic.Repository.MedicalStaffRepository;
import com.apsn.MarineClinic.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository repository;
    public List<Role> getAllRole(){
        return repository.findAll();
    }
    public Optional<Role> getRoleById(Long id){
        return repository.findById(id);
    }
    public Role saveRole(Role input){
        return repository.save(input);
    }
    public List<Role> findRoleByName(String name){
        return repository.findByName(name);
    }
    public Role updateRole(Long id,Role input){
        Optional<Role> optional=repository.findById(id);
        if(optional.isPresent()) {
            return repository.save(input);
        } else throw new RuntimeException("Role not found");
    }
}
