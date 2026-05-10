package com.example.demo.dto;

import java.time.LocalDate;

import lombok.Data;
@Data
public class PlayerDto {
	 private String name;
     private int age;
     private LocalDate dateOfBirth;
     private String email;
     private String mobileNo;
     private String state;
     private String city;
     private String sport;
	 public String getName() {
		 return name;
	 }
	
}
