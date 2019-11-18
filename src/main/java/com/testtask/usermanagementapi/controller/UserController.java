package com.testtask.usermanagementapi.controller;

import com.testtask.usermanagementapi.model.Role;
import com.testtask.usermanagementapi.model.User;
import com.testtask.usermanagementapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Collection<User>> getAllRoles() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        return ResponseEntity.of(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable long id) {
        user.setId(id);
        if (userService.updateUser(user)) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRole(@PathVariable long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Role> updatePartOfUser(@RequestBody User user, @PathVariable long id) {
        //TODO
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/roles")
    public ResponseEntity<User> addRoles(@RequestBody Collection<String> roles, @PathVariable long id) {
        return ResponseEntity.ok(userService.addRoles(id, roles));
    }

    @DeleteMapping("/{id}/roles")
    public ResponseEntity<User> deleteRoles(@RequestBody Collection<String> roles, @PathVariable long id) {
        return ResponseEntity.ok(userService.deleteRoles(id, roles));
    }
}
