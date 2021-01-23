package com.fivoriteam.chatservice.repository;

import com.fivoriteam.chatservice.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    long countBySenderIdAndRecipientIdAndStatus(String senderId, String recipientId, String status);
    List<ChatMessage> findByChatId(String chatId);
    List<ChatMessage> findAllBySenderIdAndRecipientIdAndStatus(String senderId, String recipientId, String status);
    ChatMessage findAllById(Long id);
}
