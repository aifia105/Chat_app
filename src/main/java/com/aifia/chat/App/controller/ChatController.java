package com.aifia.chat.App.controller;


import com.aifia.chat.App.model.Messages;
import com.aifia.chat.App.model.Notification;
import com.aifia.chat.App.services.MessagesService;
import com.aifia.chat.App.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final MessagesService messagesService;
    private final SimpMessagingTemplate messagingTemplate;
    private final NotificationService notificationService;

    @MessageMapping("/chat")
    public void processMessage(
            @Payload Messages messages
    ) {
        Messages savedMsg = messagesService.save(messages);
        Notification notification = Notification.builder()
                             .id(savedMsg.getId())
                             .senderId(savedMsg.getSenderId())
                             .recipientId(savedMsg.getRecipientId())
                             .content(savedMsg.getContent())
                    .build();
        notificationService.save(notification);
        messagingTemplate.convertAndSendToUser(
                messages.getRecipientId(),"/queue/messages", notification
        );
    }



    @GetMapping(value = "/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<Messages>> findConversation(
            @PathVariable("senderId") String senderId,
            @PathVariable("recipientId") String recipientId
    ) {
        return ResponseEntity.ok(messagesService.getConversation(senderId, recipientId));
    }

    @PostMapping(value = "messages/markAsSeen/{messageId}/{userId}")
    public ResponseEntity<?> markMessageAsRead(@PathVariable("messageId") String messageId,
                                               @PathVariable("userId") String userId) {
        messagesService.markMessageAsRead(messageId ,userId);
        return ResponseEntity.ok().build();
    }
}
