package com.fivoriteam.chatservice.controller;

import com.fivoriteam.chatservice.model.ChatMessage;
import com.fivoriteam.chatservice.model.ChatNotification;
import com.fivoriteam.chatservice.model.ChatProcess;
import com.fivoriteam.chatservice.service.ChatMessageService;
import com.fivoriteam.chatservice.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ChatController {

    @Autowired private SimpMessagingTemplate messagingTemplate;
    @Autowired private ChatMessageService chatMessageService;
    @Autowired private ChatRoomService chatRoomService;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        var chatId = chatRoomService.getChatId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true);
        chatMessage.setChatId(chatId);
        ChatMessage saved = chatMessageService.save(chatMessage);
        messagingTemplate.convertAndSendToUser(chatMessage.getRecipientId(),"/queue/messages", chatMessage);
    }

    @MessageMapping("/writingmessage")
    public void writingmessage(@Payload ChatProcess chatProcess) {
        var chatId = chatRoomService.getChatId(chatProcess.getSenderId(), chatProcess.getRecipientId(), true);
        messagingTemplate.convertAndSendToUser(chatProcess.getRecipientId(),"/queue/writing", chatProcess );

    }
    @MessageMapping("/seenmessage")
    public void seenmessage(@Payload ChatProcess chatProcess) {
        var chatId = chatRoomService.getChatId(chatProcess.getSenderId(), chatProcess.getRecipientId(), true);
        messagingTemplate.convertAndSendToUser(chatProcess.getRecipientId(),"/queue/seen", chatProcess );

    }

    @GetMapping("/messages/{senderId}/{recipientId}/count")
    public ResponseEntity<Long> countNewMessages(
            @PathVariable String senderId,
            @PathVariable String recipientId) {

        return ResponseEntity.ok(chatMessageService.countNewMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<?> findChatMessages ( @PathVariable String senderId, @PathVariable String recipientId) {
        return ResponseEntity.ok(chatMessageService.findChatMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<?> findMessage ( @PathVariable long id) {
        return ResponseEntity.ok(chatMessageService.findById(id));
    }
    //    Mesaj Görüldü
    @GetMapping("/seenChatMessage/{senderId}/{recipientId}")
    public ResponseEntity<?> seenChatMessage ( @PathVariable String senderId, @PathVariable String recipientId) {
        return chatMessageService.seenChatMessage(senderId, recipientId);
    }




}