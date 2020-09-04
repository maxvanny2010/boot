package com.boot.repos;

import com.boot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRepository.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 9/2/2020
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);

    User findByActivationCode(String code);
}
