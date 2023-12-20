package com.aifia.chat.App.services;


import com.aifia.chat.App.model.Notification;
import com.aifia.chat.App.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;


      public List<Notification> getUserNotifications(String Userid) {
         return notificationRepository.findAllBySenderIdOrRecipientId(Userid,Userid);
      }

      @Transactional
      public void markNotificationAsRead(String notificationId, String userId) {
          Notification notification = notificationRepository.findById(notificationId)
                  .orElseThrow(()-> new RuntimeException("Notification with this id not found" + notificationId));
          if (notification.getRecipientId().equals(userId)){
              throw new RuntimeException("User does not have permission to mark this notification as read.");
          }
          notification.setRead(true);
          notification.setReadTime(LocalDateTime.now());
          notificationRepository.save(notification);

      }

      public void save(Notification notification) {
          notificationRepository.save(notification);
      }
}
