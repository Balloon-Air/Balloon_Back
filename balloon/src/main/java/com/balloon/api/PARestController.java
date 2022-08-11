package com.balloon.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balloon.dto.PADTO;
import com.balloon.service.PASvcImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PARestController {

	private final PASvcImpl PASvc;

	// CREATE -------------------------------
	@PostMapping(value = "/pa", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertPA(@RequestBody PADTO paDTO) {
		paDTO.setPersonnelAppointmentId(paDTO.getPersonnelAppointmentId());
		paDTO.setDocumentTitle(paDTO.getDocumentTitle());
		paDTO.setDocumentContent(paDTO.getDocumentContent());
		paDTO.setDocumentStatus((byte) 1);
		paDTO.setPersonnelDate(paDTO.getPersonnelDate());
		paDTO.setPosition(paDTO.getPosition());
		paDTO.setUnitName(paDTO.getUnitName());
		paDTO.setMovedEmpName(paDTO.getMovedEmpName());
		paDTO.setEmpName(paDTO.getEmpName());
		paDTO.setMovedEmpId(paDTO.getMovedEmpId());
		paDTO.setEmp(paDTO.getEmp());
		paDTO.setUnit(paDTO.getUnit());

		PASvc.insertPA(paDTO);
	}

	// READ ---------------------------------
	@GetMapping(value = "/pa/{PAId}")
	public PADTO getPAByEmpId(@PathVariable("PAId") String PAId) {
		return PASvc.getPAByPAId(PAId);
	}

	// UPDATE -------------------------------
	@PutMapping(value = "/pa/{PAId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updataPAByPAId(@PathVariable("PAId") String PAId, @RequestBody PADTO paDTO) {
		paDTO.setPersonnelAppointmentId(PAId);
		PASvc.insertPA(paDTO);
	}

	// DELETE -------------------------------
	@DeleteMapping(value = "/pa/{PAId}")
	public void deletePAByPAId(@PathVariable("PAId") String PAId) {
		PASvc.deletePAByPAId(PAId);
	}

}