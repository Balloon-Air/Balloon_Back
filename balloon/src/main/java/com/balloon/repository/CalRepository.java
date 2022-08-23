package com.balloon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.balloon.entity.Cal;
import com.balloon.entity.Employee;

@Repository
public interface CalRepository extends JpaRepository<Cal, Long> {

	// 모든 스케쥴 찾기
	public List<Cal> findAll();

	// 스케쥴Id로 찾기
	public Cal findAllByscheduleId(Long scheduleId);

	// 스케쥴Id로 삭제하기
	public void deleteCalByscheduleId(Long scheduleId);

	// empId로 스케쥴 찾기
	public List<Cal> findAllByempId(Employee empId);

}
