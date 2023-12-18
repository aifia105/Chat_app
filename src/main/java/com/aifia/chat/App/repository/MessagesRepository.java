package com.aifia.chat.App.repository;

import com.aifia.chat.App.model.Messages;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessagesRepository extends MongoRepository<Messages, String> {
}
