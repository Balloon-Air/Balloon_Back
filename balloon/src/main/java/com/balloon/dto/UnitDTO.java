package com.balloon.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.balloon.entity.Unit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UnitDTO {

	@Id
	@Column(name = "unit_code")
	private String unitCode;

	@Column(name = "unit_name", length = 20)
	private String unitName;

	@Column(length = 15)
	private String bell;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "unit_id", referencedColumnName = "parent_unit")
	private Unit parentUnit;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parentUnit")
	private List<Unit> childUnits;

	public Unit toEntity(UnitDTO unitDTO) {
		Unit unitEntity = Unit.builder().unitCode(unitDTO.getUnitCode()).unitName(unitDTO.getUnitName())
				.bell(unitDTO.getBell()).parentUnit(unitDTO.getParentUnit()).childUnits(unitDTO.getChildUnits())
				.build();

		return unitEntity;
	}

}
