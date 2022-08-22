package com.balloon.dto;

import com.balloon.entity.BusinessTripEmployee;
import com.balloon.entity.BusinessTripEmployeeId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BizTpEmpDTO {

	private BizTpDTO businessTrip;

	private EmpDTO emp;

	public BusinessTripEmployee toEntity(BizTpEmpDTO businessTripEmployeeDTO) {
		BizTpDTO bizTpDTO = businessTripEmployeeDTO.getBusinessTrip();
		EmpDTO empDTO = businessTripEmployeeDTO.getEmp();
		BusinessTripEmployee businessTripEmployee = BusinessTripEmployee.builder()
				.businessTrip(bizTpDTO.toEntity(bizTpDTO)).emp(empDTO.toEntity(empDTO)).build();

		return businessTripEmployee;
	}

	public BusinessTripEmployeeId toId(BizTpEmpDTO businessTripEmployeeDTO) {
		BizTpDTO bizTpDTO = businessTripEmployeeDTO.getBusinessTrip();
		EmpDTO empDTO = businessTripEmployeeDTO.getEmp();
		BusinessTripEmployeeId businessTripEmployeeId = BusinessTripEmployeeId.builder()
				.businessTrip(bizTpDTO.toEntity(bizTpDTO).getBusinessTripId()).emp(empDTO.toEntity(empDTO).getEmpId())
				.build();
		return businessTripEmployeeId;
	}
}
