package com.GGB.member.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.GGB.member.domain.Member;

@Repository
public interface LoginRepository extends JpaRepository<Member, Long>{
	
	Optional<Member> findByid(String loginId);
	boolean existsByid(String loginId);

}
