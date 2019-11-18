package com.testtask.usermanagementapi.controller;

import com.testtask.usermanagementapi.model.Role;
import com.testtask.usermanagementapi.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<Collection<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRole(@PathVariable long id) {
        return ResponseEntity.of(roleService.getRoleById(id));
    }

    @PostMapping
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        return ResponseEntity.ok(roleService.createRole(role));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@RequestBody Role role, @PathVariable long id) {
        role.setId(id);
        if (roleService.updateRole(role)) {
            return ResponseEntity.ok(role);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRole(@PathVariable long id) {
        roleService.deleteRole(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Role> updatePartOfRole(@RequestBody Role role, @PathVariable long id) {
        //TODO
        return ResponseEntity.notFound().build();
    }
}
