package com.fivoriteam.chatservice.service;

import com.fivoriteam.chatservice.model.ChatRoom;
import com.fivoriteam.chatservice.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatRoomService {

    @Autowired private ChatRoomRepository chatRoomRepository;

    public String getChatId1(String senderId, String recipientId, boolean createIfNotExist) {

        ChatRoom chatRoom =  chatRoomRepository.findBySenderIdAndRecipientId(senderId, recipientId);
        if( createIfNotExist && chatRoom == null) {
            var chatId = String.format("%s_%s", senderId, recipientId);
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

    public String getChatId(String senderId, String recipientId, boolean createIfNotExist) {

        ChatRoom toChatRoom =  chatRoomRepository.findBySenderIdAndRecipientId(senderId, recipientId);
        ChatRoom FromchatRoom =  chatRoomRepository.findBySenderIdAndRecipientId(recipientId, senderId);
        if(createIfNotExist && toChatRoom != null && FromchatRoom != null ){
            toChatRoom.setStatus("1");
            FromchatRoom.setStatus("1");
            chatRoomRepository.save(toChatRoom);
            chatRoomRepository.save(FromchatRoom);
            return toChatRoom.getChatId();
        }else if( createIfNotExist  && toChatRoom == null && FromchatRoom == null) {
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
        }else if( toChatRoom != null && FromchatRoom != null) return  toChatRoom.getChatId();
        else return "";
    }
}
