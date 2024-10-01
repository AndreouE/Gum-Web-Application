package com.example.GumSystem.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.GumSystem.Model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member,Integer> {
	
	public Member findById(int id);
	
}



