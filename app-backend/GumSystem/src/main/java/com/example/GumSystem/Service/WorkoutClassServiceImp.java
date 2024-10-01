package com.example.GumSystem.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.GumSystem.Model.WorkoutClass;
import com.example.GumSystem.Repository.WorkoutClassRepository;

import jakarta.transaction.Transactional;



@Service
public class WorkoutClassServiceImp implements WorkoutClassService {
	
	@Autowired
    private WorkoutClassRepository workoutClassRepository;

   public WorkoutClassServiceImp() {}
   
   public WorkoutClassServiceImp(WorkoutClassRepository  rep) {
	   this.workoutClassRepository=rep;
   }
   
	
	
	@Override
	public WorkoutClass createClass(WorkoutClass classParam){
        WorkoutClass createdClass = new WorkoutClass();
        
        createdClass.setClassName(classParam.getClassName());
        createdClass.setCostOfClass(classParam.getCostOfClass());
        
        return workoutClassRepository.save(createdClass);
    
	}
	
	@Transactional
	public List<WorkoutClass> findAll() {
		return workoutClassRepository.findAll();
	}

	

	@Override
	public void deleteClassById(int id) {
		if(workoutClassRepository.existsById(id)) {
			workoutClassRepository.deleteById(id);
		}
	}


}
