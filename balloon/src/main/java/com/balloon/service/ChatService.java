package com.balloon.service;

import java.util.List;

import com.balloon.dto.ChatDTO;
import com.balloon.entity.Employee;
import com.balloon.vo.MessageVO;

public interface ChatService {

   public List<ChatDTO> getChat(Employee empId);

	public List<ChatDTO> getChatroomInfo(Long chatroomId, String empId);

	public void insertChats(List<MessageVO> messageVO);

   public void insertChat(MessageVO messageVO);

}

