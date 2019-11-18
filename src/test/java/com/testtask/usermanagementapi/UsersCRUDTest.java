package com.testtask.usermanagementapi;

import com.testtask.usermanagementapi.model.Role;
import com.testtask.usermanagementapi.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

public class UsersCRUDTest extends UserManagementApiApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void createUserAddRoleRemoveRole() {
        User user = new User().setLogin("Login-length").setPassword("password2");

        ResponseEntity<User> creatingResponse = restTemplate.postForEntity("/api/users", user, User.class);
        assertThat(creatingResponse.getStatusCode())
                .isEqualTo(CREATED);
        assertThat(creatingResponse.getBody())
                .isNotNull();

        Long userId = creatingResponse.getBody().getId();
        assertThat(userId).isNotNull();

        String roleName = "ROLE_NAME";
        createRole(new Role().setName(roleName));

        User userAfterAddingRole = addRoleToUser(userId, roleName);
        assertThat(userAfterAddingRole.getRoles())
                .size()
                .isEqualTo(1);
        assertThat(userAfterAddingRole.getRoles())
                .contains(new Role().setName(roleName));

        User userAfterDeletingRole = deleteRoleFromUser(userId, roleName);
        assertThat(userAfterDeletingRole.getRoles())
                .isEmpty();
    }

    private User addRoleToUser(Long userId, String roleName) {
        ResponseEntity<User> addingRoleResponse = restTemplate.postForEntity("/api/users/" + userId + "/roles",
                Set.of(roleName), User.class);
        assertThat(addingRoleResponse.getStatusCode())
                .isEqualTo(OK);
        assertThat(addingRoleResponse.getBody())
                .isNotNull();
        return addingRoleResponse.getBody();
    }

    private User deleteRoleFromUser(Long userId, String roleName) {
        HttpEntity<Collection<String>> deleteRequest = new HttpEntity<>(Set.of(roleName));
        ResponseEntity<User> deletingResponse = restTemplate.exchange("/api/users/" + userId + "/roles",
                DELETE, deleteRequest, User.class);
        assertThat(deletingResponse.getStatusCode())
                .isEqualTo(OK);
        assertThat(deleteRequest.getBody())
                .isNotNull();
        return deletingResponse.getBody();
    }

    private Role createRole(Role newRole) {
        ResponseEntity<Role> creatingResponse = restTemplate.postForEntity("/api/roles", newRole, Role.class);
        assertThat(creatingResponse.getStatusCode())
                .isEqualTo(CREATED);
        assertThat(creatingResponse.getBody())
                .isNotNull();
        assertThat(creatingResponse.getBody().getName())
                .isEqualTo(newRole.getName());

        Long id = creatingResponse.getBody().getId();
        assertThat(id)
                .isNotNull();
        return creatingResponse.getBody();
    }
}
