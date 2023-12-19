package com.aifia.chat.App.services;


import com.aifia.chat.App.model.Conversations;
import com.aifia.chat.App.repository.ConversationsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ConversationsService {

    private final ConversationsRepository conversationsRepository;


    public Optional<String> getChatRoomId(
            String senderId,
            String recipientId,
            boolean createNewRoomIfNotExists
    ){
        return conversationsRepository.findBySenderIdAndRecipientId(senderId, recipientId)
                .map(Conversations::getChatId)
                .or(()->{
                    if (createNewRoomIfNotExists){
                        var chatId = createChatId(senderId, recipientId);
                        return Optional.of(chatId);
                    }
                    return Optional.empty();
                });
    }



    private String createChatId(String senderId,String recipientId){
        var chatId = String.format("%s_%s", senderId, recipientId);
        Conversations senderConversations = Conversations.builder()
                .chatId(chatId)
                .senderId(senderId)
                .recipientId(recipientId)
                .build();
        Conversations recipientConversations = Conversations.builder()
                .chatId(chatId)
                .senderId(recipientId)
                .recipientId(senderId)
                .build();
        conversationsRepository.save(senderConversations);
        conversationsRepository.save(recipientConversations);
        return chatId;
    }
}
