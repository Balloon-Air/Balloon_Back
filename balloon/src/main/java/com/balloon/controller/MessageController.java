package com.balloon.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.balloon.service.ChatServiceImpl;
import com.balloon.vo.MessageVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MessageController {
	// 특정 broker로 메세지를 전달
	private final SimpMessagingTemplate template;
	private final ChatServiceImpl chatSvc;

	@MessageMapping(value = "/chat/message")
	public void message(@Payload MessageVO messageVO) {
		System.out.println(messageVO);
		System.out.println("연결성공");
		chatSvc.insertChat(messageVO);
		template.convertAndSend("/topic/message", messageVO);
	}

}