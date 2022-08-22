package com.balloon.api;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balloon.dto.ChatroomDTO;
import com.balloon.dto.ChatroomEmployeeDTO;
import com.balloon.entity.ChatroomEmployee;
import com.balloon.entity.Employee;
import com.balloon.service.ChatREmpServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000" })
public class ChatREmpRestController {

	private final ChatREmpServiceImpl chatREmpServicImpl;

	@GetMapping(value = "/allChatEmp")
	public List<ChatroomEmployee> allChatEmp() {
		return chatREmpServicImpl.getallChatEmp();
	}

	@GetMapping(value = "/oneChatEmp/{chatroomId}")
	public List<ChatroomEmployee> chatroomEmp(@PathVariable(value = "chatroomId") Long chatroomId) {
		return chatREmpServicImpl.getChatroomEmp(chatroomId);
	}

	@GetMapping(value = "/botChatroom/{empId}")
	public ChatroomEmployee calendarBot(@PathVariable(value = "empId") String empId) {
		return chatREmpServicImpl.getBotchatroom(empId);
	}

	@PostMapping(value = "/insertChatEmp/{chatroomId}")
	public Employee insertChatEmpList(@PathVariable(name = "chatroomId") Long chatroomId,
			@RequestBody List<ChatroomEmployeeDTO> chatroomEmployeeDTO) {
		// 채팅방 id 값 넣기
		ChatroomDTO chatroomDTO = new ChatroomDTO();
		chatroomDTO.setChatroomId(chatroomId);

		// 모든 사원 리스트
		for (ChatroomEmployeeDTO chatroomEmpDto : chatroomEmployeeDTO) {
			chatroomEmpDto.setChatroomId(chatroomDTO);
		}
		return chatREmpServicImpl.getInsertChatEmp(chatroomEmployeeDTO);
	}

	// 일정등록 시 chatroomEmployee에 만들어진 chatroomId와 empId를 넣어주는 메소드
	@PostMapping(value = "/insertSch/{chatroomId}")
	public Employee insertChatEmp(@PathVariable(name = "chatroomId") Long chatroomId,
			@RequestBody ChatroomEmployeeDTO chatroomEmployeeDTO) {

		ChatroomDTO chatroomDTO = new ChatroomDTO();
		chatroomDTO.setChatroomId(chatroomId);
//		EmpDTO empDTO = new EmpDTO();
//		empDTO.setEmpId(empId);
		chatroomEmployeeDTO.setChatroomId(chatroomDTO);
//		ChatroomEmployeeDTO chatroomEmpDTO = new ChatroomEmployeeDTO();
//		chatroomEmpDTO.setChatroomId(chatroomDTO);
//		chatroomEmpDTO.setEmpId(empDTO);
//		System.out.println(chatroomDTO);
//		System.out.println(chatroomEmpDTO);
		System.out.println(chatroomEmployeeDTO);

		return chatREmpServicImpl.getInsertSchChat(chatroomEmployeeDTO);
//		return null;
	}

	@DeleteMapping(value = "/deleteroom/{chatroomId}/{empId}")
	public void deleteChat(@PathVariable("chatroomId") Long chatroomId, @PathVariable("empId") String empId) {
		chatREmpServicImpl.getdeleteChatroom(chatroomId, empId);
	}

}
