package com.balloon.service;

import java.util.List;

import com.balloon.dto.UnitDTO;

public interface UnitService {
	public List<UnitDTO> findUnitAll();

	public UnitDTO findUnitByUnitCode(String unitCode) throws Exception;

	public void insertUnit(UnitDTO unitDTO);

	public boolean insertUnitList(List<UnitDTO> requestDtoList);

	public void updateUnit(UnitDTO unitDTO);

	public void deleteUnitByUnitCode(String unitCode);

}
