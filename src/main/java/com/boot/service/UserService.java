package com.boot.service;

import com.boot.model.Role;
import com.boot.model.User;
import com.boot.repos.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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
    private final PasswordEncoder passwordEncoder;
    @Value("${registration.path}")
    private String path;

    public UserService(final UserRepository users, final MailSender mails, final PasswordEncoder passwordEncoder) {
        this.users = users;
        this.mails = mails;
        this.passwordEncoder = passwordEncoder;
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
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        try {
            this.users.save(user);
        } catch (Exception e) {
            return false;
        }
        this.sentMessage(user);
        return true;
    }

    private void sentMessage(final User user) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            final String message = String.format(
                    "Click for activation " + this.path + "registration/active/%s",
                    user.getActivationCode());
            this.mails.send(user.getEmail(), "Activation code.", message);
        }
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

    public List<User> findAll() {
        return this.users.findAll();
    }

    public void saveUser(final User user, final String username, final Map<String, String> form) {
        user.setUsername(username);
        final Set<String> roles = Arrays
                .stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        form.keySet()
                .stream()
                .filter(roles::contains)
                .forEach(key -> user.getRoles().add(Role.valueOf(key)));
        this.users.save(user);
    }

    public void updateUser(final User user, final String password, final String email) {
        final String userEmail = user.getEmail();
        boolean isEmailChange = (email != null && !email.equals(userEmail)) ||
                userEmail != null && !userEmail.equals(email);
        if (isEmailChange) {
            user.setEmail(email);
            if (!StringUtils.isEmpty(email)) {
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }
        if (!StringUtils.isEmpty(password)) {
            user.setPassword(this.passwordEncoder.encode(password));
        }
        this.users.save(user);
        if (isEmailChange) {
            this.sentMessage(user);
        }
    }

    public void subscribe(final User currentUser, final User user) {
        user.getSubscribers().add(currentUser);
        this.users.save(user);
    }

    public void unsubscribe(final User currentUser, final User user) {
        user.getSubscribers().remove(currentUser);
        this.users.save(user);
    }
}
