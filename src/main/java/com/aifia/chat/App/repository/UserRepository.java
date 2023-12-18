package com.aifia.chat.App.repository;

import com.aifia.chat.App.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findUserByEmail(String email);

    List<User> findAllByOnlineStatus(Boolean status);
}
