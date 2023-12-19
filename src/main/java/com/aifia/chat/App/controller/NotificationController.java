package com.aifia.chat.App.controller;


import com.aifia.chat.App.model.Notification;
import com.aifia.chat.App.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;


    @GetMapping(value = "/notification/{id}")
    public ResponseEntity<List<Notification>> getUserNotification(@PathVariable String id) {
        return ResponseEntity.ok(notificationService.getUserNotifications(id));
    }
}
