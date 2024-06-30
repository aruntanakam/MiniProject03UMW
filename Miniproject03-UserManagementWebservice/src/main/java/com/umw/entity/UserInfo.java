package com.umw.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	@Column(length=30)
	private String name;
	
	@Column(length=30,unique = true,nullable = false)
	private String emailId;
	
	@Column(length=10)
	private String gender;
	
	private Long contactNumber;
	
	@Column(length=30)
	private String aadharNumber;
	
	@Column(length=10)
	private String status;
	
	@Column(length=30)
	private String password;
	
	private LocalDate dob;
	
	@CreationTimestamp
	private LocalDateTime creationTimeStamp;
	
	@UpdateTimestamp
	private LocalDateTime updateTimeStamp;
	
	private String createdBy;
	
	private String updateBy;

}
