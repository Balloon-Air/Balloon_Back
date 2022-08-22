package com.balloon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.balloon.entity.Unit;

@Repository
public interface UnitRepository extends JpaRepository<Unit, String> {

	public Unit findUnitByUnitCode(String unitCode);

	public void deleteUnitByUnitCode(String unitCode);
}
