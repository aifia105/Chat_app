package com.aifia.chat.App.model;

import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    private String ConversationId;
    @NotBlank
    private String senderId;
    private String content;
    private LocalDateTime timestamp;
    private boolean read;
    private LocalDateTime readTime;
}
