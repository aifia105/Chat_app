package com.aifia.chat.App.repository;

import com.aifia.chat.App.model.Conversations;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConversationsRepository extends MongoRepository<Conversations, String> {
}
