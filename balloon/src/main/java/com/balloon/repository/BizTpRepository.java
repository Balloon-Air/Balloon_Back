package com.balloon.repository;

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

	public List<BusinessTripPlan> findBusinessTripIdAndDocumentTitleAndUpdateDateByUnitUnitCodeStartingWithAndDocumentStatus(
			String unitCode, Byte documentStatus);

	public BusinessTripPlan findTopByOrderByWriteDateDesc();

//	public BusinessTripPlan findBusinessTripPlanByEmpId(String empId);
}
