package com.example.GumSystem.Model;

import jakarta.persistence.*;


//Map the table in the PostgreSQL database
@Entity
@Table(name = "members")
public class Member {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MemberId")
	private int id;
	
	@Column(name="firstName")
	private String firstName;
	
	@Column(name="lastName")
	private String lastName;
	
	@Column(name="yearOfBirth")
	private int yearOfBirth;
	
	@Column(name="dateOfRegistration")
	private int dateOfRegistration;
	
	@Column(name="phoneNumber")
	private long phoneNumber;
	
	
	public Member() {}
	
    
	public Member(String firstName, String lastName, int yearOfBirth, int dateOfRegistration, long phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.yearOfBirth = yearOfBirth;
		this.dateOfRegistration = dateOfRegistration;
		this.phoneNumber = phoneNumber;
		
	}


	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id=id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName=firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName=lastName;
	}
	
	public int getYearOfBirth() {
		return yearOfBirth;
	}
	
	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth=yearOfBirth;
	}
	
	public int getDateOfRegistration() {
		return dateOfRegistration;
	}
	
	public void setDateOfRegistration(int dateOfRegistration) {
		this.dateOfRegistration=dateOfRegistration;
	}
	
	public long getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber=phoneNumber;
	}
	

}
