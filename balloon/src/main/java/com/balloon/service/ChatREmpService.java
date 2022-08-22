package com.balloon.service;

import java.util.List;

import com.balloon.dto.ChatroomEmployeeDTO;
import com.balloon.entity.ChatroomEmployee;
import com.balloon.entity.Employee;

public interface ChatREmpService {

	public List<ChatroomEmployee> getallChatEmp();

	public List<ChatroomEmployee> getChatroomEmp(Long chatroomId);

	public ChatroomEmployee getBotchatroom(String empId);

//	public Employee getInsertChatEmp(ChatroomEmployeeDTO chatroomEmployeeDTO);

	public void getdeleteChatroom(Long chatroomId, String empId);

	public Employee getInsertChatEmp(List<ChatroomEmployeeDTO> chatroomEmployeeDTO);

	public Employee getInsertSchChat(ChatroomEmployeeDTO chatroomEmployeeDTO);
}
