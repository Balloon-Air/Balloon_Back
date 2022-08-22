package com.balloon.service;

import java.util.List;

import com.balloon.dto.BizTpEmpDTO;

public interface BizTpEmpSvc {
	public void insertBizTpEmp(BizTpEmpDTO bizTpEmpDTO);

	public List<BizTpEmpDTO> getBizTpEmpByBizTpId(String bizTpId);
}
