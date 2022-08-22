package com.balloon.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.balloon.dto.BizTpEmpDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "business_trip_employee")
@IdClass(BusinessTripEmployeeId.class)
public class BusinessTripEmployee {

	@Id
	@ManyToOne(targetEntity = BusinessTripPlan.class)
	@JoinColumn(name = "business_trip_id")
	private BusinessTripPlan businessTrip;

	@Id
	@ManyToOne(targetEntity = Employee.class)
	@JoinColumn(name = "emp_id")
	private Employee emp;

	public BizTpEmpDTO toDTO(BusinessTripEmployee businessTripEmployee) {
		BusinessTripPlan businessTripPlan = businessTripEmployee.getBusinessTrip();
		Employee employee = businessTripEmployee.getEmp();
		BizTpEmpDTO businessTripEmployeeDTO = BizTpEmpDTO.builder()
				.businessTrip(businessTripPlan.toDTO(businessTripPlan)).emp(employee.toDTO(employee)).build();
		return businessTripEmployeeDTO;
	}

}
