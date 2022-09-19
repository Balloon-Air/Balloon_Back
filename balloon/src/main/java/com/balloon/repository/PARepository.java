package com.balloon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.balloon.entity.PersonnelAppointment;

@Repository
public interface PARepository
		extends JpaRepository<PersonnelAppointment, String>, JpaSpecificationExecutor<PersonnelAppointment> {

	public PersonnelAppointment findPersonnelAppointmentByPersonnelAppointmentId(String personnelAppointmentId);

	public List<PersonnelAppointment> findPersonnelAppointmentIdAndDocumentTitleAndUpdateDateByEmpEmpIdAndDocumentStatus(
			String empId, Byte documentStatus);

	public List<PersonnelAppointment> findPersonnelAppointmentIdAndDocumentTitleAndUpdateDateByUnitUnitCodeStartingWithAndDocumentStatus(
			String unitCode, Byte documentStatus);

	public PersonnelAppointment findTopByOrderByWriteDateDesc();
}
