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

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000" })
public class ChatRestController {

	private final ChatServiceImpl chatSvc;

	@GetMapping(value = "/allchat/{empId}")
	public List<ChatDTO> allChat(@PathVariable(name = "empId") Employee empId) {
		return chatSvc.getChat(empId);
	}

	@GetMapping(value = "/chatrecord/{chatroomId}")
	public List<ChatDTO> chattingRecord(@PathVariable(value = "chatroomId") Long chatroomId) {
		return chatSvc.getChatroomId(chatroomId);
	}

}
