package com.klu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.klu.entity.Participant;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
	@Query("select count(p) from Participant p")
	public long noOfParticipants();
	@Query("select S from  Participant S where S.eid=:eid")
	public List<Participant> fetchAllById(@Param("eid") Long eid);
	
	@Query("select count(S) from Participant S where S.contactno=:cno")
	public Long validateByMobileNo(@Param("cno") String cno);
}