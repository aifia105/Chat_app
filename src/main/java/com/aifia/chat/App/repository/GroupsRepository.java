package com.aifia.chat.App.repository;

import com.aifia.chat.App.model.Groups;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupsRepository extends MongoRepository<Groups, String> {
}
