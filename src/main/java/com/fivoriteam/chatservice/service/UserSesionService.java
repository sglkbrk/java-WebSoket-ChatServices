package com.fivoriteam.chatservice.service;

import com.fivoriteam.chatservice.model.UserSession;
import com.fivoriteam.chatservice.repository.UserSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserSesionService {

    @Autowired private UserSessionRepository userSesionRepository;

    public void uploadUserSession(String userId,String sessionId,String status){
        UserSession us =  userSesionRepository.findFirstByUserId(userId);
        if(us == null){
            UserSession userSession1 = UserSession.builder()
                    .sesionId(sessionId)
                    .userId(userId)
                    .status(status)
                    .date(new Date().toString()).build();
            userSesionRepository.save(userSession1);
        }else {
            if(sessionId != null){
                us.setSesionId(sessionId);
                us.setStatus(status);
                us.setDate(new Date().toString());
                userSesionRepository.save(us);
            }
        }
    }

    public List<UserSession> getUserSession(List<String> userids){
        return userSesionRepository.findAllByUserIdIn(userids);
    }
}
