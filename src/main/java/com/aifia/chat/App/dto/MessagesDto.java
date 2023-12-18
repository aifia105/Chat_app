package com.aifia.chat.App.dto;

import com.aifia.chat.App.model.Messages;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class MessagesDto {

    private String id;
    private String chatId;
    private String senderId;
    private String recipientId;
    private String conversationId;
    private String content;
    private LocalDateTime timestamp;


    public static MessagesDto fromEntity(Messages messages){
        if(messages == null){
            return null;
        }
        return MessagesDto.builder()
                .id(messages.getId())
                .chatId(messages.getChatId())
                .senderId(messages.getSenderId())
                .recipientId(messages.getRecipientId())
                .conversationId(messages.getConversationId())
                .content(messages.getContent())
                .timestamp(messages.getTimestamp())
                .build();
    }

    public static Messages toEntity(MessagesDto messagesDto){
        if (messagesDto == null){
            return null;
        }
        Messages messages = new Messages();
        messages.setId(messagesDto.id);
        messages.setChatId(messagesDto.chatId);
        messages.setSenderId(messagesDto.senderId);
        messages.setRecipientId(messagesDto.recipientId);
        messages.setConversationId(messagesDto.conversationId);
        messages.setContent(messagesDto.content);
        messages.setTimestamp(messagesDto.timestamp);
        return messages;
    }
}
