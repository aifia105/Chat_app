package com.aifia.chat.App.repository;

import com.aifia.chat.App.model.Conversations;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ConversationsRepository extends MongoRepository<Conversations, String> {

    Optional<Conversations> findBySenderIdAndRecipientId(String senderId, String recipientId);
}
