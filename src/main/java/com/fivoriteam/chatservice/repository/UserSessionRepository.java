package com.fivoriteam.chatservice.repository;


import com.fivoriteam.chatservice.model.UserSession;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserSessionRepository extends MongoRepository<UserSession, String> {
    UserSession findFirstByUserId(String userid);
    List<UserSession> findAllByUserIdIn(List<String> userId);
}
