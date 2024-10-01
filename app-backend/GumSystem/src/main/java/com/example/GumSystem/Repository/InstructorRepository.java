package com.example.GumSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.GumSystem.Model.Instructor;


@Repository
public interface InstructorRepository extends JpaRepository<Instructor,Integer> {
	
	public Instructor findById(int id);
	
}
