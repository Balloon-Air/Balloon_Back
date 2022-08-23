package com.balloon.service;

import java.util.List;

import com.balloon.dto.ApvlDTO;

public interface ApvlSvc {
	public void insertApvl(ApvlDTO apvlDTO);

	public List<ApvlDTO> getApvlByBizRptId(String docId);

	public List<ApvlDTO> getApvlByBizTpId(String docId);

	public List<ApvlDTO> getApvlByPAId(String docId);

	public void deleteApvlByBizRptId(String docId);

	public void deleteApvlByBizTpId(String docId);

	public void deleteApvlByPAId(String docId);
}
