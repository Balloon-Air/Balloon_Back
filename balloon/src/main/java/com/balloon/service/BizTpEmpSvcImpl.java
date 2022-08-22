package com.balloon.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.balloon.dto.BizTpEmpDTO;
import com.balloon.entity.BusinessTripEmployee;
import com.balloon.repository.BizTpEmpRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BizTpEmpSvcImpl implements BizTpEmpSvc {
	private final BizTpEmpRepository bizTpEmpRepo;

	@Override
	public void insertBizTpEmp(BizTpEmpDTO bizTpEmpDTO) {
		BusinessTripEmployee businessTripEmployee = bizTpEmpDTO.toEntity(bizTpEmpDTO);
		bizTpEmpRepo.save(businessTripEmployee);
	}

	@Override
	public List<BizTpEmpDTO> getBizTpEmpByBizTpId(String bizTpId) {
		List<BizTpEmpDTO> DTOList = new ArrayList<BizTpEmpDTO>();
		List<BusinessTripEmployee> bizTpEmpList = bizTpEmpRepo.findEmpByBusinessTripBusinessTripId(bizTpId);

		for (BusinessTripEmployee businessTripEmployee : bizTpEmpList) {
			DTOList.add(businessTripEmployee.toDTO(businessTripEmployee));
		}
		return DTOList;
	}
}
