package com.example.GumSystem.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class WorkoutClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="costOfClass")
    private Double costOfClass; 
    
    @Column(name="className")
    private String  className; 

    
    public WorkoutClass() {}

	public WorkoutClass(Double costOfClass, String className) {
		this.costOfClass = costOfClass;
		this.className = className;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getCostOfClass() {
		return costOfClass;
	}

	public void setCostOfClass(Double costOfClass) {
		this.costOfClass = costOfClass;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	

   

}