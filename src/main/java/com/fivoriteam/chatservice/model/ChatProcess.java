package com.fivoriteam.chatservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatProcess {
    private String msgId;
    private String chatId;
    private String senderId;
    private String recipientId;
    private String processType ;
}
