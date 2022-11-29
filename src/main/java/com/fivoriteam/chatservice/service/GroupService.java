package com.fivoriteam.chatservice.service;

import com.fivoriteam.chatservice.model.ChatMessage;
import com.fivoriteam.chatservice.model.ChatRoom;
import com.fivoriteam.chatservice.repository.ChatMessageRepository;
import com.fivoriteam.chatservice.repository.ChatRoomRepository;
import com.fivoriteam.chatservice.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired private GroupRepository groupRepository;



}
