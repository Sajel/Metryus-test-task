package com.testtask.usermanagementapi.repository;

import com.testtask.usermanagementapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Role, Long> {
}
