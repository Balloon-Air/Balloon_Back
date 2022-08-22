package com.balloon.service;

import java.util.List;

import com.balloon.dto.ChatroomDTO;
import com.balloon.entity.Chatroom;

public interface ChatroomService {

	public List<Chatroom> getAllChatroom();

	public Chatroom getOneChatroom(Long chatroomId);

	public Long getCreateChatroom(ChatroomDTO chatroomDTO);

	public List<Chatroom> getCreateSchroom(List<ChatroomDTO> chatroomDTO);

	public void getUpdateChatroom(ChatroomDTO chatroomDTO);

	public void deleteChatroom(Long chatroomId);

}
