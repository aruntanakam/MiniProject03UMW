package com.umw.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserRegisterDetails {
  
	private Integer userId;
	
	private String name;

	private String emailId;

	private String gender;

	private Long contactNumber;

	private String aadharNumber;

	private LocalDate dob;
	
	private String status;

}
