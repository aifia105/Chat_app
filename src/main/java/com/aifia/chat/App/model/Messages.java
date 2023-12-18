package com.aifia.chat.App.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Messages {

    @Id
    private String id;
    private String chatId;
    private String senderId;
    private String recipientId;
    private String conversationId;
    private String content;
    private LocalDateTime timestamp;
}
