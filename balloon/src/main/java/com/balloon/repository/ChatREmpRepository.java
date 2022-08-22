package com.balloon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.balloon.entity.ChatroomEmployee;
import com.balloon.entity.ChatroomEmployeeId;

@Repository
public interface ChatREmpRepository extends JpaRepository<ChatroomEmployee, ChatroomEmployeeId> {

	public List<ChatroomEmployee> findAllByChatroomIdChatroomId(Long chatroomId);

	@Query(value = "SELECT * " + "FROM chatroom_employee " + "WHERE emp_id= :emp_id "
			+ "AND chatroom_id IN (SELECT chatroom_id " + "               FROM chatroom_employee "
			+ "               WHERE emp_id=\"Y0000001\");", nativeQuery = true)
	public ChatroomEmployee findChatroomEmployeeByempId(@Param("emp_id") String empId);
}
