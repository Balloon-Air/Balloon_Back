package com.balloon.service;

import java.util.List;

import com.balloon.dto.EmpRequestDTO;
import com.balloon.dto.EmpResponseDTO;
import com.balloon.dto.TokenDTO;

public interface AuthService {

	public EmpResponseDTO signup(EmpRequestDTO requestDto);

	public boolean signupList(List<EmpRequestDTO> requestDtoList);

	public TokenDTO login(EmpRequestDTO requestDto);

}
