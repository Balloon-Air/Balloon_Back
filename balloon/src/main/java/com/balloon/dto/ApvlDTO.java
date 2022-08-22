package com.balloon.dto;

import java.time.LocalDateTime;

import com.balloon.entity.ApprovalList;
import com.balloon.entity.BusinessReport;
import com.balloon.entity.BusinessTripPlan;
import com.balloon.entity.Employee;
import com.balloon.entity.PersonnelAppointment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

public class ApvlDTO {
	private Integer approvalId;

	private Byte approvalStatus;

	private String approvalComment;

	private String approverName;

	private String position;

	private String drafterName;

	private LocalDateTime processDate;

	private Employee emp;

	private BusinessReport businessReport;

	private BusinessTripPlan businessTrip;

	private PersonnelAppointment personnelAppointment;

	public ApprovalList toEntity(ApvlDTO apvlDTO) {
		ApprovalList approvalList = ApprovalList.builder().approvalId(apvlDTO.getApprovalId())
				.approvalStatus(apvlDTO.getApprovalStatus()).approvalComment(apvlDTO.getApprovalComment())
				.approverName(apvlDTO.getApproverName()).position(apvlDTO.getPosition())
				.drafterName(apvlDTO.getDrafterName()).processDate(apvlDTO.getProcessDate()).emp(apvlDTO.getEmp())
				.businessReport(apvlDTO.getBusinessReport()).businessTrip(apvlDTO.getBusinessTrip())
				.personnelAppointment(apvlDTO.getPersonnelAppointment()).build();
		return approvalList;
	}

}
