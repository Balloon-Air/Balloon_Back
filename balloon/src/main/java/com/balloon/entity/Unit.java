package com.balloon.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Persistable;

import com.balloon.dto.UnitDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "unit")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Unit implements Persistable<String> {

	@Id
	@Column(name = "unit_code", length = 10)
	private String unitCode;

	@NotBlank
	@Column(name = "unit_name", length = 20)
	private String unitName;

	@NotNull
	@Column(length = 15)
	private String bell;

	@NotNull
	@Column(length = 15)
	private int prior;

	@ManyToOne
	@JoinColumn(name = "parent_unit", referencedColumnName = "unit_code")
	@JsonIgnore
	private Unit parentUnit;

	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parentUnit")
	private List<Unit> childUnits;

	public UnitDTO toDTO(Unit unitEntity) {
		UnitDTO unitDTO = UnitDTO.builder().unitCode(unitEntity.getUnitCode()).unitName(unitEntity.getUnitName())
				.bell(unitEntity.getBell()).prior(unitEntity.getPrior()).parentUnit(unitEntity.getParentUnit())
				.childUnits(unitEntity.getChildUnits()).build();

		return unitDTO;
	}

	@Override
	public String getId() {
		return unitCode;
	}

	@Override
	public boolean isNew() {
		return unitCode == null;
	}

	/* 부서 코드 외 정보 업데이트 할 때 */
	public void updateUnitInfo(UnitDTO unitDTO) {
		this.unitName = unitDTO.getUnitName();
		this.parentUnit = unitDTO.getParentUnit();
		this.bell = unitDTO.getBell();
	}

	/* 부서전화번호만 업데이트할 때 */
	// public void updateBell(UnitDTO unitDTO) {
	// this.bell = unitDTO.getBell();
	// }

	/* 부서전화번호와 부서명만 업데이트 할 때 */
	// public void updateUnitNameAndBell(UnitDTO unitDTO) {
	// this.unitName = unitDTO.getUnitName();
	// this.bell = unitDTO.getBell();
	// }

}