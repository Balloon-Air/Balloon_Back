package com.balloon.service;

import java.time.LocalDateTime;
import java.util.List;

import com.balloon.dto.BizTpDTO;
import com.balloon.vo.DocVO;

public interface BizTpSvc {
	public void insertBizTp(BizTpDTO bizTpDTO);

	public List<DocVO> getDocbyEmpIdAndDocStatus(String empId, Byte documentStatus);

	public List<DocVO> getDocbyEmpIdAndDocStatusByDate(String empId, Byte docStatus, LocalDateTime sunDay,
			LocalDateTime saturDay);

	public List<DocVO> getDocbyUnitCode(String unitCode);

	public BizTpDTO getBizTpByBizTpId(String businessTripId);

	public void deleteBizTpByBizTpId(String businessTripId);

	public BizTpDTO getBizTpWD();

//	public BizTpDTO getBizTpByEmpId(String empId);

}
