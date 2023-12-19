package com.aifia.chat.App.services;


import com.aifia.chat.App.model.Notification;
import com.aifia.chat.App.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;


      public List<Notification> getUserNotifications(String Userid) {
         return notificationRepository.findAllBySenderIdOrRecipientId(Userid,Userid);
      }

      public void markNotificationAsRead(String id) {

      }

      public void save(Notification notification) {
          notificationRepository.save(notification);
      }
}
