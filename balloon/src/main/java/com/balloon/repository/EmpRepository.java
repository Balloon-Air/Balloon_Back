package com.balloon.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.balloon.dto.EmpResponseDTO;
import com.balloon.entity.Employee;

@Repository
public interface EmpRepository extends JpaRepository<Employee, String> {

//	public void saveAll(List<Employee> employees);

	public Employee findEmpByEmpId(String empId);

	@Query(value = "SELECT *  " + "FROM employee e " + "WHERE unit_code " + "IN ( SELECT u.unit_code " + "FROM unit u "
			+ "WHERE u.parent_unit = :unitCode);", nativeQuery = true)
	public List<Employee> findEmpListInUnitCode(@Param("unitCode") String uniCode);

	Optional<Employee> findByEmpId(String empId);

	EmpResponseDTO getByEmpId(String empId);

	boolean existsEmpByEmpId(String empId);

	public List<Employee> findAll();

	@Query(value = "SELECT * FROM employee e " + "	    WHERE e.unit_code = (SELECT u.parent_unit FROM unit u "
			+ "											WHERE u.unit_code = ( SELECT ee.unit_code FROM employee ee "
			+ "																WHERE ee.emp_id = :empId) "
			+ "					 ) " + "ORDER BY e.emp_name ASC;", nativeQuery = true)
	public List<Employee> findEmpListOnParentCode(@Param("empId") String empId);

	@Query(value = "SELECT * FROM employee e " + "	   WHERE e.unit_code IN (SELECT u.unit_code FROM unit u "
			+ "					         WHERE u.parent_unit = ( SELECT uu.parent_unit FROM unit uu	"
			+ "											        WHERE uu.unit_code = ( SELECT ee.unit_code FROM employee ee "
			+ "																          WHERE ee.emp_id = :empId) "
			+ "										) " + "					) AND e.emp_id != :empId "
			+ "	   ORDER BY e.unit_code, e.emp_name ASC;", nativeQuery = true)
	public List<Employee> findEmpListOnSameParentCode(@Param("empId") String empId);

}
