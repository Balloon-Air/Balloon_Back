package com.balloon.service;

import java.util.List;

import com.balloon.dto.ApvlDTO;

public interface ApvlSvc {
	public void insertApvl(ApvlDTO apvlDTO);

	public List<ApvlDTO> getApvlByBizRptId(String docId);

	public List<ApvlDTO> getApvlByBizTpId(String docId);

	public List<ApvlDTO> getApvlByPAId(String docId);

	public List<ApvlDTO> getApvlByApproverIdAndDocStatus(String apvrId, Byte docStatus);

	public ApvlDTO getApvlIdByBizRptIdAndApvrId(String docId, String approver);

	public ApvlDTO getApvlIdByBizTpIdAndApvrId(String docId, String approver);

	public ApvlDTO getApvlIdByPAIdAndApvrId(String docId, String approver);

	public void updateApvl(ApvlDTO apvlDTO);

	public void deleteApvlByBizRptId(String docId, String empId);

	public void deleteApvlByBizTpId(String docId, String empId);

	public void deleteApvlByPAId(String docId, String empId);

//	public void updateApvlByBizRptIdAndApvr(String docId, String apvr);
}
