package com.chocolatada.Spring.Security.service;

import com.chocolatada.Spring.Security.entity.UserEntity;
import com.chocolatada.Spring.Security.repository.IUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final IUserRepository userRepository;

    public UserDetailsServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findUserEntityByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User of username: "+username+" not found.")
                );
    }
}
