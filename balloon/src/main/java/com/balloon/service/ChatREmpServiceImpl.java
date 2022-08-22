package com.balloon.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.balloon.dto.ChatroomDTO;
import com.balloon.dto.ChatroomEmployeeDTO;
import com.balloon.dto.EmpDTO;
import com.balloon.entity.ChatroomEmployee;
import com.balloon.entity.ChatroomEmployeeId;
import com.balloon.entity.Employee;
import com.balloon.repository.ChatREmpRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatREmpServiceImpl implements ChatREmpService {

	private final ChatREmpRepository chatREmpRepository;

	@Override
	@Transactional
	public List<ChatroomEmployee> getallChatEmp() {
		return chatREmpRepository.findAll();
	}

	@Override
	public List<ChatroomEmployee> getChatroomEmp(Long chatroomId) {
		return chatREmpRepository.findAllByChatroomIdChatroomId(chatroomId);
	}

	@Override
	@Transactional
	public ChatroomEmployee getBotchatroom(String empId) {
		return chatREmpRepository.findChatroomEmployeeByempId(empId);
	}

	@Override
	@Transactional
	public Employee getInsertChatEmp(List<ChatroomEmployeeDTO> chatroomEmployeeDTO) {
		List<ChatroomEmployee> chatroomEmployeeEntity = new ArrayList<ChatroomEmployee>();

		for (ChatroomEmployeeDTO chatEmpDto : chatroomEmployeeDTO) {
			chatroomEmployeeEntity.add(chatEmpDto.toEntity(chatEmpDto));
		}

		chatREmpRepository.saveAll(chatroomEmployeeEntity);

//		ChatroomEmployee chatEmpEntity = 
//		Employee emp = chatREmpRepository.save(chatEmpEntity).getEmpId();
//		if (emp != null) {
//			Chatroom chatroomId = chatroomRepository.findById(chatroomEmployeeDTO.getChatroomId().getChatroomId())
//					.get();
		// 생성자 패턴 사용할 예정
		// 생성자 생성 후 처음 chatroomDTO.setChatroomId();만 해줄 시 값은 null임
		// set메소드로 ChatroomDTO에 있는 id,name,count값을 가져와서 넣어줌
//			ChatroomDTO chatroomDTO = new ChatroomDTO();
//			chatroomDTO.setChatroomId(chatroomId.getChatroomId());
//			chatroomDTO.setChatroomName(chatroomId.getChatroomName());
//			chatroomDTO.setHeadCount(chatroomId.getHeadCount() + 1);
		// 사람 많이 추가 시 Controller에서 List로 받아옴, List.size()만큼 head_Counter를 추가해준다
//			chatroomRepository.save(chatroomDTO.toEntity(chatroomDTO));
//	}return emp;
		return null;
	}

	@Override
	@Transactional
	public Employee getInsertSchChat(ChatroomEmployeeDTO chatroomEmployeeDTO) {

//		ChatroomDTO chatroomDTO = new ChatroomDTO();
//		Chatroom chatroomEntity = chatroomDTO.toEntity(chatroomDTO);
//
//		EmpDTO empDTO = new EmpDTO();
//		Employee empEntity = empDTO.toEntity(empDTO);

		ChatroomEmployee chatroomEmployeeEntity = chatroomEmployeeDTO.toEntity(chatroomEmployeeDTO);
		return chatREmpRepository.save(chatroomEmployeeEntity).getEmpId();

//		ChatroomEmployee chatroomEmployeeEntity = chatroomEmployeeDTO.toEntity(chatroomEmployeeDTO);
//		Employee emp = chatREmpRepository.save(chatroomEmployeeEntity).getEmpId();
//		if (emp != null) {
//			Chatroom chatroomId = chatroomRepository.findById(chatroomEmployeeDTO.getChatroomId().getChatroomId())
//					.get();
//			ChatroomDTO chatroomDTO = new ChatroomDTO();
//			chatroomDTO.setChatroomId(chatroomId.getChatroomId());
//			chatroomDTO.setChatroomName(chatroomId.getChatroomName());
//			chatroomDTO.setHeadCount(chatroomId.getHeadCount());
//
//			chatroomRepository.save(chatroomDTO.toEntity(chatroomDTO));
//			System.out.println(chatroomDTO);
//			System.out.println(chatroomEmployeeDTO);
//		}

//		return null;
	}

	@Override
	@Transactional
	public void getdeleteChatroom(Long chatroomId, String empId) {
		ChatroomEmployeeDTO chatroomEmployeeDTO = new ChatroomEmployeeDTO();
		ChatroomDTO chatroomDTO = new ChatroomDTO();
		chatroomDTO.setChatroomId(chatroomId);
		chatroomEmployeeDTO.setChatroomId(chatroomDTO);

		EmpDTO empDTO = new EmpDTO();
		empDTO.setEmpId(empId);
		chatroomEmployeeDTO.setEmpId(empDTO);

		ChatroomEmployeeId chatroomEmployeeIdEntity = chatroomEmployeeDTO.toId(chatroomEmployeeDTO);
		chatREmpRepository.deleteById(chatroomEmployeeIdEntity);

	}

}
