package com.aifia.chat.App.controller;

import com.aifia.chat.App.model.Conversations;
import com.aifia.chat.App.services.ConversationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ConversationController {

    private final ConversationsService conversationsService;


    @PostMapping(value = "conversation/one-on-one/{id}")
    public ResponseEntity<Conversations> conversationOneOnOne(@PathVariable String id) {
        return ResponseEntity.ok(conversationsService.conversationOneOnOne(id));
    }

    @PostMapping(value = "conversation/group")
    public ResponseEntity<Conversations> conversationGroup(@RequestBody List<String> participants) {
        return ResponseEntity.ok(conversationsService.conversationsGroup(participants));
    }

    @GetMapping(value = "conversation/{id}")
    public ResponseEntity<List<Conversations>> getUserConversation(@PathVariable String id) {
        return ResponseEntity.ok(conversationsService.getUserConversations(id));
    }
}
