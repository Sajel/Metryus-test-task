package com.testtask.usermanagementapi.repository;

import com.testtask.usermanagementapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
//    User findByLogin(String login);
}
