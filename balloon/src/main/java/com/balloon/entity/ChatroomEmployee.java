package com.balloon.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.balloon.dto.ChatroomEmployeeDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "chatroom_employee")
@IdClass(ChatroomEmployeeId.class)
public class ChatroomEmployee {

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chatroom_id")
	private Chatroom chatroomId;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "emp_id")
	private Employee empId;

	public ChatroomEmployeeDTO toDTO(ChatroomEmployee chatroomEmployeeEntity) {
		Chatroom chatroom = chatroomEmployeeEntity.getChatroomId();
		Employee employee = chatroomEmployeeEntity.getEmpId();
		ChatroomEmployeeDTO chatroomEmployeeDTO = ChatroomEmployeeDTO.builder().chatroomId(chatroom.toDTO(chatroom))
				.empId(employee.toDTO(employee)).build();
		return chatroomEmployeeDTO;
	}

}
