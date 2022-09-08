package com.balloon.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.balloon.dto.ChatroomDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chatroom")
@ToString
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
public class Chatroom {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "chatroom_id")
   private Long chatroomId;

   @Column(name = "chatroom_name")
   private String chatroomName;

   @Column(name = "head_count")
   private Long headCount;

   @OneToMany(mappedBy = "chatroom")
   @JsonIgnore
   List<Chat> chat = new ArrayList<Chat>();

   @OneToMany(mappedBy = "chatroomId")
   @JsonIgnore
   List<ChatroomEmployee> chatroomEmployee = new ArrayList<ChatroomEmployee>();

   public ChatroomDTO toDTO(Chatroom chatroomEntity) {
      ChatroomDTO chatroomDTO = ChatroomDTO.builder().chatroomId(chatroomEntity.getChatroomId())
            .chatroomName(chatroomEntity.getChatroomName()).headCount(chatroomEntity.getHeadCount()).build();
      return chatroomDTO;
   }

   public void updateEntity(String chatroomName, Long headCount) {
      this.chatroomName = chatroomName;
      this.headCount = headCount;
   }

}