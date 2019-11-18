package com.testtask.usermanagementapi.repository;

import com.testtask.usermanagementapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Collection<Role> findAllByNameIn(Collection<String> names);
}
