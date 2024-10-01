package com.example.GumSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.GumSystem.Model.WorkoutClass;


@Repository
public interface WorkoutClassRepository extends JpaRepository<WorkoutClass, Integer> {
	public WorkoutClass findById(int id);
}


