package com.fivoriteam.chatservice.repository;

import com.fivoriteam.chatservice.model.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
    ChatRoom findBySenderIdAndRecipientId(String senderId, String recipientId);
}
