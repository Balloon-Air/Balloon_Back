package com.balloon.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balloon.dto.ChatroomDTO;
import com.balloon.entity.Chatroom;
import com.balloon.repository.ChatroomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatroomServiceImpl implements ChatroomService{
	
	private final ChatroomRepository chatroomRepository;
	
	@Override
	@Transactional
	public List<Chatroom> getAllChatroom() {
		return chatroomRepository.findAll();
	}
	
	@Override
	@Transactional
	public Chatroom getOneChatroom(Long chatroomId) {
		return chatroomRepository.findChatroomByChatroomId(chatroomId);
	}

	@Override
	@Transactional
	public Long getCreateChatroom(ChatroomDTO chatroomDTO) {
		Chatroom chatroomEntity = chatroomDTO.toEntity(chatroomDTO);
		return chatroomRepository.save(chatroomEntity).getChatroomId();
	}

	@Override
	@Transactional
	public void getUpdateChatroom(ChatroomDTO chatroomDTO) {
		Chatroom chatroomEntity = chatroomDTO.toEntity(chatroomDTO);
		chatroomEntity.updateEntity(chatroomDTO.getChatroomName(), chatroomDTO.getHeadCount());
		chatroomRepository.save(chatroomEntity);
	}

	@Override
	@Transactional
	public void deleteChatroom(Long chatroomId) {
		chatroomRepository.deleteById(chatroomId);
	}

}
