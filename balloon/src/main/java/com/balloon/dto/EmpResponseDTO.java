package com.balloon.dto;

import java.time.LocalDate;

import com.balloon.entity.Employee;
import com.balloon.entity.Unit;
import com.balloon.entity.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmpResponseDTO {

	private String empId;      
	
	private String empName;
	
	private String position;
	
	private String mobile;
	
	private LocalDate hiredate;
	
	private Float salary;
	
	private Float commission;
	
	private String empMail;
	
	private String photo;
	
	private String empBell;
	
	private LocalDate birthday;
	
	private String address;
	
	private String licensePlate;
	
	private String responsibility;
	
	private Unit unit;
	
	private UserRole userRoleGrade;

	public Employee toEntity(EmpResponseDTO employeeDTO) {
		Employee employeeEntity = Employee.builder().empId(employeeDTO.getEmpId()).empName(employeeDTO.getEmpName())
				.position(employeeDTO.getPosition()).mobile(employeeDTO.getMobile()).hiredate(employeeDTO.getHiredate())
				.salary(employeeDTO.getSalary()).commission(employeeDTO.getCommission())
				.empMail(employeeDTO.getEmpMail()).photo(employeeDTO.getPhoto()).empBell(employeeDTO.getEmpBell())
				.birthday(employeeDTO.getBirthday()).address(employeeDTO.getAddress())
				.licensePlate(employeeDTO.getLicensePlate()).responsibility(employeeDTO.getResponsibility())
				.unit(employeeDTO.getUnit()).userRoleGrade(employeeDTO.getUserRoleGrade()).build();

		return employeeEntity;
	}

	public static EmpResponseDTO of(Employee employee) {
		return EmpResponseDTO.builder().empId(employee.getEmpId()).empName(employee.getEmpName())
				.position(employee.getPosition()).mobile(employee.getMobile()).hiredate(employee.getHiredate())
				.salary(employee.getSalary()).commission(employee.getCommission()).empMail(employee.getEmpMail())
				.photo(employee.getPhoto()).empBell(employee.getEmpBell()).birthday(employee.getBirthday())
				.address(employee.getAddress()).licensePlate(employee.getLicensePlate())
				.responsibility(employee.getResponsibility()).unit(employee.getUnit())
				.userRoleGrade(employee.getUserRoleGrade()).build();

	}

}
