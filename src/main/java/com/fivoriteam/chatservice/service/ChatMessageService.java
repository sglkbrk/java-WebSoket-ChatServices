package com.fivoriteam.chatservice.service;

import com.fivoriteam.chatservice.model.ChatMessage;
import com.fivoriteam.chatservice.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatMessageService {
    @Autowired private ChatMessageRepository repository;
    @Autowired private ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage chatMessage) {
        return  repository.save(chatMessage);
    }

    public long countNewMessages(String senderId, String recipientId) {
        return repository.countBySenderIdAndRecipientIdAndStatus(senderId, recipientId, "1");
    }

    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
        String chatId = chatRoomService.getChatId(senderId, recipientId, false);
        List<ChatMessage> messages = repository.findByChatId(chatId);
        if(messages.size() > 0) {
//            deliveredChatMessage(senderId,recipientId);
        }
        return messages;
    }
    public ChatMessage getSinglemesaj(String id) {
        return repository.findAllById(id);
    }
    public ResponseEntity<?> seenChatMessage(String senderId, String recipientId) {
       List<ChatMessage>  chatMessages = repository.findAllBySenderIdAndRecipientIdAndStatus(senderId,recipientId,"2");
        for (ChatMessage chatMessage : chatMessages) {
            chatMessage.setStatus("3");
        }
        repository.saveAll(chatMessages);
        return ResponseEntity.ok().build();
    }

    public void deliveredChatMessage(String senderId, String recipientId) {
        List<ChatMessage>  chatMessages = repository.findAllBySenderIdAndRecipientIdAndStatus(senderId,recipientId,"1");
        for (ChatMessage chatMessage : chatMessages) {
            chatMessage.setStatus("2");
        }
        repository.saveAll(chatMessages);
    }

}
