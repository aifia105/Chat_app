package com.aifia.chat.App.repository;
import com.aifia.chat.App.model.GroupConversation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface GroupConversationRepository extends MongoRepository<GroupConversation, String> {
    Optional<GroupConversation> findGroupConversationByParticipantsContaining(String id);
}
