package com.example.GumSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.GumSystem.Model.Instructor;
import com.example.GumSystem.Model.Member;
import com.example.GumSystem.Service.InstructorServiceImp;

@RestController
@RequestMapping("/instructors")
public class InstructorController {
	@Autowired()
	private InstructorServiceImp instructorService;
	
	public InstructorController() {}
	
	public InstructorController(InstructorServiceImp service) {
		this.instructorService=service;
	}
	
	
	@CrossOrigin(origins="http://localhost:3000")
	@GetMapping("/AllInstructors")
	public List <Instructor> allInstructor(){
		return instructorService.findAll();
	}
	
	
	@CrossOrigin(origins="http://localhost:3000")
	@PostMapping("/createInstructor")
	public ResponseEntity<Instructor> createInstructor(@RequestBody Instructor newInstructor){
      return ResponseEntity.ok(instructorService.createInstructor(newInstructor));
	}
	
	
	@CrossOrigin(origins="http://localhost:3000")
	@GetMapping("{id}")
	public ResponseEntity<Instructor> getInstructorById(@PathVariable int id) {
	      Instructor instructor = instructorService.findInstructorById(id);
	      if (instructor != null) {
	         return ResponseEntity.ok(instructor);
	       } else {
	           return ResponseEntity.notFound().build();
	       }
	    	
	   }
	
	@CrossOrigin(origins="http://localhost:3000")
    @PutMapping("{id}")
    public ResponseEntity<Instructor> updateInstructor(@PathVariable int id, @RequestBody Instructor updatedInstructor) {
		Instructor instructor = instructorService.updateInstructor(id, updatedInstructor);
          if(instructor  !=null) {
        	  return ResponseEntity.ok(instructor );
          }
          else {
            return ResponseEntity.notFound().build();
        }
    }
    
   
	@CrossOrigin(origins="http://localhost:3000")
    @DeleteMapping("/{id}")
   public ResponseEntity <Void> deleteInstructor(@PathVariable int id) {
       try {
    	   instructorService.deleteInstructorById(id);
           return ResponseEntity.noContent().build();
       } catch (RuntimeException e) {
           return ResponseEntity.notFound().build();
        }
    }
}


