package com.balloon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.balloon.config.SecurityUtil;
import com.balloon.dto.EmpDTO;
import com.balloon.dto.EmpResponseDTO;
import com.balloon.dto.PageRequestDTO;
import com.balloon.dto.PageResultDTO;
import com.balloon.entity.Employee;
import com.balloon.repository.EmpRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpServiceImpl implements EmpService {

	private final EmpRepository empRepo;
	private final PasswordEncoder passwordEncoder;

	@Transactional(readOnly = true)
	@Override
	public PageResultDTO<EmpDTO, Employee> findEmpList(PageRequestDTO pageRequestDTO) {
		Pageable pageable = pageRequestDTO.getPageable(Sort.by("empId").descending());
		Page<Employee> result = empRepo.findAll(pageable);
		Function<Employee, EmpDTO> function = (empEntity -> empEntity.toDTO(empEntity));

		return new PageResultDTO<EmpDTO, Employee>(result, function);
	};

	@Transactional(readOnly = true)
	@Override
	public EmpDTO findEmpByEmpId(String empId) throws Exception {
		Employee empEntity = empRepo.findEmpByEmpId(empId);
		if (empEntity == null) {
			throw new Exception("사원 정보가 존재하지 않습니다.");
		} else {
			return empEntity.toDTO(empEntity);
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<EmpDTO> findEmps() {
		List<Employee> empEntityList = empRepo.findAll();
		List<EmpDTO> empDTOList = new ArrayList<EmpDTO>();

		empEntityList.forEach(empEntity -> empDTOList.add(empEntity.toDTO(empEntity)));

		return empDTOList;
	};

	@Transactional(readOnly = true)
	@Override
	public EmpResponseDTO getMyInfoBySecurity() {
		if (SecurityUtil.getCurrentEmpId() == null) {
			throw new RuntimeException("로그인 유저 정보가 없습니다.");
		}

		return empRepo.getByEmpId(SecurityUtil.getCurrentEmpId());
	}

	@Transactional(readOnly = true)
	@Override
	public List<Employee> findEmpListInUnitCode(String unitCode) {
		return empRepo.findEmpListInUnitCode(unitCode);
	}

	@Transactional(readOnly = true)
	@Override
	public List<EmpDTO> findEmpListInSameUnit(String empId) {

		List<Employee> parentCodeList = empRepo.findEmpListOnParentCode(empId);
		List<Employee> sameParentCodeList = empRepo.findEmpListOnSameParentCode(empId);

		List<EmpDTO> empDTOList = new ArrayList<EmpDTO>();

		parentCodeList.forEach(empEntity -> empDTOList.add(empEntity.toDTO(empEntity)));
		sameParentCodeList.forEach(empEntity -> empDTOList.add(empEntity.toDTO(empEntity)));

		return empDTOList;

	}

	@Transactional
	@Override
	public List<EmpDTO> findApvrListInSameUnit(String empId) {

		List<Employee> parentCodeList = empRepo.findApvrListOnParentCode(empId);
		List<Employee> sameParentCodeList = empRepo.findApvrListOnSameParentCode(empId);

		List<EmpDTO> empDTOList = new ArrayList<EmpDTO>();

		parentCodeList.forEach(empEntity -> empDTOList.add(empEntity.toDTO(empEntity)));
		sameParentCodeList.forEach(empEntity -> empDTOList.add(empEntity.toDTO(empEntity)));

		return empDTOList;

	}

	@Override
	public void deleteByEmpId(String empId) {
		empRepo.deleteById(empId);
	}

	@Transactional
	@Override
	public EmpResponseDTO changeEmpName(String empId, String empName) {
		Employee employee = empRepo.findEmpByEmpId(empId);
		if (employee == null) {
			throw new RuntimeException("로그인 유저 정보가 없습니다.");
		}
		employee.updateEmpName(empName);
		return EmpResponseDTO.of(empRepo.save(employee));
	}

	@Transactional
	@Override
	public EmpResponseDTO changePassword(String empId, String exPasssword, String newPassword) {
		Employee employee = empRepo.findEmpByEmpId(empId);
		if (employee == null) {
			throw new RuntimeException("로그인 유저 정보가 없습니다.");
		}
		if (!passwordEncoder.matches(exPasssword, employee.getPassword())) {
			throw new RuntimeException("비밀번호가 맞지 않습니다.");
		}
		employee.updatePassword(passwordEncoder.encode(newPassword));
		return EmpResponseDTO.of(empRepo.save(employee));
	}

}
