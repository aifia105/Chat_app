package com.aifia.chat.App.repository;

import com.aifia.chat.App.model.Messages;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessagesRepository extends MongoRepository<Messages, String> {
    List<Messages> findByConversationId(String chatId);
}
