package com.balloon.service;

import com.balloon.dto.ApvlDTO;

public interface ApvlSvc {
	public void insertApvl(ApvlDTO apvlDTO);

	public ApvlDTO getApvlByBizRptId(String docId);

	public ApvlDTO getApvlByBizTpId(String docId);

	public ApvlDTO getApvlByPAId(String docId);

	public void deleteApvlByBizRptId(String docId);

	public void deleteApvlByBizTpId(String docId);

	public void deleteApvlByPAId(String docId);
}
