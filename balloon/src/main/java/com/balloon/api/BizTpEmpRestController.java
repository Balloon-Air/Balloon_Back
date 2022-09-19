package com.balloon.api;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balloon.dto.BizTpEmpDTO;
import com.balloon.service.BizTpEmpSvcImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BizTpEmpRestController {

	private final BizTpEmpSvcImpl BizTpEmpSvc;

	// CREATE -------------------------------
	@PostMapping(value = "/biztpemp", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertBizTpEmp(@RequestBody BizTpEmpDTO bizTpEmpDTO) {
		bizTpEmpDTO.setBusinessTrip(bizTpEmpDTO.getBusinessTrip());
		bizTpEmpDTO.setEmp(bizTpEmpDTO.getEmp());

		BizTpEmpSvc.insertBizTpEmp(bizTpEmpDTO);

	}

	// READ -------------------------------
	@GetMapping(value = "/biztpemp/{bizTpId}")
	public List<BizTpEmpDTO> getBizTpEmpByBizTpId(@PathVariable("bizTpId") String bizTpId) {
		return BizTpEmpSvc.getBizTpEmpByBizTpId(bizTpId);
	}

	// UPDATE -------------------------------

	// DELETE -------------------------------

}
