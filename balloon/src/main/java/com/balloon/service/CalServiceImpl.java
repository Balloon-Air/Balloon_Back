package com.balloon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.balloon.dto.CalDTO;
import com.balloon.entity.Cal;
import com.balloon.entity.Employee;
import com.balloon.repository.CalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CalServiceImpl implements CalService {

	private final CalRepository CalRepo;

	@Transactional(readOnly = true)
	@Override
	public List<CalDTO> findAll() {
		List<Cal> calEntityList = CalRepo.findAll();
		List<CalDTO> calDTOList = new ArrayList<CalDTO>();

		calEntityList.forEach(calEntity -> calDTOList.add(calEntity.toDTO(calEntity)));

		return calDTOList;
	}

	@Transactional(readOnly = true)
	@Override
	public CalDTO getCalByscheduleId(Long scheduleId) {
		Cal calEntity = CalRepo.findAllByscheduleId(scheduleId);
		CalDTO calDTO = new CalDTO();
		calDTO = calEntity.toDTO(calEntity);
		return calDTO;
	}

	@Transactional(readOnly = true)
	@Override
	public List<CalDTO> getCalByempId(String empId) {
		CalDTO calDTO = new CalDTO();
		Employee employeeId = calDTO.toEmpId(empId);

		List<Cal> calEntityList = CalRepo.findAllByempId(employeeId);
		List<CalDTO> calDTOList = new ArrayList<CalDTO>();

		calEntityList.forEach(calEntity -> calDTOList.add(calEntity.toDTO(calEntity)));

		return calDTOList;
	}

	@Transactional
	@Override
	public void insertBycal(CalDTO calDTO) {
		Cal calEntity = calDTO.toEntity(calDTO);
		CalRepo.save(calEntity);
	}

	@Transactional
	@Override
	public void scheduleListAdd(List<CalDTO> calDTOList) {
		List<Cal> calEntityList = new ArrayList<Cal>();
		for (CalDTO calDTO : calDTOList) {
			calEntityList.add(calDTO.toEntity(calDTO));
		}

		CalRepo.saveAll(calEntityList);
	}

	@Transactional
	@Override
	public void updateByCal(CalDTO requestDTO) throws Exception {
		System.out.println(requestDTO);
		CalDTO calDTO = new CalDTO();
		calDTO = getCalByscheduleId(requestDTO.getScheduleId());
		if (calDTO != null) {
			Cal cal = calDTO.toEntity(calDTO);
			cal.updateCal(requestDTO);
			CalRepo.save(cal);
		} else {
			throw new NullPointerException("scheduleId is null.");
		}
	}

	@Transactional
	@Override
	public void deleteByCalId(Long scheduleId) {
		CalRepo.deleteById(scheduleId);
	}
}