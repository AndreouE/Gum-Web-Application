package com.example.GumSystem.Model;
import jakarta.persistence.*;

//Map the table in the PostgreSQL database
@Entity
@Table(name = "Instructors")
public class Instructor {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
	private int id;
	
	@Column(name="firstName")
	private String firstName;
	
	@Column(name="lastName")
	private String lastName;
	
	@Column(name="specialty")
	private String specialty;
	
	@Column(name="phoneNumber")
	private long phoneNumber;

	public Instructor() {}
	
	public Instructor(String firstName, String lastName, String specialty, long phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.specialty = specialty;
		this.phoneNumber = phoneNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
