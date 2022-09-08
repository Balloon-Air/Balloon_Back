package com.balloon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.balloon.entity.Chatroom;

@Repository
public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {

   public Chatroom findChatroomByChatroomId(Long chatroomId);

}