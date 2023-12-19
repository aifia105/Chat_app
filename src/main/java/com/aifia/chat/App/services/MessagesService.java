package com.aifia.chat.App.services;

import com.aifia.chat.App.model.Messages;
import com.aifia.chat.App.repository.MessagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessagesService {

    private final MessagesRepository messagesRepository;
    private final ConversationsService conversationsService;


    public Messages save(Messages messages){
        var chatId = conversationsService.getChatRoomId(
                messages.getSenderId(),
                messages.getRecipientId(),
                true
        ).orElse("message information invalid");
        messages.setChatId(chatId);
        messagesRepository.save(messages);
        return messages;
    }

    public List<Messages> getConversation(
            String senderId,
            String recipientId
    ) {
        var chatId = conversationsService.getChatRoomId(
                senderId,
                recipientId,
                false);
        return chatId.map(messagesRepository::findByChatId).orElse(new ArrayList<>());
    }


    @Transactional
    public void markMessageAsRead(String messageId, String userId){
        Messages messages = messagesRepository.findById(messageId)
                .orElseThrow(()-> new RuntimeException("Message not found with id " + messageId));
        if (!messages.getRecipientId().equals(userId)){
            throw new RuntimeException("User does not have permission to mark this message as read.");
        }
        messages.setRead(true);
        messages.setReadTime(LocalDateTime.now());
        messagesRepository.save(messages);
    }
}
