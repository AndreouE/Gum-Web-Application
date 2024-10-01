package com.example.GumSystem.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.GumSystem.Model.Instructor;
import com.example.GumSystem.Repository.InstructorRepository;

import jakarta.transaction.Transactional;

@Service
public class InstructorServiceImp implements InstructorsService {

	@Autowired()
	private InstructorRepository instructorRepository;
	
	
	public InstructorServiceImp(InstructorRepository instructorRep) {
		
		this.instructorRepository = instructorRep;
	}
	
	
	public InstructorServiceImp() {}
	
	
	
	@Override
	@Transactional
	public Instructor createInstructor(Instructor newInstructorParam) {
		Instructor newInstructor=new Instructor();
		newInstructor.setFirstName(newInstructorParam.getFirstName());
		newInstructor.setLastName(newInstructorParam.getLastName());
		newInstructor.setPhoneNumber(newInstructorParam.getPhoneNumber());
		newInstructor.setSpecialty(newInstructorParam.getSpecialty());
		return instructorRepository.save(newInstructor);
	}

	@Override
	@Transactional
	public Instructor updateInstructor(int id, Instructor updatedInstructor) {
		Instructor existingInstructor = instructorRepository.findById(id);
		if(instructorRepository.existsById(id)) {
			
			existingInstructor.setFirstName(updatedInstructor.getFirstName());
			existingInstructor.setLastName(updatedInstructor.getLastName());
			existingInstructor.setPhoneNumber(updatedInstructor.getPhoneNumber());
			existingInstructor.setSpecialty(updatedInstructor.getSpecialty());
			
	       
	        return instructorRepository.save( existingInstructor);
		}
		return null;
	       
	}

	@Override
	@Transactional
	public void deleteInstructorById(int id) {
		if(instructorRepository.existsById(id)) {
			instructorRepository.deleteById(id);
		}

	}

	@Override
	@Transactional
	public Instructor findInstructorById(int id) {
		
		return instructorRepository.findById(id);
	}

	@Override
	@Transactional
	public List<Instructor> findAll() {
		
		return instructorRepository.findAll();
	}

}
