package com.example.GumSystem.Service;

import java.util.List;

import com.example.GumSystem.Model.Member;

public interface MemberService {
	public Member createMember(Member newMember);
	public Member updateMember(int id,Member updatedMember);
	public void deleteMemberById(int id);
	
	public Member findMemberById(int id);
	public List<Member> findAll();

}
