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
@RequestMapping("/chatroom")
@RequiredArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000" })
public class ChatroomRestController {

	private final ChatroomServiceImpl chatroomSvc;

	// 채팅방 목록 조회
	@GetMapping(value = "/allchatroom")
	public List<Chatroom> allChatroom() {
		return chatroomSvc.getAllChatroom();
	}

	// 채팅방 조회
	@GetMapping(value = "/onechatroom/{chatroomId}")
	public Chatroom onechatroom(@PathVariable Long chatroomId) {
		return chatroomSvc.getOneChatroom(chatroomId);
	}

	// 채팅방 개설
	@PostMapping(value = "/createchatroom")
	public Long createchatroom(@RequestBody ChatroomDTO chatroomDTO) {
		System.out.println(chatroomDTO);
		return chatroomSvc.getCreateChatroom(chatroomDTO);
	}


	// 일정을 공유할 사원만큼 채팅방을 생성하는 코드
	@PostMapping(value = "/createschchatroom")
	public List<Chatroom> createSchChatroom(@RequestBody List<ChatroomDTO> chatroomDTOList) {
		System.out.println(chatroomDTOList);
		return chatroomSvc.getCreateSchroom(chatroomDTOList);
	}

	@PutMapping(value = "/updatechatroom/{chatroomId}")
	public void updateChatroom(@PathVariable(value = "chatroomId") Long chatroomId,
			@RequestBody ChatroomDTO chatroomDTO) {
		chatroomDTO.setChatroomId(chatroomId);
		chatroomSvc.getUpdateChatroom(chatroomDTO);
	}

	@DeleteMapping(value = "/deletechatroom/{chatroomId}")
	public void deleteChatroom(@PathVariable Long chatroomId) {
		chatroomSvc.deleteChatroom(chatroomId);
	}
}
