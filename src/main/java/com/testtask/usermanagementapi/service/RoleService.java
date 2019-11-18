package com.testtask.usermanagementapi.service;

import com.testtask.usermanagementapi.model.Role;
import com.testtask.usermanagementapi.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Collection<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    @Transactional
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Transactional
    public boolean updateRole(Role role) {
        if (roleRepository.existsById(role.getId())) {
            roleRepository.save(role);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    public Collection<Role> getRolesByNames(Collection<String> roleNames) {
        return roleRepository.findAllByNameIn(roleNames);
    }
}
