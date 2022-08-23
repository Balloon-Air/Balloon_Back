package com.balloon.service;

import java.util.List;

import com.balloon.dto.ChatDTO;
import com.balloon.entity.Employee;
import com.balloon.vo.MessageVO;

public interface ChatService {

	public List<ChatDTO> getChat(Employee empId);

	public List<ChatDTO> getChatroomId(Long chatroomId);

	public void insertChat(MessageVO messageVO);

}
