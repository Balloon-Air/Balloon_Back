package com.balloon.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balloon.dto.ApvlDTO;
import com.balloon.service.ApvlSvcImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApvlRestController {
	private final ApvlSvcImpl ApvlSvc;

	// CREATE -------------------------------
	@PostMapping(value = "/apvl", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertApvl(@RequestBody ApvlDTO apvlDTO) {
		ApvlSvc.insertApvl(apvlDTO);
	}

	// READ ---------------------------------
	@GetMapping(value = "/apvl/{docId}")
	public List<ApvlDTO> getApvlByDocId(@PathVariable("docId") String docId) throws Exception {
		List<ApvlDTO> list = new ArrayList<ApvlDTO>();

		if (docId.contains("업무기안")) {
			list.addAll(ApvlSvc.getApvlByBizRptId(docId));

		} else if (docId.contains("출장계획")) {
			list.addAll(ApvlSvc.getApvlByBizTpId(docId));

		} else if (docId.contains("인사명령")) {
			list.addAll(ApvlSvc.getApvlByPAId(docId));

		} else {
			throw new Exception("없는 문서 입니다.");
		}
		return list;
	}

	@GetMapping(value = "/apvl/{apvrId}/{docStatus}")
	public List<ApvlDTO> getApvlByApvrNameAndDocStatus(@PathVariable("apvrId") String approver,
			@PathVariable("docStatus") Byte docStatus) {
		System.out.println(docStatus);
		System.out.println(approver);
		List<ApvlDTO> list = new ArrayList<ApvlDTO>();
		list.addAll(ApvlSvc.getApvlByApproverIdAndDocStatus(approver, docStatus));

		return list;
	}

	@GetMapping(value = "/apvl/apvlid/{docId}/{apvrId}")
	public ApvlDTO getApvlIdByDocIdAndApvrId(@PathVariable("docId") String docId,
			@PathVariable("apvrId") String approver) throws Exception {
		ApvlDTO apvlDTO = new ApvlDTO();
		if (docId.contains("업무기안")) {
			apvlDTO = ApvlSvc.getApvlIdByBizRptIdAndApvrId(docId, approver);
		} else if (docId.contains("출장계획")) {
			apvlDTO = ApvlSvc.getApvlIdByBizTpIdAndApvrId(docId, approver);
		} else if (docId.contains("인사명령")) {
			apvlDTO = ApvlSvc.getApvlIdByPAIdAndApvrId(docId, approver);
		} else {
			throw new Exception("없는 문서 입니다.");
		}
		return apvlDTO;
	}

	// UPDATE -------------------------------
	@PutMapping(value = "/apvl/{apvlId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateApvlByApvlId(@PathVariable("apvlId") Integer apvlId, @RequestBody ApvlDTO apvlDTO) {
		apvlDTO.setApprovalId(apvlId);
		ApvlSvc.updateApvl(apvlDTO);

	}

	// DELETE -------------------------------
	@CrossOrigin(origins = { "http://localhost:3000" })
	@DeleteMapping(value = "/apvl/{docId}/{empId}")
	public void deleteApvlByDocId(@PathVariable("docId") String docId, @PathVariable("empId") String empId)
			throws Exception {

		if (docId.contains("업무기안")) {
			ApvlSvc.deleteApvlByBizRptId(docId, empId);

		} else if (docId.contains("출장계획")) {
			ApvlSvc.deleteApvlByBizTpId(docId, empId);

		} else if (docId.contains("인사명령")) {
			ApvlSvc.deleteApvlByPAId(docId, empId);

		} else {
			throw new Exception("없는 문서 입니다.");
		}

	}

}
