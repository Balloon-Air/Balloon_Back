package com.balloon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.balloon.dto.BizRptDTO;
import com.balloon.entity.BusinessReport;
import com.balloon.repository.BizRptRepository;
import com.balloon.vo.DocVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BizRptSvcImpl implements BizRptSvc {
	private final BizRptRepository bizRptRepo;

	@Override
	public void insertBizRpt(BizRptDTO bizRptDTO) {
		BusinessReport bizRptEntity = bizRptDTO.toEntity(bizRptDTO);
		bizRptRepo.save(bizRptEntity);
	}

//	@Override
//	public PageResultDTO<BizRptDTO, BusinessReport> getList(PageRequestDTO pageRequestDTO) {
//		Pageable pageable = pageRequestDTO.getPageable(Sort.by("businessReportId").descending());
//
//		Page<BusinessReport> result = bizRptRepo.findAll(pageable);
//
//		Function<BusinessReport, BizRptDTO> function = (businessReport -> businessReport.toDTO(businessReport));
//
//		return new PageResultDTO<BizRptDTO, BusinessReport>(result, function);
//	}

	@Override
	public List<DocVO> getDocbyEmpIdAndDocStatus(String empId, Byte docStatus) {
		String id = empId;
		Byte status = docStatus;
		List<DocVO> voList = new ArrayList<DocVO>();
		List<BusinessReport> businessReportsList = bizRptRepo
				.findBusinessReportIdAndDocumentTitleAndUpdateDateByEmpEmpIdAndDocumentStatus(id, status);
		for (BusinessReport businessReport : businessReportsList) {
			voList.add(businessReport.toVO(businessReport));
		}
		return voList;
	}

	@Override
	public List<DocVO> getDocbyUnitCode(String unitCode) {
		String code = unitCode.substring(0, 4);
		Byte status = 2;
		List<DocVO> voList = new ArrayList<DocVO>();
		List<BusinessReport> businessReportsList = bizRptRepo
				.findBusinessReportIdAndDocumentTitleAndUpdateDateByUnitUnitCodeStartingWithAndDocumentStatus(code,
						status);
		for (BusinessReport businessReport : businessReportsList) {
			voList.add(businessReport.toVO(businessReport));
		}
		return voList;
	}

//	@Override
//	public List<DocVO> getDocByEmpName(String empName) {
//		
//		return null;
//	}

	@Override
	public BizRptDTO getBizRptByBizRptId(String bizRptId) {
		BusinessReport businessReport = bizRptRepo.findBusinessReportByBusinessReportId(bizRptId);
		BizRptDTO bizRptDTO = businessReport.toDTO(businessReport);
		return bizRptDTO;
	}

	@Override
	public void deleteBizRptByBizRptId(String bizRptId) {
		bizRptRepo.deleteById(bizRptId);
	}

	@Override
	public BizRptDTO getBizRptWD() {
		BusinessReport businessReport = bizRptRepo.findTopByOrderByWriteDateDesc();
		BizRptDTO bizRptDTO = businessReport.toDTO(businessReport);
		return bizRptDTO;
	}

}
