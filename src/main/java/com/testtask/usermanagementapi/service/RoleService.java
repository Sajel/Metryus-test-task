package com.testtask.usermanagementapi.service;

import com.testtask.usermanagementapi.model.Role;
import com.testtask.usermanagementapi.repository.RolesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
public class RoleService {

    private final RolesRepository rolesRepository;

    public RoleService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public Collection<Role> getAllRoles() {
        return rolesRepository.findAll();
    }

    public Optional<Role> getRoleById(Long id) {
        return rolesRepository.findById(id);
    }

    @Transactional
    public Role createRole(Role role) {
        return rolesRepository.save(role);
    }

    @Transactional
    public boolean updateRole(Role role) {
        if (rolesRepository.existsById(role.getId())) {
            rolesRepository.save(role);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public void deleteRole(Long id) {
        rolesRepository.deleteById(id);
    }
}
