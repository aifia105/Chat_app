package com.aifia.chat.App.repository;

import com.aifia.chat.App.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}
