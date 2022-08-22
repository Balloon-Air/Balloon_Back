package com.balloon.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.balloon.dto.ApvlDTO;
import com.balloon.entity.ApprovalList;
import com.balloon.repository.ApvlRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ApvlSvcImpl implements ApvlSvc {
	private final ApvlRepository ApvlRepo;

	@Override
	public void insertApvl(ApvlDTO apvlDTO) {
		ApprovalList approvalList = apvlDTO.toEntity(apvlDTO);
		ApvlRepo.save(approvalList);
	}

	@Override
	public ApvlDTO getApvlByBizRptId(String docId) {
		ApprovalList approvalList = ApvlRepo.findApprovalListByBusinessReportBusinessReportId(docId);
		ApvlDTO apvlDTO = approvalList.toDTO(approvalList);
		return apvlDTO;
	}

	@Override
	public ApvlDTO getApvlByBizTpId(String docId) {
		ApprovalList approvalList = ApvlRepo.findApprovalListByBusinessTrip(docId);
		ApvlDTO apvlDTO = approvalList.toDTO(approvalList);
		return apvlDTO;
	}

	@Override
	public ApvlDTO getApvlByPAId(String docId) {
		ApprovalList approvalList = ApvlRepo.findApprovalListByPersonnelAppointment(docId);
		ApvlDTO apvlDTO = approvalList.toDTO(approvalList);
		return apvlDTO;
	}

	@Override
	public void deleteApvlByBizRptId(String docId) {
		ApvlRepo.deleteApprovalListByBusinessReportBusinessReportId(docId);
	}

	@Override
	public void deleteApvlByBizTpId(String docId) {
		ApvlRepo.deleteApprovalListByBusinessTripBusinessTripId(docId);
	}

	@Override
	public void deleteApvlByPAId(String docId) {
		ApvlRepo.deleteApprovalListByPersonnelAppointmentPersonnelAppointmentId(docId);
	}

}
