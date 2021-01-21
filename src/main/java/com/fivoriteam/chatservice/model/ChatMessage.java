package com.fivoriteam.chatservice.model;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "ChatMessage")
public class ChatMessage {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name="id")
   private Long id;
   private String chatId;
   private String senderId;
   private String recipientId;
   private String senderName;
   private String recipientName;
   private String content;
   private Date timestamp;
   private String status;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getChatId() {
      return chatId;
   }

   public void setChatId(String chatId) {
      this.chatId = chatId;
   }

   public String getSenderId() {
      return senderId;
   }

   public void setSenderId(String senderId) {
      this.senderId = senderId;
   }

   public String getRecipientId() {
      return recipientId;
   }

   public void setRecipientId(String recipientId) {
      this.recipientId = recipientId;
   }

   public String getSenderName() {
      return senderName;
   }

   public void setSenderName(String senderName) {
      this.senderName = senderName;
   }

   public String getRecipientName() {
      return recipientName;
   }

   public void setRecipientName(String recipientName) {
      this.recipientName = recipientName;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   public Date getTimestamp() {
      return timestamp;
   }

   public void setTimestamp(Date timestamp) {
      this.timestamp = timestamp;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }
}



