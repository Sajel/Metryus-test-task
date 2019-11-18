package com.testtask.usermanagementapi;

import com.testtask.usermanagementapi.model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

public class RolesCRUDTest extends UserManagementApiApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void adminAndClientRolesExisted() {
        ResponseEntity<Role[]> response = restTemplate.getForEntity("/api/roles", Role[].class);

        assertThat(response.getStatusCode())
                .isEqualTo(OK);

        assertThat(response.getBody())
                .isNotNull();

        List<Role> roles = Arrays.asList(response.getBody());
        assertThat(roles)
                .size()
                .isEqualTo(2);
        assertThat(roles)
                .contains(new Role().setId(1L).setName("ADMIN"));
        assertThat(roles)
                .contains(new Role().setId(2L).setName("CLIENT"));
    }

    @Test
    void createReadDeleteRole() {
        String initialRoleName = "New role";
        Role newRole = new Role().setName(initialRoleName);

        Role createdRole = createRole(initialRoleName, newRole);
        Long id = createdRole.getId();

        checkRoleName(initialRoleName, id);

        String updatedRoleName = "Updated role";
        updateRole(id, updatedRoleName);
        checkRoleName(updatedRoleName, id);

        deleteRole(id, updatedRoleName);

        assertThat(restTemplate.getForEntity("/api/roles/" + id, Role.class).getStatusCode())
                .isEqualTo(NOT_FOUND);
    }

    private void deleteRole(Long id, String updatedRoleName) {
        HttpEntity<Role> deleteRequest = new HttpEntity<>(new Role().setName(updatedRoleName));
        ResponseEntity<Void> updatingResponse = restTemplate.exchange("/api/roles/" + id, DELETE, deleteRequest, Void.class);
        assertThat(updatingResponse.getStatusCode())
                .isEqualTo(OK);
    }

    private void updateRole(Long id, String updatedRoleName) {
        HttpEntity<Role> updateRequest = new HttpEntity<>(new Role().setName(updatedRoleName));
        ResponseEntity<Role> updatingResponse = restTemplate.exchange("/api/roles/" + id, PUT, updateRequest, Role.class);
        assertThat(updatingResponse.getStatusCode())
                .isEqualTo(OK);
        assertThat(updatingResponse.getBody())
                .isNotNull();
        assertThat(updatingResponse.getBody().getName())
                .isEqualTo(updatedRoleName);
    }

    private void checkRoleName(String roleName, Long id) {
        ResponseEntity<Role> receivingResponse = restTemplate.getForEntity("/api/roles/" + id, Role.class);

        assertThat(receivingResponse.getStatusCode())
                .isEqualTo(OK);
        assertThat(receivingResponse.getBody())
                .isNotNull();

        assertThat(receivingResponse.getBody().getName())
                .isEqualTo(roleName);
        assertThat(receivingResponse.getBody().getId())
                .isEqualTo(id);
    }

    private Role createRole(String newRoleName, Role newRole) {
        ResponseEntity<Role> creatingResponse = restTemplate.postForEntity("/api/roles", newRole, Role.class);
        assertThat(creatingResponse.getStatusCode())
                .isEqualTo(OK);
        assertThat(creatingResponse.getBody())
                .isNotNull();
        assertThat(creatingResponse.getBody().getName())
                .isEqualTo(newRoleName);

        Long id = creatingResponse.getBody().getId();
        assertThat(id)
                .isNotNull();
        return creatingResponse.getBody();
    }
}
