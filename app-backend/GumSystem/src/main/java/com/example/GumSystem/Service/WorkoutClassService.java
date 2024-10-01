package com.example.GumSystem.Service;

import java.util.List;


import com.example.GumSystem.Model.WorkoutClass;

public interface WorkoutClassService {
	public WorkoutClass createClass(WorkoutClass newClass);
	public void deleteClassById(int id);
	public List<WorkoutClass>findAll();
	
}
