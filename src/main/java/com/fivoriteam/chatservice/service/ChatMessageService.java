package com.fivoriteam.chatservice.service;

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
        return  repository.save(chatMessage);
    }

    public long countNewMessages(String senderId, String recipientId) {
        return repository.countBySenderIdAndRecipientIdAndStatus(senderId, recipientId, "2");
    }

    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
        String chatId = chatRoomService.getChatId(senderId, recipientId, false);
        List<ChatMessage> messages = repository.findByChatId(chatId);
        if(messages.size() > 0 ) setStatusDelivered(senderId,recipientId,"2");
        return messages;
    }

    public ChatMessage getSinglemesaj(String id) {
        return repository.findAllById(id);
    }

    public ResponseEntity<?> setStatusChatMessage(String senderId, String recipientId,String status) {
        Query query = new Query(Criteria.where("senderId").is(senderId).and("recipientId").is(recipientId).and("status").in("1","2"));
        Update update = Update.update("status", status);
        mongoOperations.updateMulti(query, update, ChatMessage.class);
        return ResponseEntity.ok().build();
    }

    public void setStatusDelivered(String senderId, String recipientId,String status) {
        Query query = new Query(Criteria.where("senderId").is(senderId).and("recipientId").is(recipientId).and("status").is("1"));
        Update update = Update.update("status", status);
        mongoOperations.updateMulti(query, update, ChatMessage.class);
    }
}
