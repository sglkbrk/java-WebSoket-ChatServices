package com.fivoriteam.chatservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatProcess {
    private String id;
    private String msgId;
    private String chatId;
    private String senderId;
    private String recipientId;
    private String processType ;
}
