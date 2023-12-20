package com.aifia.chat.App.repository;

import com.aifia.chat.App.model.Groups;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ListIterator;

public interface GroupsRepository extends MongoRepository<Groups, String> {
    ListIterator<Groups> findByParticipantsIsContaining(String id);
}
