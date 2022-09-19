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
import com.balloon.service.ChatroomServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/chatroom")
@RequiredArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000" })
//@CrossOrigin(origins = {"http://15.164.224.26:8080"})

public class ChatroomRestController {

	private final ChatroomServiceImpl chatroomSvc;

	// 채팅방 목록 조회
	@GetMapping(value = "/allchatroom")
	public List<ChatroomDTO> allChatroom() {
		return chatroomSvc.getAllChatroom();
	}

	// 채팅방 조회
	@GetMapping(value = "/onechatroom/{chatroomId}")
	public ChatroomDTO onechatroom(@PathVariable Long chatroomId) {
		return chatroomSvc.getOneChatroom(chatroomId);
	}

	// 채팅방 개설
	@PostMapping(value = "/createchatroom")
	public Long createchatroom(@RequestBody ChatroomDTO chatroomDTO) {
		return chatroomSvc.getCreateChatroom(chatroomDTO);
	}

	// 일정을 공유할 사원만큼 채팅방을 생성하는 코드
	@PostMapping(value = "/createschchatroom")
	public List<ChatroomDTO> createSchChatroom(@RequestBody List<ChatroomDTO> chatroomDTO) {
		return chatroomSvc.getCreateSchroom(chatroomDTO);
	}

	// 채팅방 이름 수정
	@PutMapping(value = "/updatechatroom/{chatroomId}")
	public void updateChatroom(@PathVariable(value = "chatroomId") Long chatroomId,
			@RequestBody ChatroomDTO chatroomDTO) {
		chatroomDTO.setChatroomId(chatroomId);
		chatroomSvc.getUpdateChatroom(chatroomDTO);
	}

	// headCount가 1일 때 채팅방 삭제
	@DeleteMapping(value = "/deletechatroom/{chatroomId}")
	public void deleteChatroom(@PathVariable Long chatroomId) {
		chatroomSvc.deleteChatroom(chatroomId);
	}
}