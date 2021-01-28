package com.fivoriteam.chatservice.controller;

import com.fivoriteam.chatservice.model.ChatRoom;
import com.fivoriteam.chatservice.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

@ResponseBody
@Controller
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;


    @GetMapping ("/getUserChatRoom/{userId}")
    public List<ChatRoom> getUserChatRoom (@PathVariable String userId) {
        return chatRoomService.getUserChatRoom(userId);
    }


}
