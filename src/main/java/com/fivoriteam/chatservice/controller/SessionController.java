package com.fivoriteam.chatservice.controller;

import com.fivoriteam.chatservice.entity.UserSessionEntity;
import com.fivoriteam.chatservice.model.UserSession;
import com.fivoriteam.chatservice.service.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class SessionController {

    @Autowired
    private UserSessionService userSesionService;

    @MessageMapping("/SaveSessionUser") //for saving current user's location in memory
    public void saveLocation(@Payload UserSessionEntity userSessionEntity, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("userId", userSessionEntity.getUserId());
        userSesionService.uploadUserSession(userSessionEntity.getUserId(),headerAccessor.getSessionId(),"1");
    }

    @PostMapping("/getUserSession")
    public List<UserSession> getUserSession (@RequestBody List<String> userIds) {
        return userSesionService.getUserSession(userIds);
    }

    @GetMapping ("/getUserSession/{userId}")
    public UserSession getFilterUserSession (@PathVariable String userId) {
        return userSesionService.getSingleUserSession(userId);
    }
}
