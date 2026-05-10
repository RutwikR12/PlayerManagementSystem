package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name="playerss")
public class Player {
     @Id
     @GeneratedValue (strategy=GenerationType.IDENTITY)
     private int id;
     private String name;
     private int age;
     private LocalDate dateOfBirth;
     private String email;
     private String mobileNo;
     private String state;
     private String city;
     private String sport;
     
     public void setName(String name) {
    	 this.name = name;
     }
     
     public String getName() {
    	 return name;
     }

	 public int getId() {
		 return id;
	 }

	 public void setId(int id) {
		 this.id = id;
	 }

	 public int getAge() {
		 return age;
	 }

	 public void setAge(int age) {
		 this.age = age;
	 }

	 public LocalDate getDateOfBirth() {
		 return dateOfBirth;
	 }

	 public void setDateOfBirth(LocalDate dateOfBirth) {
		 this.dateOfBirth = dateOfBirth;
	 }

	 public String getEmail() {
		 return email;
	 }

	 public void setEmail(String email) {
		 this.email = email;
	 }

	 public String getMobileNo() {
		 return mobileNo;
	 }

	 public void setMobileNo(String mobileNo) {
		 this.mobileNo = mobileNo;
	 }

	 public String getState() {
		 return state;
	 }

	 public void setState(String state) {
		 this.state = state;
	 }

	 public String getCity() {
		 return city;
	 }

	 public void setCity(String city) {
		 this.city = city;
	 }

	 public String getSport() {
		 return sport;
	 }

	 public void setSport(String sport) {
		 this.sport = sport;
	 }
     
     
     
    
}
