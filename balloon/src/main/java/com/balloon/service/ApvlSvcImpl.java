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
		List<ApprovalList> approvalLists = ApvlRepo.findApprovalListByBusinessTripBusinessTripId(docId);
		for (ApprovalList approvalList : approvalLists) {
			apvlDTOList.add(approvalList.toDTO(approvalList));
		}
		return apvlDTOList;
	}

	@Override
	public List<ApvlDTO> getApvlByPAId(String docId) {
		List<ApvlDTO> apvlDTOList = new ArrayList<ApvlDTO>();
		List<ApprovalList> approvalLists = ApvlRepo.findApprovalListByPersonnelAppointmentPersonnelAppointmentId(docId);
		for (ApprovalList approvalList : approvalLists) {
			apvlDTOList.add(approvalList.toDTO(approvalList));
		}
		return apvlDTOList;
	}

	@Override
	public ApvlDTO getApvlIdByBizRptIdAndApvrId(String docId, String approver) {
		ApprovalList approvalList = ApvlRepo.findApprovalIdByBusinessReportBusinessReportIdAndApproverEmpEmpId(docId,
				approver);
		if (approvalList != null) {
			ApvlDTO apvlDTO = approvalList.toDTO(approvalList);
			return apvlDTO;
		} else {
			return null;
		}
	}

	@Override
	public ApvlDTO getApvlIdByBizTpIdAndApvrId(String docId, String approver) {
		ApprovalList approvalList = ApvlRepo.findApprovalIdByBusinessTripBusinessTripIdAndApproverEmpEmpId(docId,
				approver);
		if (approvalList != null) {
			ApvlDTO apvlDTO = approvalList.toDTO(approvalList);
			return apvlDTO;
		} else {
			return null;
		}
	}

	@Override
	public ApvlDTO getApvlIdByPAIdAndApvrId(String docId, String approver) {
		ApprovalList approvalList = ApvlRepo
				.findApprovalIdByPersonnelAppointmentPersonnelAppointmentIdAndApproverEmpEmpId(docId, approver);
		if (approvalList != null) {
			ApvlDTO apvlDTO = approvalList.toDTO(approvalList);
			return apvlDTO;
		} else {
			return null;
		}
	}

	@Override
	public List<ApvlDTO> getApvlByApproverIdAndDocStatus(String apvrId, Byte docStatus) {
		List<ApvlDTO> apvlDTOList = new ArrayList<ApvlDTO>();
		List<ApprovalList> approvalLists = ApvlRepo.findApprovalListByApproverEmpEmpIdAndApprovalStatus(apvrId,
				docStatus);
		for (ApprovalList approvalList : approvalLists) {
			apvlDTOList.add(approvalList.toDTO(approvalList));
		}
		return apvlDTOList;
	}

//	@Override
//	public void updateApvlByBizRptIdAndApvr(ApvlDTO apvlDTO) {
//		
//		ApprovalList approval = apvlDTO.toEntity(apvlDTO);
//		ap
////				ApvlRepo.findApprovalListByBusinessReportBusinessReportIdAndApproverName(docId, apvr);
//		apvlDTO = approval.toDTO(approval);
//	}

	@Override
	public void updateApvl(ApvlDTO apvlDTO) {
		ApprovalList approvalList = apvlDTO.toEntity(apvlDTO);
//		approvalList.updateEntity(apvlDTO.getApprovalStatus(), apvlDTO.getApprovalComment(), apvlDTO.getApproverName(),
//				apvlDTO.getPosition(), apvlDTO.getDrafterName(), apvlDTO.getEmp(), apvlDTO.getBusinessReport(),
//				apvlDTO.getBusinessTrip(), apvlDTO.getPersonnelAppointment());
		ApvlRepo.save(approvalList);
	}

	@Override
	public void deleteApvlByBizRptId(String docId, String empId) {
		ApvlRepo.deleteApprovalListByBusinessReportBusinessReportIdAndApproverEmpEmpId(docId, empId);
	}

	@Override
	public void deleteApvlByBizTpId(String docId, String empId) {
		ApvlRepo.deleteApprovalListByBusinessTripBusinessTripIdAndApproverEmpEmpId(docId, empId);
	}

	@Override
	public void deleteApvlByPAId(String docId, String empId) {
		ApvlRepo.deleteApprovalListByPersonnelAppointmentPersonnelAppointmentIdAndApproverEmpEmpId(docId, empId);
	}

}
