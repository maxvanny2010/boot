package com.boot.repos;

import com.boot.model.Message;
import org.springframework.data.repository.CrudRepository;
import java.util.*;

/**
 * MessageRepository.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 9/2/2020
 */
public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findByTag(String tag);
}
