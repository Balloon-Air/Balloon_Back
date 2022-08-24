package com.balloon.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@GetMapping(value = "/apvl/{approver}/{docStatus}")
	public List<ApvlDTO> getApvlByApproverNameAndDocStatus(@PathVariable("approver") String approver,
			@PathVariable("docStatus") Byte docStatus) {
		System.out.println(docStatus);
		System.out.println(approver);
		List<ApvlDTO> list = new ArrayList<ApvlDTO>();
		list.addAll(ApvlSvc.getApvlByApproverNameAndDocStatus(approver, docStatus));

		return list;
	}

	// UPDATE -------------------------------

	// DELETE -------------------------------
	@CrossOrigin(origins = { "http://localhost:3000" })
	@DeleteMapping(value = "/apvl/{docId}")
	public void deleteApvlByDocId(@PathVariable("docId") String docId) throws Exception {

		if (docId.contains("업무기안")) {
			ApvlSvc.deleteApvlByBizRptId(docId);

		} else if (docId.contains("출장계획")) {
//			ApvlSvc.deleteApvlByBizTpId(docId);

		} else if (docId.contains("인사명령")) {
//			ApvlSvc.deleteApvlByPAId(docId);

		} else {
			throw new Exception("없는 문서 입니다.");
		}

	}

}
