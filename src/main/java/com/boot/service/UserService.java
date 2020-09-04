package com.boot.service;

import com.boot.model.Role;
import com.boot.model.User;
import com.boot.repos.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.UUID;

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
    private final MailSender mails;

    public UserService(final UserRepository users, final MailSender mails) {
        this.users = users;
        this.mails = mails;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return this.users.findByUsername(username);
    }

    public boolean addUser(User user) {
        final User userFromDB = this.users.findByUsername(user.getUsername());
        if (userFromDB != null) {
            return false;
        }
        user.setEnabled(false);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        this.users.save(user);
        if (!StringUtils.isEmpty(user.getEmail())) {
            final String message = String.format(
                    "Click for activation http://localhost:8080/registration/active/%s",
                    user.getActivationCode());
            this.mails.send(user.getEmail(), "Activation code.", message);
        }
        return true;
    }

    public boolean activateUser(final String code) {
        User user = this.users.findByActivationCode(code);
        if (user == null) {
            return false;
        }
        user.setActivationCode(null);
        user.setEnabled(true);
        this.users.save(user);
        return true;
    }
}
