package com.balloon.api;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balloon.dto.ChatDTO;
import com.balloon.entity.Employee;
import com.balloon.service.ChatServiceImpl;
import com.balloon.vo.MessageVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000" })
//@CrossOrigin(origins = { "http://15.164.224.26:8080" })
public class ChatRestController {

	private final ChatServiceImpl chatSvc;

	// 마지막으로 보낸 채팅내용 -> 채팅방리스트
	@GetMapping(value = "/allchat/{empId}")
	public List<ChatDTO> allChat(@PathVariable(name = "empId") Employee empId) {
		return chatSvc.getChat(empId);
	}

	// 채팅방 기록남기기
	@GetMapping(value = "/chatrecord/{chatroomId}/{empId}")
	public List<ChatDTO> chattingRecord(@PathVariable(value = "chatroomId") Long chatroomId,
			@PathVariable(value = "empId") String empId) {
		return chatSvc.getChatroomInfo(chatroomId, empId);
	}


	// list형태로 DB에 채팅을 담기위해서
	@PostMapping(value = "/messages")
	public void messages(@RequestBody List<MessageVO> messageVOList) {
		System.out.println(messageVOList);
		System.out.println("연결성공");
		chatSvc.insertChats(messageVOList);
	}

}

