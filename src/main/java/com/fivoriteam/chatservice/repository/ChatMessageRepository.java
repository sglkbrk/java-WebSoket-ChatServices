package com.fivoriteam.chatservice.repository;

import com.fivoriteam.chatservice.model.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.data.domain.Pageable;
import java.util.List;


public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {


    long countBySenderIdAndRecipientIdAndStatus(String senderId, String recipientId, String status);
    List<ChatMessage> findByChatId(String chatId);
    List<ChatMessage> findAllBySenderIdAndRecipientIdAndStatus(String senderId, String recipientId, String status);
    ChatMessage findAllById(String id);

    List<ChatMessage>  findByChatId (String chatId, Pageable pageable);
}
