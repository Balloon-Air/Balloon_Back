package com.balloon.dto;

import java.time.LocalDateTime;

import com.balloon.entity.Chat;
import com.balloon.entity.Chatroom;
import com.balloon.entity.Employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ChatDTO {
	private Long chatId;
	private String chatContent;
	private LocalDateTime chatTime;
	private Long status;
	private Chatroom chatroom;
	private Employee employee;

	public Chat toEntity(ChatDTO chatDTO) {
		Chat chatEntity = Chat.builder().chatId(chatDTO.getChatId()).chatContent(chatDTO.getChatContent())
				.chatTime(chatDTO.getChatTime()).status(chatDTO.getStatus()).chatroom(chatDTO.getChatroom())
				.employee(chatDTO.getEmployee()).build();
		return chatEntity;
	}

}