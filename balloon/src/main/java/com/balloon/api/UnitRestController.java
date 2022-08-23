package com.balloon.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balloon.dto.UnitDTO;
import com.balloon.service.UnitServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/unit")
@RequiredArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000" }, allowedHeaders = "*")
public class UnitRestController {

	private final UnitServiceImpl unitSvc;

	@GetMapping(value = "/units")
	public List<UnitDTO> findUnits() throws Exception {
		try {
			List<UnitDTO> unitDTOList = unitSvc.findUnitAll();
			if (unitDTOList == null) {
				throw new Exception("존재하는 조직이 없습니다.");
			}
			return unitDTOList;

		} catch (Exception e) {
			throw new Exception("존재하는 조직이 없습니다.");
		}
	}

	@GetMapping(value = "/{unitCode}")
	public UnitDTO findUnitByUnitCode(@Valid @PathVariable String unitCode) throws Exception {
		try {
			if (unitCode == null) {
				throw new Exception("조직 번호를 입력받지 못습니다.");
			}
			UnitDTO unitDTO = unitSvc.findUnitByUnitCode(unitCode);
			return unitDTO;

		} catch (Exception e) {
			throw new Exception("조직 번호가 없습니다요.");
		}
	}

//	@GetMapping(value = "/childs/")
//	public UnitDTO findChildUnitByUnitCode(@RequestBody List<UnitDTO> unitDTOList) throws Exception {
//		try {
//			if (unitDTOList == null) {
//				throw new Exception("조직을 입력받지 못습니다.");
//			}
//			List<Unit> unitList = unitSvc.findChildUnitByUnitCode(unitDTOList);
//			if (unitList == null) {
//				throw new Exception("조직 번호가 존재하지 않습니다.");
//			}
//			return unitList.toDTO(unitList);
//
//		} catch (Exception e) {
//			throw new Exception("조직 번호가 없습니다요.");
//		}
//	}

	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertUnit(@Valid @RequestBody UnitDTO unitDTO) throws Exception {
		try {
			try {
				if (unitDTO == null) {
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

	@PostMapping(value = "/list", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Boolean insertUnitList(@Valid @RequestBody List<UnitDTO> unitDtoList) {
		try {
			if (unitDtoList == null) {
				throw new Exception("입력받은 값이 없습니다.");
			}
			boolean insertChk = unitSvc.insertUnitList(unitDtoList);
			return insertChk;
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	@PutMapping(value = "/change", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateUnit(@RequestBody UnitDTO unitDTO) throws Exception {
		try {
			try {
				if (unitDTO == null) {
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

	@DeleteMapping(value = "/{unitCode}")
	public void deleteUnitByUnitCode(@PathVariable String unitCode) throws Exception {
		try {
			if (unitCode == null) {
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
