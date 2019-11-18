package com.testtask.usermanagementapi.service;

import com.testtask.usermanagementapi.model.Role;
import com.testtask.usermanagementapi.model.User;
import com.testtask.usermanagementapi.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    public static final User USER = new User().setId(1L).setLogin("Login");

    @InjectMocks
    UserService userService;

    @Mock
    RoleService roleService;
    @Mock
    UserRepository userRepository;

    @Test
    void userShouldContainRolesAfterAdding() {
        when(userRepository.getOne(Mockito.eq(1L))).thenReturn(USER);
        when(roleService.getRolesByNames(any())).thenReturn(Collections.singleton(new Role().setName("ADMIN")));
        when(userRepository.save(any())).thenAnswer(invocation -> invocation.getArguments()[0]);

        User updatedUser = userService.addRoles(USER.getId(), Collections.singleton("ADMIN"));

        assertThat(updatedUser.getRoles())
                .contains(new Role().setName("ADMIN"));
    }

}