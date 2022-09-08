package com.balloon.dto;

import java.time.LocalDateTime;

import com.balloon.entity.ChatroomEmployee;
import com.balloon.entity.ChatroomEmployeeId;

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
public class ChatroomEmployeeDTO {

   private ChatroomDTO chatroomId;
   private EmpDTO empId;
   private LocalDateTime exitTime;
	 private LocalDateTime inTime;

	public ChatroomEmployee toEntity(ChatroomEmployeeDTO chatroomEmployeeDTO) {
		ChatroomDTO chatroomDTO = chatroomEmployeeDTO.getChatroomId();
		EmpDTO employeeDTO = chatroomEmployeeDTO.getEmpId();
		ChatroomEmployee chatroomEmployeeEntity = ChatroomEmployee.builder()
				.chatroomId(chatroomDTO.toEntity(chatroomDTO)).empId(employeeDTO.toEntity(employeeDTO))
				.exitTime(chatroomEmployeeDTO.getExitTime()).inTime(chatroomEmployeeDTO.getInTime()).build();
		return chatroomEmployeeEntity;
	}


   public ChatroomEmployeeId toId(ChatroomEmployeeDTO chatroomEmployeeDTO) {
      ChatroomDTO chatroomDTO = chatroomEmployeeDTO.getChatroomId();
      EmpDTO employeeDTO = chatroomEmployeeDTO.getEmpId();
      ChatroomEmployeeId chatroomEmployeeIdEntity = ChatroomEmployeeId.builder()
            .chatroomId(chatroomDTO.toEntity(chatroomDTO).getChatroomId())
            .empId(employeeDTO.toEntity(employeeDTO).getEmpId()).build();
      return chatroomEmployeeIdEntity;
   }

}