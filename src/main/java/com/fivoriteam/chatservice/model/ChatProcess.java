package com.fivoriteam.chatservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatProcess {
    private Long msgId;
    private String chatId;
    private String senderId;
    private String recipientId;
    private String processType ;
}
