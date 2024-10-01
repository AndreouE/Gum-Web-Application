package com.example.GumSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.GumSystem.Model.Member;
import com.example.GumSystem.Model.WorkoutClass;
import com.example.GumSystem.Service.MemberServiceImp;
import com.example.GumSystem.Service.WorkoutClassServiceImp;

@RestController
@RequestMapping("/WorkoutClasses")
public class WorkoutClassController {
	
	@Autowired()
	private WorkoutClassServiceImp workoutClassService;
	
	public WorkoutClassController() {}
	
	public WorkoutClassController(WorkoutClassServiceImp workoutService) {
		this.workoutClassService=workoutService;
	}
	
	@CrossOrigin(origins="http://localhost:3000")
	@GetMapping("/AllClasses")
	public List <WorkoutClass> findAllMembers(){
		return workoutClassService.findAll();
	}
	
	@CrossOrigin(origins="http://localhost:3000")
	@PostMapping("/create")
	public ResponseEntity<WorkoutClass> createClass(@RequestBody WorkoutClass newClass){
      
      return ResponseEntity.ok(workoutClassService.createClass(newClass));
	}
	
	
	@CrossOrigin(origins="http://localhost:3000")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteClass(@PathVariable int id) {
		try {
			workoutClassService.deleteClassById(id);
	        return ResponseEntity.noContent().build();
	    } catch (RuntimeException e) {
	          return ResponseEntity.notFound().build();
	        }
	    }
	

}
