package com.balloon.vo;

import com.balloon.dto.ChatroomDTO;
import com.balloon.dto.EmpDTO;
import com.balloon.entity.Chat;
import com.balloon.entity.Chatroom;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MessageVO {

	String chatroomId;

//	String writer;

	EmpDTO writer;

	String chatContent;

	public Chat toChat(MessageVO messageDTO) {
		// ---------------chatRoomId 값을 entity로 변환해준 것-----------------------
		ChatroomDTO chatroomDTO = new ChatroomDTO();
		chatroomDTO.setChatroomId(Long.parseLong(messageDTO.getChatroomId()));
		Chatroom chatRoomId = chatroomDTO.toEntity(chatroomDTO);
		// --------------------------------------------------
		// ---------------EmployeeId 값을 entity로 변환해준 것-----------------------
//		EmpDTO employeeDTO = new EmpDTO();
//		employeeDTO.setEmpId(messageDTO.getWriter());
//		Employee employeeId = employeeDTO.toEntity(employeeDTO);

		// ---------------------------------------------
		Chat chatEntity = Chat.builder().chatroom(chatRoomId).chatContent(messageDTO.getChatContent())
				.employee(messageDTO.getWriter().toEntity(messageDTO.getWriter())).build();
		return chatEntity;
//		Chat chatEntity = Chat.builder().chatroom(chatRoomId).chatContent(messageDTO.getChatContent())
//				.employee(employeeId).build();
//		return chatEntity;
	}
}
