package com.aifia.chat.App.controller;


import com.aifia.chat.App.model.Conversations;
import com.aifia.chat.App.model.Messages;
import com.aifia.chat.App.repository.ConversationsRepository;
import com.aifia.chat.App.services.MessagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequiredArgsConstructor
public class ChatController {

    private final MessagesService messagesService;
    private final SimpMessagingTemplate messagingTemplate;
    private final ConversationsRepository conversationsRepository;

    @MessageMapping("/sendMessage")
    public void processMessage(
            @Payload Messages messages) {
        Conversations conversations = conversationsRepository.findById(messages.getConversationId()).get();
        String  recipientId = null;
        if (conversations.getType().equals("one-on-one")){
            for (String participant: conversations.getParticipants()){
                if (!participant.equals(messages.getSenderId())){
                        recipientId = participant;
                        break;
                }
            }

            if (recipientId != null) {
                messagingTemplate.convertAndSendToUser(recipientId, "/topic/private-messages", messages);
            }
        } else if (conversations.getType().equals("group")){
            messagingTemplate.convertAndSend("topic/group-messages-" + messages.getConversationId() , messages);
        }
        messagesService.save(messages);
    }





    @PostMapping(value = "messages/markAsSeen/{messageId}/{userId}")
    public ResponseEntity<?> markMessageAsRead(@PathVariable("messageId") String messageId,
                                               @PathVariable("userId") String userId) {
        messagesService.markMessageAsRead(messageId ,userId);
        return ResponseEntity.ok().build();
    }
}
