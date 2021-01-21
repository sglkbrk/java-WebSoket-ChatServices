package com.fivoriteam.chatservice.service;

import com.fivoriteam.chatservice.exception.ResourceNotFoundException;
import com.fivoriteam.chatservice.model.ChatMessage;
import com.fivoriteam.chatservice.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatMessageService {
    @Autowired private ChatMessageRepository repository;
    @Autowired private ChatRoomService chatRoomService;
    @Autowired private MongoOperations mongoOperations;

    public ChatMessage save(ChatMessage chatMessage) {
        chatMessage.setStatus("1");
        ChatMessage chatMessage2 = repository.save(chatMessage);
        return  chatMessage2;
    }

    public long countNewMessages(String senderId, String recipientId) {
        return repository.countBySenderIdAndRecipientIdAndStatus(senderId, recipientId, "1");
    }

    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
        var chatId = chatRoomService.getChatId(senderId, recipientId, false);
        var messages = repository.findByChatId(chatId);
        if(messages.size() > 0) {
            deliveredChatMessage(senderId,recipientId);
        }
        return messages;
    }

    public ChatMessage findById(Long id) {
        return repository
                .findById(id)
                .map(chatMessage -> {
                    chatMessage.setStatus("2");
                    return repository.save(chatMessage);
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException("can't find message (" + id + ")"));
    }
    public ResponseEntity<ChatMessage> seenChatMessage(String senderId, String recipientId) {
       List<ChatMessage>  chatMessages = repository.findAllBySenderIdAndRecipientIdAndStatus(senderId,recipientId,"2");
        for (ChatMessage chatMessage : chatMessages) {
            chatMessage.setStatus("3");
        }
        repository.saveAll(chatMessages);
        return ResponseEntity.ok().build();
    }

    public void deliveredChatMessage(String senderId, String recipientId) {
        List<ChatMessage>  chatMessages = repository.findAllBySenderIdAndRecipientIdAndStatus(senderId,recipientId,"1");
        for (ChatMessage chatMessage : chatMessages) {
            chatMessage.setStatus("2");
        }
        repository.saveAll(chatMessages);
    }
}
