package com.balloon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.balloon.dto.EmpRequestDTO;
import com.balloon.dto.EmpResponseDTO;
import com.balloon.dto.TokenDTO;
import com.balloon.entity.Employee;
import com.balloon.jwt.TokenProvider;
import com.balloon.repository.EmpRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class AuthServiceImpl implements AuthService {
	private final AuthenticationManagerBuilder managerBuilder;
	private final EmpRepository empRepo;
	private final PasswordEncoder passwordEncoder;
	private final TokenProvider tokenProvider;

	@Transactional
	@Override
	public EmpResponseDTO signup(EmpRequestDTO requestDto) {
		System.out.println(requestDto.getEmpId());
		if ((empRepo.existsEmpByEmpId(requestDto.getEmpId())) == true) {
			throw new RuntimeException("이미 가입되어 있는 유저입니다");
		} else {
			Employee employee = requestDto.toEmployee(passwordEncoder);
			return EmpResponseDTO.of(empRepo.save(employee));
		}

	}

	@Transactional
	@Override
	public boolean signupList(List<EmpRequestDTO> requestDtoList) {

//		if ((empRepo.existsEmpByEmpId(requestDto.getEmpId())) == true) {
//			throw new RuntimeException("이미 가입되어 있는 유저입니다");
//		} else {
//			Employee employee = requestDto.toEmployee(passwordEncoder);
//			return EmpResponseDTO.of(empRepo.save(employee));
//		}
		if (requestDtoList != null) {
			System.out.println(requestDtoList);

			List<Employee> empList = new ArrayList<Employee>();

			// unitList.forEach(unitEntity ->
			// unitDTOList.add(unitEntity.toDTO(unitEntity)));

			requestDtoList.forEach(empDto -> empList.add(empDto.toEmployee(passwordEncoder)));

			empRepo.saveAll(empList);
			System.out.println("sssssssssssssssssssssssssssssssssssssssss");
			return true;
		}

		return false;
	}

	@Transactional
	@Override
	public TokenDTO login(EmpRequestDTO requestDto) {
		UsernamePasswordAuthenticationToken authenticationToken = requestDto.toAuthentication();
		Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);
		return tokenProvider.generateTokenDTO(authentication);
	}

}