package com.fivoriteam.chatservice.controller;

import com.fivoriteam.chatservice.model.UserSession;
import com.fivoriteam.chatservice.service.UserSesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class SessionController {

    @Autowired
    private UserSesionService userSesionService;

    @MessageMapping("/SaveSesionUser") //for saving current user's location in memory
    public void saveLocation(@Payload UserSession userSession, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("userId", userSession.getUserId());
        userSesionService.uploadUserSession(userSession.getUserId(),headerAccessor.getSessionId(),"1");
    }

    @PostMapping("/getUserSession")
    public List<UserSession> getUserSession (@RequestBody List<String> userIds) {
        return userSesionService.getUserSession(userIds);
    }
}
