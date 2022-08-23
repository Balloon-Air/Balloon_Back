package com.balloon.service;

import java.util.ArrayList;
import java.util.List;

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
	public List<ApvlDTO> getApvlByBizRptId(String docId) {
		List<ApvlDTO> apvlDTOList = new ArrayList<ApvlDTO>();
		List<ApprovalList> approvalLists = ApvlRepo.findApprovalListByBusinessReportBusinessReportId(docId);
		for (ApprovalList approvalList : approvalLists) {
			apvlDTOList.add(approvalList.toDTO(approvalList));
		}
		return apvlDTOList;
	}

	@Override
	public List<ApvlDTO> getApvlByBizTpId(String docId) {
		List<ApvlDTO> apvlDTOList = new ArrayList<ApvlDTO>();
		List<ApprovalList> approvalLists = ApvlRepo.findApprovalListByBusinessTrip(docId);
		for (ApprovalList approvalList : approvalLists) {
			apvlDTOList.add(approvalList.toDTO(approvalList));
		}
		return apvlDTOList;
	}

	@Override
	public List<ApvlDTO> getApvlByPAId(String docId) {
		List<ApvlDTO> apvlDTOList = new ArrayList<ApvlDTO>();
		List<ApprovalList> approvalLists = ApvlRepo.findApprovalListByPersonnelAppointment(docId);
		for (ApprovalList approvalList : approvalLists) {
			apvlDTOList.add(approvalList.toDTO(approvalList));
		}
		return apvlDTOList;
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
