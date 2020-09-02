package com.boot.service;

import com.boot.repos.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * UserService.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 9/2/2020
 */
@Service
public class UserService implements UserDetailsService {
    private final UserRepository users;

    public UserService(final UserRepository users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return this.users.findByUsername(username);
    }
}
