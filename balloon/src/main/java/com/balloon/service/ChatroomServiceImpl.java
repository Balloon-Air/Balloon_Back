package com.balloon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.balloon.dto.ChatroomDTO;
import com.balloon.entity.Chatroom;
import com.balloon.repository.ChatroomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatroomServiceImpl implements ChatroomService {

	private final ChatroomRepository chatroomRepo;

	@Transactional(readOnly = true)
	@Override
	public List<Chatroom> getAllChatroom() {
		return chatroomRepo.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Chatroom getOneChatroom(Long chatroomId) {
		return chatroomRepo.findChatroomByChatroomId(chatroomId);
	}

	@Transactional
	@Override
	public Long getCreateChatroom(ChatroomDTO chatroomDTO) {
		Chatroom chatroomEntity = chatroomDTO.toEntity(chatroomDTO);
		return chatroomRepo.save(chatroomEntity).getChatroomId();
	}

	@Transactional
	@Override
	public List<Chatroom> getCreateSchroom(List<ChatroomDTO> chatroomDTOList) {
		List<Chatroom> chatroomEntityList = new ArrayList<Chatroom>();
		for (ChatroomDTO chatroomDTO : chatroomDTOList) {
			chatroomEntityList.add(chatroomDTO.toEntity(chatroomDTO));
		}
		return chatroomRepo.saveAll(chatroomEntityList);
	}

	@Transactional
	@Override
	public void getUpdateChatroom(ChatroomDTO chatroomDTO) {
		Chatroom chatroomEntity = chatroomDTO.toEntity(chatroomDTO);
		chatroomEntity.updateEntity(chatroomDTO.getChatroomName(), chatroomDTO.getHeadCount());
		System.out.println(chatroomEntity);
		chatroomRepo.save(chatroomEntity);
	}

	@Transactional
	@Override
	public void deleteChatroom(Long chatroomId) {
		chatroomRepo.deleteById(chatroomId);
	}

}
