package com.example.GumSystem.Service;

import java.util.List;

import com.example.GumSystem.Model.Instructor;


public interface InstructorsService {
	public Instructor createInstructor(Instructor newInstructor);
	public Instructor updateInstructor(int id,Instructor updatedInstructor);
	public void deleteInstructorById(int id);
	
	public Instructor findInstructorById(int id);
	public List<Instructor> findAll();

}
