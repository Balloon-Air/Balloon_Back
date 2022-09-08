package com.balloon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.balloon.entity.ApprovalList;

@Repository
public interface ApvlRepository extends JpaRepository<ApprovalList, Integer> {

	public List<ApprovalList> findApprovalListByBusinessReportBusinessReportId(String BusinessReportId);

	public List<ApprovalList> findApprovalListByBusinessTripBusinessTripId(String docId);

	public List<ApprovalList> findApprovalListByPersonnelAppointmentPersonnelAppointmentId(String docId);

	public List<ApprovalList> findApprovalListByApproverEmpEmpIdAndApprovalStatus(String apvrId, Byte docStatus);

	public ApprovalList findApprovalIdByBusinessReportBusinessReportIdAndApproverEmpEmpId(String docId,
			String approver);

	public ApprovalList findApprovalIdByBusinessTripBusinessTripIdAndApproverEmpEmpId(String docId, String approver);

	public ApprovalList findApprovalIdByPersonnelAppointmentPersonnelAppointmentIdAndApproverEmpEmpId(String docId,
			String approver);

	public void deleteApprovalListByBusinessReportBusinessReportIdAndApproverEmpEmpId(String docId, String empId);

	public void deleteApprovalListByBusinessTripBusinessTripIdAndApproverEmpEmpId(String docId, String empId);

	public void deleteApprovalListByPersonnelAppointmentPersonnelAppointmentIdAndApproverEmpEmpId(String docId,
			String empId);

	public ApprovalList findApprovalListByBusinessReportBusinessReportIdAndApproverName(String docId, String apvr);

}
