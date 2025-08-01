package com.chocolatada.Spring.Security.service;

import com.chocolatada.Spring.Security.entity.RoleEntity;
import com.chocolatada.Spring.Security.entity.RoleEnum;
import com.chocolatada.Spring.Security.entity.UserEntity;
import com.chocolatada.Spring.Security.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.Optional;
import java.util.Set;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceImplTest {
    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Test
    void loadUserByUsername_shouldReturnAUserWithValidUsername() {
        RoleEntity role = new RoleEntity();
        role.setName(RoleEnum.USER);
        String validUsername = "username";
        UserEntity validUser = new UserEntity();
        validUser.setUsername(validUsername);
        validUser.setPassword("$2a10$...");
        validUser.setRoles(Set.of(role));

        when(userRepository.findUserEntityByUsername(validUsername))
                .thenReturn(Optional.of(validUser));

        UserDetails result = userDetailsService.loadUserByUsername(validUsername);

        assertThat(result.getUsername()).isEqualTo(validUser.getUsername());
        assertThat(result.getPassword()).isEqualTo(validUser.getPassword());
        assertThat(result.getAuthorities()).isEqualTo(validUser.getAuthorities());
    }

    @Test
    void loadUserByUsername_shouldThrowExceptionWithInvalidUsername() {
        String invalidUsername = "username";

        when(userRepository.findUserEntityByUsername(invalidUsername))
                .thenReturn(Optional.empty());

        assertThatExceptionOfType(UsernameNotFoundException.class)
                .isThrownBy(
                        () -> userDetailsService.loadUserByUsername(invalidUsername)
                );
    }
}
