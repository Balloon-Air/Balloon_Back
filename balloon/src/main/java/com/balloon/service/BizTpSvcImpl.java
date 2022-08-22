package com.balloon.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.balloon.dto.BizTpDTO;
import com.balloon.entity.BusinessTripPlan;
import com.balloon.repository.BizTpRepository;
import com.balloon.vo.DocVO;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BizTpSvcImpl implements BizTpSvc {
	private final BizTpRepository bizTpRepo;

	@Override
	public void insertBizTp(BizTpDTO bizTpDTO) {
		BusinessTripPlan bizTpEntity = bizTpDTO.toEntity(bizTpDTO);
		bizTpRepo.save(bizTpEntity);
	}

	@Override
	public List<DocVO> getDocbyEmpIdAndDocStatus(String empId, Byte docStatus) {
		String id = empId;
		Byte status = docStatus;
		List<DocVO> voList = new ArrayList<DocVO>();
		List<BusinessTripPlan> businessTripPlanList = bizTpRepo
				.findBusinessTripIdAndDocumentTitleAndUpdateDateByEmpEmpIdAndDocumentStatus(id, status);
		for (BusinessTripPlan businessTripPlan : businessTripPlanList) {
			voList.add(businessTripPlan.toVO(businessTripPlan));
		}
		return voList;
	}

	@Override
	public List<DocVO> getDocbyUnitCode(String unitCode) {
		String code = unitCode.substring(0, 4);
		System.out.println(code);
		Byte status = 2;
		List<DocVO> voList = new ArrayList<DocVO>();
		List<BusinessTripPlan> businessTripPlanList = bizTpRepo
				.findBusinessTripIdAndDocumentTitleAndUpdateDateByUnitUnitCodeStartingWithAndDocumentStatus(code,
						status);
		for (BusinessTripPlan businessTripPlan : businessTripPlanList) {
			voList.add(businessTripPlan.toVO(businessTripPlan));
		}
		return voList;
	}

	@Override
	public BizTpDTO getBizTpByBizTpId(String bizTpId) {
		BusinessTripPlan businessTripPlan = bizTpRepo.findBusinessTripPlanByBusinessTripId(bizTpId);
		BizTpDTO bizTpDTO = businessTripPlan.toDTO(businessTripPlan);
		return bizTpDTO;
	}

	@Override
	public void deleteBizTpByBizTpId(String bizTpId) {
		bizTpRepo.deleteById(bizTpId);
	}

	@Override
	public BizTpDTO getBizTpWD() {
		BusinessTripPlan businessTripPlan = bizTpRepo.findTopByOrderByWriteDateDesc();
		BizTpDTO bizTpDTO = businessTripPlan.toDTO(businessTripPlan);
		return bizTpDTO;
	}

}
