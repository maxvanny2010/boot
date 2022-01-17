package com.boot.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.*;

/**
 * User.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 9/2/2020
 */
@Entity
@Table(name = "usr")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user")
    private Long id;
    @NotEmpty(message = "Поле не может быть пустым")
    private String username;
    @NotEmpty(message = "Поле не может быть пустым")
    private String password;
    @Transient
    private String password2;
    private boolean enabled;
    @Email(message = "Адрес не корректен")
    @NotEmpty(message = "Поле не может быть пустым")
    private String email;
    private String activationCode;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Message> messages;
    @ManyToMany
    @JoinTable(name = "user_subscriptions",
            joinColumns = {@JoinColumn(name = "channel_id")},
            inverseJoinColumns = {@JoinColumn(name = "subscriber_id")})
    private Set<User> subscribers = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "user_subscriptions",
            joinColumns = {@JoinColumn(name = "subscriber_id")},
            inverseJoinColumns = {@JoinColumn(name = "channel_id")})
    private Set<User> subscriptions = new HashSet<>();

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public User(final String username, final String password, final boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public User() {
    }

    public boolean isAdmin() {
        return this.roles.contains(Role.ADMIN);
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(final Set<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(final String activationCode) {
        this.activationCode = activationCode;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(final String password2) {
        this.password2 = password2;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(final Set<Message> messages) {
        this.messages = messages;
    }

    public Set<User> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(final Set<User> subscribers) {
        this.subscribers = subscribers;
    }

    public Set<User> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(final Set<User> subscriptions) {
        this.subscriptions = subscriptions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        final User user = (User) o;
        return getId().equals(user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
