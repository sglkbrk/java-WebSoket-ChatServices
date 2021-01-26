package com.fivoriteam.chatservice.service;

import com.fivoriteam.chatservice.model.ChatRoom;
import com.fivoriteam.chatservice.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatRoomService {

    @Autowired private ChatRoomRepository chatRoomRepository;

    public String getChatId(String senderId, String recipientId, boolean createIfNotExist) {
        ChatRoom chatRoom =  chatRoomRepository.findBySenderIdAndRecipientId(senderId, recipientId);
        if( createIfNotExist && chatRoom == null) {
            String chatId = String.format("%s_%s", senderId, recipientId);
            ChatRoom senderRecipient = new ChatRoom();
            senderRecipient.setChatId(chatId);
            senderRecipient.setSenderId(senderId);
            senderRecipient.setRecipientId(recipientId);
            chatRoomRepository.save(senderRecipient);
            ChatRoom recipientSender = new ChatRoom();
            recipientSender.setChatId(chatId);
            recipientSender.setSenderId(recipientId);
            recipientSender.setRecipientId(senderId);
            chatRoomRepository.save(recipientSender);
            return chatId;
        }else if(chatRoom != null) return  chatRoom.getChatId();
        else return "";
    }
}
