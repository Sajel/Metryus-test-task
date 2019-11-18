package com.testtask.usermanagementapi.repository;

import com.testtask.usermanagementapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
