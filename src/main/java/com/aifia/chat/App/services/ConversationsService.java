package com.aifia.chat.App.services;


import com.aifia.chat.App.model.Conversations;
import com.aifia.chat.App.model.User;
import com.aifia.chat.App.repository.ConversationsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ConversationsService {

    private final ConversationsRepository conversationsRepository;


    public Conversations conversationOneOnOne(String id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String senderId =  user.getId();
        List<String> participants = List.of(id,senderId);
        Optional<Conversations> conversationsOptional = conversationsRepository.findByParticipantsAndType(participants, "one-on-one");
        if (conversationsOptional.isEmpty()){
            Conversations newConversation = new Conversations();
            newConversation.setType("one-on-one");
            newConversation.setParticipants(participants);
            conversationsRepository.save(newConversation);
            return newConversation;
        } else {
            return conversationsOptional.get();
        }
    }

    public Conversations conversationsGroup(List<String> participants){
        Conversations newConversation = new Conversations();
        newConversation.setType("group");
        newConversation.setParticipants(participants);
        conversationsRepository.save(newConversation);
        return newConversation;
    }

    public List<Conversations> getUserConversations(String id){
        if (id == null){
            throw new RuntimeException("user id is null");
        }
        return conversationsRepository.findByParticipantsIsContaining(id);
    }










}
