package com.aifia.chat.App.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document
@Builder
public class Conversations {

    @Id
    private String id;
    private String chatId;
    private String senderId;
    private String recipientId;

}
