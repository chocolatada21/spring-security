package com.chocolatada.Spring.Security.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigurationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void notSecured_shouldReturnOKStatusWithUnauthorizedUser() throws Exception {
        mockMvc.perform(get("/notSecured"))
                .andExpect(status().isOk())
                .andExpect(content().string("notSecured"));
    }

    @WithMockUser
    @Test
    void secured_shouldReturnOKStatusWithAuthorizedUser() throws Exception {
        mockMvc.perform(get("/secured"))
                .andExpect(status().isOk())
                .andExpect(content().string("secured"));
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    void admin_shouldReturnOKStatusWithAuthorizedUserWhoHasAdminRole() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(content().string("admin"));
    }

    @Test
    void secured_shouldReturn401UnauthorizedWithUnauthorizedUser() throws Exception {
        mockMvc.perform(get("/secured"))
                .andExpect(status().isUnauthorized());
    }

    @WithMockUser()
    @Test
    void admin_shouldReturn403ForbiddenWithAuthenticatedUserWithoutAdminRole() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isForbidden());
    }
}
