
package com.fivoriteam.chatservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Group {
    @Id
    private String id;
    private String name;
    private String status;
    private String type;
    private Long count;
    private ChatMessage lastMsg;
    private String crdate;
    private String chdate;
}