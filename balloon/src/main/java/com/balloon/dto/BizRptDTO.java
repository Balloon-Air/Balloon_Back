package com.balloon.dto;

import java.time.LocalDateTime;

import com.balloon.entity.BusinessReport;
import com.balloon.entity.Employee;
import com.balloon.entity.Unit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BizRptDTO {

	private String businessReportId;

	private String documentTitle;

	private String documentContent;

	private Byte documentStatus;

	private String empName;

	private String position;

	private LocalDateTime writeDate;

	private LocalDateTime updateDate;

	private String unitName;

	private Unit unit;

	private Employee emp;

	public BusinessReport toEntity(BizRptDTO bizRptDTO) {
		BusinessReport businessReport = BusinessReport.builder().businessReportId(bizRptDTO.getBusinessReportId())
				.documentTitle(bizRptDTO.getDocumentTitle()).documentContent(bizRptDTO.getDocumentContent())
				.documentStatus(bizRptDTO.getDocumentStatus()).empName(bizRptDTO.getEmpName())
				.position(bizRptDTO.getPosition()).writeDate(bizRptDTO.getWriteDate())
				.updateDate(bizRptDTO.getUpdateDate()).unitName(bizRptDTO.getUnitName()).unit(bizRptDTO.getUnit())
				.emp(bizRptDTO.getEmp()).build();

		return businessReport;
	}

}
