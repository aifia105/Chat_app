package com.aifia.chat.App.repository;

import com.aifia.chat.App.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification, String> {
    List<Notification> findAllBySenderIdOrRecipientId(String senderId, String recipientId);
}
