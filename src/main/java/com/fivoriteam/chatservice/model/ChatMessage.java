package com.fivoriteam.chatservice.model;

import com.bol.secure.Encrypted;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class ChatMessage {
   @Id
   private String id;
   private String chatId;
   private String senderId;
   private String recipientId;
   @Field
   @Encrypted
   private String senderName;
   @Field
   @Encrypted
   private String recipientName;
   @Field
   @Encrypted
   private String content;
   private Date timestamp;
   private String status;
   private String type;
   @Field
   @Encrypted
   private String fileurl;
   private String filesize;
}



