package com.fivoriteam.chatservice.repository;

import com.fivoriteam.chatservice.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    ChatRoom findBySenderIdAndRecipientId(String senderId, String recipientId);
}
