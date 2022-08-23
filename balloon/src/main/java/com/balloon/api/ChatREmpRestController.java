package com.balloon.api;

import java.util.List;

import org.springframework.http.MediaType;
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
@RequestMapping("/cre")
@RequiredArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000" })
public class ChatREmpRestController {

	private final ChatREmpServiceImpl chatREmpServicImpl;

	@GetMapping(value = "/allchatemp")
	public List<ChatroomEmployee> allChatEmp() {
		return chatREmpServicImpl.getallChatEmp();
	}

	@GetMapping(value = "/onechatemp/{chatroomId}")
	public List<ChatroomEmployee> chatroomEmp(@PathVariable(value = "chatroomId") Long chatroomId) {
		return chatREmpServicImpl.getChatroomEmp(chatroomId);
	}

	@PostMapping(value = "/botchatroom", consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<ChatroomEmployee> calendarBot(@RequestBody List<String> empIds) {

		return chatREmpServicImpl.getBotchatroom(empIds);
	}

	@PostMapping(value = "/insertchatemp/{chatroomId}")
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

	@DeleteMapping(value = "/deleteroom/{chatroomId}/{empId}")
	public void deleteChat(@PathVariable("chatroomId") Long chatroomId, @PathVariable("empId") String empId) {
		chatREmpServicImpl.getdeleteChatroom(chatroomId, empId);
	}

}