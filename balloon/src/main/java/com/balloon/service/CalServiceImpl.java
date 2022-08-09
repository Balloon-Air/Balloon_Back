package com.balloon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balloon.dto.CalDTO;
import com.balloon.entity.Cal;
import com.balloon.entity.Employee;
import com.balloon.repository.CalRepository;

@Service
public class CalServiceImpl implements CalService{
	@Autowired
	private CalRepository CalRepository;


	
	@Override
	public List<CalDTO> findAll() {
		List<Cal> calEntityList = CalRepository.findAll();
		List<CalDTO> calDTOList = new ArrayList<CalDTO>();
			
		calEntityList.forEach(calEntity -> calDTOList.add(calEntity.toDTO(calEntity)));
		
		return calDTOList;
	}



	@Override
	public Cal getCalByscheduleId(Long scheduleId) {
		
		return CalRepository.findAllByscheduleId(scheduleId);
	}



	@Override
	public void deleteByCalId(Long scheduleId) {
		CalRepository.deleteById(scheduleId);
	}



	@Override
	public void insertBycal(CalDTO calDTO) {
		Cal calEntity = calDTO.toEntity(calDTO);
		CalRepository.save(calEntity);
	}



	@Override
	public void updateByCal(CalDTO calDTO) {
		Cal cal = getCalByscheduleId(calDTO.getScheduleId());
		cal.updateCal(calDTO);
		CalRepository.save(cal);
	}



	@Override
	public List<CalDTO> getCalByempId(String empId) {
		
		
		CalDTO calDTO = new CalDTO();
		Employee employeeId = calDTO.toEmpId(empId);
		
		List<Cal> calEntityList = CalRepository.findAllByempId(employeeId);
		
		List<CalDTO> calDTOList = new ArrayList<CalDTO>();
			
		calEntityList.forEach(calEntity -> calDTOList.add(calEntity.toDTO(calEntity)));
		
		return calDTOList;
		
	}
	
	


	
	
}