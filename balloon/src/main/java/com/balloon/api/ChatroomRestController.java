package com.balloon.api;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balloon.dto.ChatroomDTO;
import com.balloon.entity.Chatroom;
import com.balloon.service.ChatroomServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000" })
public class ChatroomRestController {

//	@Autowired
	private final ChatroomServiceImpl chatroomServiceImpl;

	// 채팅방 목록 조회
	@GetMapping(value = "/allChatroom")
	public List<Chatroom> allChatroom() {
		return chatroomServiceImpl.getAllChatroom();
	}

	// 채팅방 조회
	@GetMapping(value = "/oneChatroom/{chatroomId}")
	public Chatroom oneChatroom(@PathVariable Long chatroomId) {
		return chatroomServiceImpl.getOneChatroom(chatroomId);
	}

	// 채팅방 개설
	@PostMapping(value = "/createChatroom")
	public Long createChatroom(@RequestBody ChatroomDTO chatroomDTO) {
		System.out.println(chatroomDTO);
		return chatroomServiceImpl.getCreateChatroom(chatroomDTO);
	}

	// 일정을 공유할 사원만큼 채팅방을 생성하는 코드
	@PostMapping(value = "/createSchChatroom")
	public List<Chatroom> createSchChatroom(@RequestBody List<ChatroomDTO> chatroomDTO) {
		System.out.println(chatroomDTO);
		return chatroomServiceImpl.getCreateSchroom(chatroomDTO);
	}

	@PutMapping(value = "/updateroom/{chatroomId}")
	public void updateChatroom(@PathVariable(value = "chatroomId") Long chatroomId,
			@RequestBody ChatroomDTO chatroomDTO) {
		chatroomDTO.setChatroomId(chatroomId);
		chatroomServiceImpl.getUpdateChatroom(chatroomDTO);
	}

	@DeleteMapping(value = "/deleteChatroom/{chatroomId}")
	public void deleteChatroom(@PathVariable Long chatroomId) {
		chatroomServiceImpl.deleteChatroom(chatroomId);
	}
}
