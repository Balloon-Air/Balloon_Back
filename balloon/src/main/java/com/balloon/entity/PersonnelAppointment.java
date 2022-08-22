package com.balloon.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.balloon.dto.PADTO;
import com.balloon.vo.DocVO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "personnel_appointment")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)

public class PersonnelAppointment {

	@Id
	@Column(name = "personnel_appointment_id", length = 20)
	private String personnelAppointmentId;

	@NotNull
	@Column(name = "document_title", length = 45)
	private String documentTitle;

	@Column(name = "document_content", length = 2000)
	private String documentContent;

	@NotNull
	@Column(name = "document_status", length = 1)
	private Byte documentStatus;

	@NotNull
	@Column(name = "personnel_date")
	private LocalDate personnelDate;

	@NotNull
	@Column(name = "position", length = 20)
	private String position;

	@NotNull
	@Column(name = "unit_name", length = 20)
	private String unitName;

	@NotNull
	@Column(name = "moved_emp_name", length = 30)
	private String movedEmpName;

	@NotNull
	@Column(name = "emp_name", length = 30)
	private String empName;

	@CreatedDate
	@Column(name = "write_date", updatable = false)
	private LocalDateTime writeDate;

	@LastModifiedDate
	@Column(name = "update_date")
	private LocalDateTime updateDate;

	@JsonIgnore
	@ManyToOne(targetEntity = Employee.class)
	@JoinColumn(name = "moved_emp_id")
	private Employee movedEmpId;

	@JsonIgnore
	@ManyToOne(targetEntity = Employee.class)
	@JoinColumn(name = "emp_id")
	private Employee emp;

	@JsonIgnore
	@ManyToOne(targetEntity = Unit.class)
	@JoinColumn(name = "unit_code")
	private Unit unit;

	public PADTO toDTO(PersonnelAppointment personnelAppointment) {
		PADTO paDTO = PADTO.builder().personnelAppointmentId(personnelAppointment.getPersonnelAppointmentId())
				.documentTitle(personnelAppointment.getDocumentTitle())
				.documentContent(personnelAppointment.getDocumentContent())
				.documentStatus(personnelAppointment.getDocumentStatus())
				.personnelDate(personnelAppointment.getPersonnelDate()).position(personnelAppointment.getPosition())
				.unitName(personnelAppointment.getUnitName()).movedEmpName(personnelAppointment.getMovedEmpName())
				.empName(personnelAppointment.getEmpName()).writeDate(personnelAppointment.getWriteDate())
				.updateDate(personnelAppointment.getUpdateDate()).movedEmpId(personnelAppointment.getMovedEmpId())
				.emp(personnelAppointment.getEmp()).unit(personnelAppointment.getUnit()).build();

		return paDTO;
	}

	public DocVO toVO(PersonnelAppointment personnelAppointment) {
//		DocVO docVO = DocVO.builder().docId(personnelAppointment.getPersonnelAppointmentId())
//				.documentTitle(personnelAppointment.getDocumentTitle()).updateTime(personnelAppointment.getUpdateDate())
//				.documentStatus(personnelAppointment.getDocumentStatus()).unit(personnelAppointment.getUnit()).build();
		DocVO docVO = DocVO.builder().docId(personnelAppointment.getPersonnelAppointmentId())
				.documentTitle(personnelAppointment.getDocumentTitle()).updateTime(personnelAppointment.getUpdateDate())
				.build();
		return docVO;
	}
}
