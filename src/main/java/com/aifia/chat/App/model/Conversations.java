package com.aifia.chat.App.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document
@Builder
public class Conversations {

    @Id
    private String id;
    @NotBlank
    private List<String> participants;
    private String type;
    private List<Messages> messagesList;

}
