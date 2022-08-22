package com.balloon.api;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balloon.dto.ChatDTO;
import com.balloon.entity.Employee;
import com.balloon.service.ChatServiceImpl;
import com.balloon.service.EmpServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000" })
public class ChatRestController {

	private final EmpServiceImpl empSvc;
	private final ChatServiceImpl chatServiceImpl;

	@GetMapping(value = "/allEmp")
	public List<Employee> allEmp() {
		return empSvc.getEmp();
	}

	@GetMapping(value = "/allChat/{empId}")
	public List<ChatDTO> allChat(@PathVariable(name = "empId") Employee empId) {
		return chatServiceImpl.getChat(empId);
	}

	@GetMapping(value = "/chatRecord/{chatroomId}")
	public List<ChatDTO> chattingRecord(@PathVariable(value = "chatroomId") Long chatroomId) {
		return chatServiceImpl.getChatroomId(chatroomId);
	}

//	@PostMapping(value = "/insertChat/{chatroomId}")
//	public void createChatroom(@PathVariable(value = "chatroomId") Long chatroomId, @RequestBody ChatDTO chatDTO) {
//		ChatroomDTO chatroomDTO = new ChatroomDTO();
//		chatroomDTO.setChatroomId(chatroomId);
//		chatDTO.setChatroom(chatroomDTO.toEntity(chatroomDTO));
//		System.out.println(chatroomDTO);
//		System.out.println(chatDTO);
//
//		chatServiceImpl.getInsertChat(chatDTO);
//	}
}
