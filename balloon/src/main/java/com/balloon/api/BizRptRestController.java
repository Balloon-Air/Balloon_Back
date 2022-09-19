package com.balloon.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balloon.dto.BizRptDTO;
import com.balloon.service.BizRptSvcImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
<<<<<<< HEAD
//@CrossOrigin(origins = { "http://localhost:3000" })
//@CrossOrigin(origins = { "http://54.180.148.125:8080" })
=======
>>>>>>> 28cd69fa1279cfc7504235ebc9523ad34ec3d86b
public class BizRptRestController {

	private final BizRptSvcImpl BizRptSvc;

	// CREATE -------------------------------
	@PostMapping(value = "/bizrpt", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertBizRpt(@RequestBody BizRptDTO bizRptDTO) {
		BizRptSvc.insertBizRpt(bizRptDTO);
	}

	// READ ---------------------------------
	@GetMapping(value = "/bizrpt/{bizRptId}")
	public BizRptDTO getBizRptByBisRptId(@PathVariable("bizRptId") String bizRptId) {
		return BizRptSvc.getBizRptByBizRptId(bizRptId);
	}

	@GetMapping(value = "/bizrpt/wd")
	public BizRptDTO getLatestBizRpt() {
		return BizRptSvc.getBizRptWD();
	}

	// UPDATE -------------------------------
	@PatchMapping(value = "/bizrpt/{bizRptId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateBizRptByBizRptId(@PathVariable("bizRptId") String bizRptId, @RequestBody BizRptDTO bizRptDTO) {
		bizRptDTO.setBusinessReportId(bizRptId);
		BizRptSvc.insertBizRpt(bizRptDTO);
	}

	// DELETE -------------------------------
//	@CrossOrigin(origins = { "http://localhost:3000"})
//	@CrossOrigin(origins = { "http://54.180.148.125:8080" })
	@DeleteMapping(value = "/bizrpt/{bizRptId}")
	public void deleteBizRptByBizRptId(@PathVariable("bizRptId") String bizRptId) {
		BizRptSvc.deleteBizRptByBizRptId(bizRptId);
	}

}
