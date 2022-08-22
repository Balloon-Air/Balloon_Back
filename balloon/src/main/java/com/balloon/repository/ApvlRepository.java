package com.balloon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.balloon.entity.ApprovalList;

@Repository
public interface ApvlRepository extends JpaRepository<ApprovalList, Integer> {

	public ApprovalList findApprovalListByBusinessReportBusinessReportId(String BusinessReportId);

	public ApprovalList findApprovalListByBusinessTrip(String docId);

	public ApprovalList findApprovalListByPersonnelAppointment(String docId);

	public void deleteApprovalListByBusinessReportBusinessReportId(String docId);

	public void deleteApprovalListByBusinessTripBusinessTripId(String docId);

	public void deleteApprovalListByPersonnelAppointmentPersonnelAppointmentId(String docId);

}
