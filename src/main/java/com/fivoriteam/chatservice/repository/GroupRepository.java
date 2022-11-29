package com.fivoriteam.chatservice.repository;

import com.fivoriteam.chatservice.model.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GroupRepository extends MongoRepository<ChatRoom, String> {

}
