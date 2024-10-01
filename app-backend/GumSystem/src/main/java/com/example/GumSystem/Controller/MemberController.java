package com.example.GumSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.GumSystem.Model.Member;
import com.example.GumSystem.Service.MemberService;
import com.example.GumSystem.Service.MemberServiceImp;


@RestController
@RequestMapping("/members")
public class MemberController {
	
	@Autowired()
	private MemberServiceImp memberService;

	public MemberController() {}

	public MemberController(MemberServiceImp service) {
		this.memberService = service;
	}
	
	
	@CrossOrigin(origins="http://localhost:3000")
	@GetMapping("/Allmembers")
	public List <Member> findAllMembers(){
		return memberService.findAll();
	}
	
	
	@CrossOrigin(origins="http://localhost:3000")
	@PostMapping("/create")
	public ResponseEntity<Member> createMember(@RequestBody Member newMember){
      
      return ResponseEntity.ok(memberService.createMember(newMember));
	}
	
	

    // Get a single Member by ID 
	@CrossOrigin(origins="http://localhost:3000")
    @GetMapping("{id}")
   public ResponseEntity<Member> getMemberById(@PathVariable int id) {
       Member member = memberService.findMemberById(id);
      if (member != null) {
         return ResponseEntity.ok(member);
       } else {
           return ResponseEntity.notFound().build();
       }
    	
   }
   
   
   // Update a Member by ID 
	@CrossOrigin(origins="http://localhost:3000")
    @PutMapping("{id}")
    public ResponseEntity<Member> updateMember(@PathVariable int id, @RequestBody Member updatedMember) {
          Member member = memberService.updateMember(id, updatedMember);
          if(member !=null) {
        	  return ResponseEntity.ok(member);
          }
          else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Delete a Member by ID 
	@CrossOrigin(origins="http://localhost:3000")
    @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteMember(@PathVariable int id) {
       try {
            memberService.deleteMemberById(id);
           return ResponseEntity.noContent().build();
       } catch (RuntimeException e) {
           return ResponseEntity.notFound().build();
        }
    }
	
	
	
	
}


