package com.balloon.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.balloon.dto.ChatroomDTO;
import com.balloon.entity.Chatroom;
import com.balloon.repository.ChatroomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatroomServiceImpl implements ChatroomService {

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
	public List<Chatroom> getCreateSchroom(List<ChatroomDTO> chatroomDTO) {
		List<Chatroom> chatroomEntity = new ArrayList<Chatroom>();
		for (ChatroomDTO chatroomDto : chatroomDTO) {
			chatroomEntity.add(chatroomDto.toEntity(chatroomDto));
		}
		return chatroomRepository.saveAll(chatroomEntity);
	}

	@Override
	@Transactional
	public void getUpdateChatroom(ChatroomDTO chatroomDTO) {
		Chatroom chatroomEntity = chatroomDTO.toEntity(chatroomDTO);
		chatroomEntity.updateEntity(chatroomDTO.getChatroomName(), chatroomDTO.getHeadCount());
		System.out.println(chatroomEntity);
		chatroomRepository.save(chatroomEntity);
	}

	@Override
	@Transactional
	public void deleteChatroom(Long chatroomId) {
		chatroomRepository.deleteById(chatroomId);
	}

}
