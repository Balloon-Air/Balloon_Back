package com.balloon.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balloon.dto.UnitDTO;
import com.balloon.entity.Unit;
import com.balloon.service.UnitServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4000"}, allowedHeaders = "*")
public class UnitRestController {

	private final UnitServiceImpl unitSvc;

	@GetMapping(value="/unit/units")
	public List<UnitDTO> findUnits() throws Exception {
		try {
			List<Unit> unitList = unitSvc.findUnitAll();
			if (unitList == null) {
				throw new Exception("존재하는 조직이 없습니다.");
			}
			List<UnitDTO> unitDTOList = new ArrayList<UnitDTO>();
			unitList.forEach(unitEntity -> unitDTOList.add(unitEntity.toDTO(unitEntity)));
			return unitDTOList;

		} catch (Exception e) {
			throw new Exception("존재하는 조직이 없습니다.");
		}
	}

	@GetMapping(value="/unit/{unitCode}")
	public UnitDTO findUnitByUnitCode(@PathVariable String unitCode) throws Exception{
		try {
			if (unitCode == null) {
				throw new Exception("조직 번호를 입력받지 못습니다.");
			}
			Unit unitEntity = unitSvc.findUnitByUnitCode(unitCode);
			if(unitEntity == null) {
				throw new Exception("조직 번호가 존재하지 않습니다.");
			}
			return unitEntity.toDTO(unitEntity);


		} catch (Exception e) {
			throw new Exception("조직 번호가 없습니다요.");
		}
	}

	@PostMapping(value = "/unit", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertUnit(@RequestBody UnitDTO unitDTO) throws Exception {
		try {
			try {
				if(unitDTO == null) {
					throw new Exception("입력받은 조직 추가 정보가 없습니다.");
				}
				unitSvc.insertUnit(unitDTO);

			} catch (Exception e) {
				throw new Exception("조직 번호가 존재합니다.");
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@PutMapping(value = "/unit", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateUnit(@RequestBody UnitDTO unitDTO) throws Exception {
		try {
			try {
				if(unitDTO == null) {
					throw new Exception("입력받은 조직 수정 정보가 없습니다.");
				} 
				unitSvc.updateUnit(unitDTO);

			} catch (Exception e) {
				throw new Exception("조직 번호가 존재하지 않습니다.");
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@DeleteMapping(value = "/unit/{unitCode}")
	public void deleteUnitByUnitCode(@PathVariable String unitCode) throws Exception {
		try {
			if(unitCode == null) {
				throw new Exception("입력받은 조직번호가 없습니다.");
			} 
			UnitDTO unitDTO = new UnitDTO();
			unitDTO = findUnitByUnitCode(unitCode);
			if (unitDTO == null) {
				throw new Exception("입력한 조직번호에 해당하는 조직이 존재하지 않습니다.");
			}
			unitSvc.deleteUnitByUnitCode(unitCode);


		} catch (Exception e) {
			e.getMessage();
		}
	}

}
