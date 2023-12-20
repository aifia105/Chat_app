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
public class GroupConversation {

    @Id
    private String id;
    private String groupId;
    private String[] participants;
}
