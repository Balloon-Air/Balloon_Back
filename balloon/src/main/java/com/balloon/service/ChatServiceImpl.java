package com.balloon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.balloon.dto.ChatDTO;
import com.balloon.entity.Chat;
import com.balloon.entity.Employee;
import com.balloon.repository.ChatRepository;
import com.balloon.vo.MessageVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

	private final ChatRepository chatRepo;

	@Transactional(readOnly = true)
	@Override
	public List<ChatDTO> getChat(Employee empId) {
		List<Chat> chatEntityList = chatRepo.findAll(empId);
		List<ChatDTO> chatDTOList = new ArrayList<ChatDTO>();

		chatEntityList.forEach(chatEntity -> chatDTOList.add(chatEntity.toDTO(chatEntity)));

		return chatDTOList;
	}

	@Transactional(readOnly = true)
	@Override
	public List<ChatDTO> getChatroomId(Long chatroomId) {
		List<ChatDTO> ChatList = new ArrayList<ChatDTO>();
		for (Chat chat : chatRepo.findAllByChatroomChatroomId(chatroomId)) {
			ChatList.add(chat.toChatDTO(chat));
		}
		return ChatList;
	}


	// 채팅보내기
	@Transactional
	@Override
	public void insertChat(MessageVO messageVO) {
		Chat chat = messageVO.toChat(messageVO);
		chatRepo.save(chat);
	}

}
