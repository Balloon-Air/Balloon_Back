package com.balloon.service;

import java.util.List;

import com.balloon.dto.CalDTO;

public interface CalService {

	public List<CalDTO> findAll();

	public CalDTO getCalByscheduleId(Long scheduleId);

	public List<CalDTO> getCalByempId(String empId);

	public void insertBycal(CalDTO calDTO);

	public void scheduleListAdd(List<CalDTO> calDTOs);

	public void updateByCal(CalDTO requestDTO) throws Exception;

	public void deleteByCalId(Long scheduleId);

}
