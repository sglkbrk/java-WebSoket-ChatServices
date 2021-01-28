package com.fivoriteam.chatservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSessionEntity {
    private String sesionId;
    private String userId;
    private String status;
    private String date;
}
