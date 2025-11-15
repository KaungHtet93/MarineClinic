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

    public List<Role> getAll() {
        return repository.findAll();
    }

    public Optional<Role> getById(Long id) {
        return repository.findById(id);
    }

    public Role create(Role input) {
        return repository.save(input);
    }

    public List<Role> findByName(String name) {
        return repository.findByName(name);
    }

    public Role update(Long id, Role input) {
        Optional<Role> optional = repository.findById(id);
        if (optional.isPresent()) {
            Role role = optional.get();
            role.setName(input.getName());
            return repository.save(role);
        } else throw new RuntimeException("Role not found");
    }

}
