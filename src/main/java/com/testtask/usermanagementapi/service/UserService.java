package com.testtask.usermanagementapi.service;

import com.testtask.usermanagementapi.model.Role;
import com.testtask.usermanagementapi.model.User;
import com.testtask.usermanagementapi.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    public UserService(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    public Collection<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public boolean updateUser(User user) {
        if (userRepository.existsById(user.getId())) {
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public User addRoles(long userId, Collection<String> roleNames) {
        User user = userRepository.getOne(userId);

        Collection<Role> roles = roleService.getRolesByNames(roleNames);
        user.getRoles().addAll(roles);

        return userRepository.save(user);
    }

    @Transactional
    public User deleteRoles(long userId, Collection<String> roleNames) {
        User user = userRepository.getOne(userId);

        Collection<Role> roles = roleService.getRolesByNames(roleNames);
        user.getRoles().removeAll(roles);

        return userRepository.save(user);
    }

}
