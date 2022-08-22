package com.balloon.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.balloon.dto.PADTO;
import com.balloon.entity.PersonnelAppointment;
import com.balloon.repository.PARepository;
import com.balloon.vo.DocVO;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PASvcImpl implements PASvc {
	private final PARepository PARepo;

	@Override
	public void insertPA(PADTO paDTO) {
		PersonnelAppointment PAEntity = paDTO.toEntity(paDTO);
		PARepo.save(PAEntity);
	}

	@Override
	public List<DocVO> getDocbyEmpIdAndDocStatus(String empId, Byte docStatus) {
		String id = empId;
		Byte status = docStatus;
		List<DocVO> voList = new ArrayList<DocVO>();
		List<PersonnelAppointment> personnelAppointmentList = PARepo
				.findPersonnelAppointmentIdAndDocumentTitleAndUpdateDateByEmpEmpIdAndDocumentStatus(id, status);
		for (PersonnelAppointment personnelAppointment : personnelAppointmentList) {
			voList.add(personnelAppointment.toVO(personnelAppointment));
		}
		return voList;
	}

	@Override
	public List<DocVO> getDocbyUnitCode(String unitCode) {
		String code = unitCode.substring(0, 4);
		System.out.println(code);
		Byte status = 2;
		List<DocVO> voList = new ArrayList<DocVO>();
		List<PersonnelAppointment> personnelAppointmentList = PARepo
				.findPersonnelAppointmentIdAndDocumentTitleAndUpdateDateByUnitUnitCodeStartingWithAndDocumentStatus(
						code, status);
		for (PersonnelAppointment personnelAppointment : personnelAppointmentList) {
			voList.add(personnelAppointment.toVO(personnelAppointment));
		}
		return voList;
	}

	@Override
	public PADTO getPAByPAId(String PAId) {
		PersonnelAppointment personnelAppointment = PARepo.findPersonnelAppointmentByPersonnelAppointmentId(PAId);
		PADTO paDTO = personnelAppointment.toDTO(personnelAppointment);
		return paDTO;
	}

	@Override
	public void deletePAByPAId(String PAId) {
		PARepo.deleteById(PAId);
	}

	@Override
	public PADTO getPAWD() {
		PersonnelAppointment personnelAppointment = PARepo.findTopByOrderByWriteDateDesc();
		PADTO paDTO = personnelAppointment.toDTO(personnelAppointment);
		return paDTO;
	}

}
