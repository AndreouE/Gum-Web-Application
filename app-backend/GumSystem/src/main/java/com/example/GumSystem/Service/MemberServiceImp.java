package com.example.GumSystem.Service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.GumSystem.Model.Member;

import com.example.GumSystem.Repository.MemberRepository;


import jakarta.transaction.Transactional;

@Service
public class MemberServiceImp implements MemberService {
	
	@Autowired()
	private MemberRepository memberRepository;
	
	
	public MemberServiceImp() {}
	
	public MemberServiceImp (MemberRepository rep ) {
		this.memberRepository=rep;
		
	}
	

	@Override
	@Transactional
	public Member createMember(Member newMemberParam) {
		Member newMember=new Member();
		newMember.setFirstName( newMemberParam.getFirstName());
		newMember.setLastName(newMemberParam.getLastName());
		newMember.setPhoneNumber(newMemberParam.getPhoneNumber());
		newMember.setYearOfBirth(newMemberParam.getYearOfBirth());
		newMember.setDateOfRegistration(newMemberParam.getDateOfRegistration());
		
		
		
		return memberRepository.save(newMember);

	}

	//@Override
	public Member updateMember(int id, Member updatedMember) {
		Member existingMember = memberRepository.findById(id);
		if(memberRepository.existsById(id)) {
			// Update the member's fields
	        existingMember.setFirstName(updatedMember.getFirstName());
	        existingMember.setLastName(updatedMember.getLastName());
	        existingMember.setDateOfRegistration(updatedMember.getDateOfRegistration());
	        existingMember.setYearOfBirth(updatedMember.getYearOfBirth());
	        existingMember.setPhoneNumber(updatedMember.getPhoneNumber());

	        
	        return memberRepository.save(existingMember);
		}
		return null;
	       
	}

	
	@Override
	@Transactional
	public void deleteMemberById(int id) {
		if(memberRepository.existsById(id)) {
			memberRepository.deleteById(id);
		}
	}
		

	@Override
	@Transactional
	public Member findMemberById(int id) {
		return memberRepository.findById(id);
	}

	@Override
	@Transactional
	public List<Member> findAll() {
		return memberRepository.findAll();
	}
	
	
}
