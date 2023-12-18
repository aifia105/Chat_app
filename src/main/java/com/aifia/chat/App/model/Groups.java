package com.aifia.chat.App.model;

import jakarta.validation.constraints.NotBlank;
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
public class Groups {

    @Id
    private String id;
    private String[] participants;
    @NotBlank(message = "group name cannot be empty")
    private String groupName;
    private LocalDateTime createdAt;
}
