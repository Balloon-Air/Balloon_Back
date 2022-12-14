package com.balloon.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.balloon.entity.BusinessTripPlan;

@Repository
public interface BizTpRepository
		extends JpaRepository<BusinessTripPlan, String>, JpaSpecificationExecutor<BusinessTripPlan> {

	public BusinessTripPlan findBusinessTripPlanByBusinessTripId(String businessTripId);

	public List<BusinessTripPlan> findBusinessTripIdAndDocumentTitleAndUpdateDateByEmpEmpIdAndDocumentStatus(
			String empId, Byte documentStatus);

	public List<BusinessTripPlan> findBusinessTripIdAndDocumentTitleAndUpdateDateByEmpEmpIdAndDocumentStatusAndWriteDateBetween(
			String empId, Byte documentStatus, LocalDateTime sunDay, LocalDateTime saturDay);

	public List<BusinessTripPlan> findBusinessTripIdAndDocumentTitleAndUpdateDateByUnitUnitCodeStartingWithAndDocumentStatus(
			String unitCode, Byte documentStatus);

	public BusinessTripPlan findTopByOrderByBusinessTripIdDesc();

}
