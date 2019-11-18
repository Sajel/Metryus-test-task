package com.testtask.usermanagementapi.controller;

import com.testtask.usermanagementapi.model.Role;
import com.testtask.usermanagementapi.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//@RestController
//@RequestMapping("/")
public class TestController {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public String test() {
//        Role role = new Role()
//                .setId(1L)
//                .setName("ADMIN");
//        rolesRepository.save(role);
        List<Role> all = roleRepository.findAll();
        return "Test";
    }

}
