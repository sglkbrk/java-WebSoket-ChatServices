package com.fivoriteam.chatservice.service;

import com.fivoriteam.chatservice.model.ChatMessage;
import com.fivoriteam.chatservice.model.ChatRoom;
import com.fivoriteam.chatservice.repository.ChatMessageRepository;
import com.fivoriteam.chatservice.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatRoomService {

    @Autowired private ChatRoomRepository chatRoomRepository;
    @Autowired private ChatMessageRepository chatMessageRepository;

    public String getChatId(String senderId, String recipientId, boolean createIfNotExist) {
        ChatRoom chatRoom =  chatRoomRepository.findBySenderIdAndRecipientId(senderId, recipientId);
        if( createIfNotExist && chatRoom == null) {
            String chatId = String.format("%s_%s", senderId, recipientId);
            ChatRoom senderRecipient = new ChatRoom();
            senderRecipient.setChatId(chatId);
            senderRecipient.setSenderId(senderId);
            senderRecipient.setRecipientId(recipientId);
            senderRecipient.setType("S");
            chatRoomRepository.save(senderRecipient);
            ChatRoom recipientSender = new ChatRoom();
            recipientSender.setChatId(chatId);
            recipientSender.setSenderId(recipientId);
            recipientSender.setRecipientId(senderId);
            recipientSender.setType("S");
            chatRoomRepository.save(recipientSender);
            return chatId;
        }else if(chatRoom != null) return  chatRoom.getChatId();
        else return "";
    }

    public List<ChatRoom> getUserChatRoom(String userId){
        Pageable pageable = PageRequest.of(0, 1, Sort.by("timestamp").descending());
        List<ChatRoom> chatRooms = chatRoomRepository.findAllBySenderId(userId);

        for (ChatRoom chatromm: chatRooms) {
            List<ChatMessage>  chatMessage = chatMessageRepository.findByChatId(chatromm.getChatId(),pageable);
            if(chatMessage.size() > 0 ) {
                chatromm.setLastMsg(chatMessage.get(0));
                chatromm.setCount(chatMessageRepository.countBySenderIdAndRecipientIdAndStatusIsNot(chatromm.getRecipientId(),chatromm.getSenderId(), "3"));
            }
        }
        return chatRooms;
    }
}
