package com.balloon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.balloon.entity.BusinessTripEmployee;
import com.balloon.entity.BusinessTripEmployeeId;

@Repository
public interface BizTpEmpRepository extends JpaRepository<BusinessTripEmployee, BusinessTripEmployeeId> {

	List<BusinessTripEmployee> findEmpByBusinessTripBusinessTripId(String bizTpId);

}
