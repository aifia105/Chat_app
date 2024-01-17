package com.aifia.chat.App.repository;

import com.aifia.chat.App.model.Conversations;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;
import java.util.Optional;

public interface ConversationsRepository extends MongoRepository<Conversations, String> {


    Optional<Conversations> findByParticipantsAndType(@NotBlank List<String> participants, String type);

    List<Conversations> findByParticipantsIsContaining(String userId);

}
