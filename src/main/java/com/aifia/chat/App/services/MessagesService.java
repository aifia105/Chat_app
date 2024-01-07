package com.aifia.chat.App.services;

import com.aifia.chat.App.model.Conversations;
import com.aifia.chat.App.model.Messages;
import com.aifia.chat.App.repository.ConversationsRepository;
import com.aifia.chat.App.repository.MessagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessagesService {

    private final MessagesRepository messagesRepository;
    private final ConversationsRepository conversationsRepository;


    public void save(Messages messages){
       Optional<Conversations> conversationsOptional = conversationsRepository.findById(messages.getConversationId());
       conversationsOptional.ifPresent(conversations -> {
           Messages newMessage = new Messages();
           newMessage.setConversationId(messages.getConversationId());
           newMessage.setSenderId(messages.getSenderId());
           newMessage.setContent(messages.getContent());
           newMessage.setTimestamp(LocalDateTime.now());
           newMessage.setRead(false);
           conversations.getMessagesList().add(newMessage);
           conversationsRepository.save(conversations);
           messagesRepository.save(newMessage);
       });
    }




    @Transactional
    public void markMessageAsRead(String messageId, String userId){
        Messages messages = messagesRepository.findById(messageId)
                .orElseThrow(()-> new RuntimeException("Message not found with id " + messageId));
        if (!messages.getSenderId().equals(userId)){
            throw new RuntimeException("User does not have permission to mark this message as read.");
        }
        messages.setRead(true);
        messages.setReadTime(LocalDateTime.now());
        messagesRepository.save(messages);
    }
}
