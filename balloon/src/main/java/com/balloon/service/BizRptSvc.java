package com.balloon.service;

import java.time.LocalDateTime;
import java.util.List;

import com.balloon.dto.BizRptDTO;
import com.balloon.vo.DocVO;

public interface BizRptSvc {
	public void insertBizRpt(BizRptDTO bizRptDTO);

//	public PageResultDTO<BizRptDTO, BusinessReport> getList(PageRequestDTO pageRequestDTO);

	public List<DocVO> getDocbyEmpIdAndDocStatus(String empId, Byte documentStatus);

	public List<DocVO> getDocbyEmpIdAndDocStatusByDate(String empId, Byte docStatus, LocalDateTime sunDay,
			LocalDateTime saturDay);

	public List<DocVO> getDocbyUnitCode(String unitCode);

//	public List<DocVO> getDocByEmpName(String empName);

	public BizRptDTO getBizRptByBizRptId(String businessReportId);

	public void deleteBizRptByBizRptId(String businessReportId);

	public BizRptDTO getBizRptWD();

}
