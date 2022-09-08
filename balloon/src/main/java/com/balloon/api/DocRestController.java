package com.balloon.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balloon.service.BizRptSvcImpl;
import com.balloon.service.BizTpSvcImpl;
import com.balloon.service.PASvcImpl;
import com.balloon.vo.DocVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/box")
@RequiredArgsConstructor
public class DocRestController {

	private final BizRptSvcImpl BizRptSvc;
	private final BizTpSvcImpl BizTpSvc;
	private final PASvcImpl PASvc;

	@GetMapping(value = { "/empdocs/{empId}/{docStatus}" })
	public List<DocVO> getDocbyEmpId(@PathVariable("empId") String empId, @PathVariable("docStatus") Byte docStatus) {
		System.out.println(empId);
		System.out.println(docStatus);
		List<DocVO> list = new ArrayList<DocVO>();
		list.addAll(BizRptSvc.getDocbyEmpIdAndDocStatus(empId, docStatus));
		list.addAll(BizTpSvc.getDocbyEmpIdAndDocStatus(empId, docStatus));
		list.addAll(PASvc.getDocbyEmpIdAndDocStatus(empId, docStatus));

//		System.out.println(list); -> stackoverflow
		return list;
	}

	@GetMapping(value = "/unitdocs/{unitCode}")
	public List<DocVO> getDocbyUnitCode(@PathVariable("unitCode") String unitCode) {
		List<DocVO> list = new ArrayList<DocVO>();
		list.addAll(BizRptSvc.getDocbyUnitCode(unitCode));
		list.addAll(BizTpSvc.getDocbyUnitCode(unitCode));
		list.addAll(PASvc.getDocbyUnitCode(unitCode));

		return list;
	}

//	@GetMapping(value = "/apvldocs/{empName}")
//	public List<DocVO> getDocByApvrName(@PathVariable("empName") String empName) {
//		List<DocVO> list = new ArrayList<DocVO>();
//		list.addAll(BizRptSvc.getDocByEmpName(empName));
//		list.addAll(BizTpSvc.getDocByEmpName(empName));
//		list.addAll(PASvc.getDocByEmpName(empName));
//		return list;
//	}

//	@GetMapping(value = "/dc")
//	@GetMapping(value = "/ds")
//	@GetMapping(value = "/dr")
//	@GetMapping(value = "/dd/{doc_id}")
//	@GetMapping(value = "/dc/{doc_id}")
//	@GetMapping(value = "/ds/{doc_id}")
//	@GetMapping(value = "/dr/{doc_id}")
//	
//	// UPDATE -------------------------------
//	@PutMapping(value = "/dd/{doc_id}")
//	@PutMapping(value = "/ds/{doc_id}")
//	@PutMapping(value = "/dr/{doc_id}")
//	
//	// DELETE -------------------------------
//	@DeleteMapping(value = "/dd/{doc_id}")
//	@DeleteMapping(value = "/ds/{doc_id}")
//	@DeleteMapping(value = "/dr/{doc_id}")

}
