package com.testtask.usermanagementapi.controller;

import com.testtask.usermanagementapi.model.Role;
import com.testtask.usermanagementapi.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RestController
//@RequestMapping("/")
public class TestController {

    @Autowired
    private RolesRepository rolesRepository;

    @GetMapping
    public String test() {
//        Role role = new Role()
//                .setId(1L)
//                .setName("ADMIN");
//        rolesRepository.save(role);
        List<Role> all = rolesRepository.findAll();
        return "Test";
    }

}
