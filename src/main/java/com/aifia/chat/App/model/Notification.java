package com.aifia.chat.App.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {

    @Id
    private String id;
    private String senderId;
    private String recipientId;
    private String content;
    private boolean read;
    private LocalDateTime readTime;
}
