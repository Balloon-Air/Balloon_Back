package com.balloon.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.balloon.entity.Employee;
import com.balloon.entity.PersonnelAppointment;
import com.balloon.entity.Unit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

public class PADTO {
	private String personnelAppointmentId;

	private String documentTitle;

	private String documentContent;

	private Byte documentStatus;

	private LocalDate personnelDate;

	private String position;

	private String unitName;

	private String movedEmpName;

	private String empName;

	private LocalDateTime writeDate;

	private LocalDateTime updateDate;

	private Employee movedEmp;

	private Employee emp;

	private Unit unit;

	public PersonnelAppointment toEntity(PADTO paDTO) {
		PersonnelAppointment personnelAppointment = PersonnelAppointment.builder()
				.personnelAppointmentId(paDTO.getPersonnelAppointmentId()).documentTitle(paDTO.getDocumentTitle())
				.documentContent(paDTO.getDocumentContent()).documentStatus(paDTO.getDocumentStatus())
				.personnelDate(paDTO.getPersonnelDate()).position(paDTO.getPosition()).unitName(paDTO.getUnitName())
				.movedEmpName(paDTO.getMovedEmpName()).empName(paDTO.getEmpName()).writeDate(paDTO.getWriteDate())
				.updateDate(paDTO.getUpdateDate()).movedEmp(paDTO.getMovedEmp()).emp(paDTO.getEmp())
				.unit(paDTO.getUnit()).build();

		return personnelAppointment;
	}
}
