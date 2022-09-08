package com.balloon.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.balloon.dto.ApvlDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "approval_list")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
public class ApprovalList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "approval_id")
	private Integer approvalId;

	@NotNull
	@Column(name = "approval_status", length = 1)
	private Byte approvalStatus;

	@Column(name = "approval_comment", length = 200)
	private String approvalComment;

	@NotNull
	@Column(name = "approver_name", length = 30)
	private String approverName;

	@NotNull
	@Column(name = "position", length = 20)
	private String position;

	@NotNull
	@Column(name = "drafter_name", length = 30)
	private String drafterName;

	@LastModifiedDate
	@Column(name = "process_date")
	private LocalDateTime processDate;

	@JsonIgnore
	@ManyToOne(targetEntity = Employee.class)
	@NotNull
	@JoinColumn(name = "drafter_emp_id")
	private Employee drafterEmp;

	@JsonIgnore
	@ManyToOne(targetEntity = Employee.class)
	@NotNull
	@JoinColumn(name = "approver_emp_id")
	private Employee approverEmp;

	@JsonIgnore
	@ManyToOne(targetEntity = BusinessReport.class)
	@JoinColumn(name = "business_report_id")
	private BusinessReport businessReport;

	@JsonIgnore
	@ManyToOne(targetEntity = BusinessTripPlan.class)
	@JoinColumn(name = "business_trip_id")
	private BusinessTripPlan businessTrip;

	@JsonIgnore
	@ManyToOne(targetEntity = PersonnelAppointment.class)
	@JoinColumn(name = "personnel_appointment_id")
	private PersonnelAppointment personnelAppointment;

	public ApvlDTO toDTO(ApprovalList approvalList) {
		ApvlDTO apvlDTO = ApvlDTO.builder().approvalId(approvalList.getApprovalId())
				.approvalStatus(approvalList.getApprovalStatus()).approvalComment(approvalList.getApprovalComment())
				.approverName(approvalList.getApproverName()).position(approvalList.getPosition())
				.drafterName(approvalList.getDrafterName()).processDate(approvalList.getProcessDate())
				.drafterEmp(approvalList.getDrafterEmp()).approverEmp(approvalList.getApproverEmp())
				.businessReport(approvalList.getBusinessReport()).businessTrip(approvalList.getBusinessTrip())
				.personnelAppointment(approvalList.getPersonnelAppointment()).build();
		return apvlDTO;
	}

	public void updateEntity(Byte approvalStatus, String approvalComment, String approverName, String position,
			String drafterName, Employee drafterEmp, Employee approverEmp, BusinessReport businessReport,
			BusinessTripPlan businessTrip, PersonnelAppointment personnelAppointment) {

		this.approvalStatus = approvalStatus;
		this.approvalComment = approvalComment;
		this.approverName = approverName;
		this.position = position;
		this.drafterName = drafterName;
		this.drafterEmp = drafterEmp;
		this.approverEmp = approverEmp;
		this.businessReport = businessReport;
		this.businessTrip = businessTrip;
		this.personnelAppointment = personnelAppointment;

	}

}
