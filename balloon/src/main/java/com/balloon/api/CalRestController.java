package com.balloon.api;

import java.util.List;

import javax.validation.Valid;

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

import com.balloon.dto.CalDTO;
import com.balloon.service.CalServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000" })
public class CalRestController {
//	@Autowired

	private final CalServiceImpl calService;
	// 캘린더

	@GetMapping(value = "/cal/list")
	public List<CalDTO> findAll() {

		return calService.findAll();
	}

	@GetMapping(value = "/cal/all/{scheduleId}")
	public CalDTO CalByScheduleId(@PathVariable(name = "scheduleId") Long scheduleid) {
		System.out.println("스케쥬우우울");
		return calService.getCalByscheduleId(scheduleid);
	}

	@DeleteMapping(value = "/cal/delete/{scheduleId}")
	public void scheduleIdDelete(@PathVariable(name = "scheduleId") Long scheduleid) {
		calService.deleteByCalId(scheduleid);
	}

	@PostMapping(value = "/cal/insert", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public void CalByInsert(@Valid @RequestBody CalDTO calDTO) {
		System.out.println(calDTO);
		calService.insertBycal(calDTO);
	}

	@PostMapping(value = "/cal/schedule", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void scheduleListAdd(@RequestBody List<CalDTO> calDTOs) {
		calService.scheduleListAdd(calDTOs);
	}

	@PutMapping(value = "/cal/update", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public void updateCalByscheduleId(@RequestBody CalDTO calDTO) {
		System.out.println(calDTO);
		try {
			calService.updateByCal(calDTO);
		} catch (Exception e) {
			System.out.println("에러에러");
			throw new NullPointerException("scheduleId is null.");
		}
	}

	@GetMapping(value = "/cal/{empId}")
	public List<CalDTO> CalByEmpId(@PathVariable(name = "empId") String empId) {
		System.out.println("이엠피아이디");

		return calService.getCalByempId(empId);
	}
}
