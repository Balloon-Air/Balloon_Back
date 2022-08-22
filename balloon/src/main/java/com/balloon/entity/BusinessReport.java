package com.balloon.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.balloon.dto.BizRptDTO;
import com.balloon.vo.DocVO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "business_report")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)

public class BusinessReport {

	@Id
	@Column(name = "business_report_id", length = 20)
	private String businessReportId;

	@NotNull
	@Column(name = "document_title", length = 45)
	private String documentTitle;

	@NotNull
	@Column(name = "document_content", length = 2000)
	private String documentContent;

	@NotNull
	@Column(name = "document_status", length = 1)
	private Byte documentStatus;

	@NotNull
	@Column(name = "emp_name", length = 30)
	private String empName;

	@NotNull
	@Column(name = "position", length = 20)
	private String position;

	@CreatedDate
	@Column(name = "write_date", updatable = false)
	private LocalDateTime writeDate;

	@LastModifiedDate
	@Column(name = "update_date")
	private LocalDateTime updateDate;

	@NotNull
	@Column(name = "unit_name")
	private String unitName;

	@JsonIgnore
	@ManyToOne(targetEntity = Unit.class)
	@JoinColumn(name = "unit_code")
	private Unit unit;

	@JsonIgnore
	@ManyToOne(targetEntity = Employee.class)
	@JoinColumn(name = "emp_id")
	private Employee emp;

	public BizRptDTO toDTO(BusinessReport businessReport) {

		BizRptDTO bizRptDTO = BizRptDTO.builder().businessReportId(businessReport.getBusinessReportId())
				.documentTitle(businessReport.getDocumentTitle()).documentContent(businessReport.getDocumentContent())
				.documentStatus(businessReport.getDocumentStatus()).empName(businessReport.getEmpName())
				.position(businessReport.getPosition()).updateDate(businessReport.getUpdateDate())
				.unit(businessReport.getUnit()).emp(businessReport.getEmp()).build();

		return bizRptDTO;
	}

	public DocVO toVO(BusinessReport businessReport) {
//		DocVO docVO = DocVO.builder().docId(businessReport.getBusinessReportId())
//				.documentTitle(businessReport.getDocumentTitle()).updateTime(businessReport.getUpdateDate())
//				.documentStatus(businessReport.getDocumentStatus()).unit(businessReport.getUnit()).build();
		DocVO docVO = DocVO.builder().docId(businessReport.getBusinessReportId())
				.documentTitle(businessReport.getDocumentTitle()).updateTime(businessReport.getUpdateDate()).build();
		return docVO;
	}

}
