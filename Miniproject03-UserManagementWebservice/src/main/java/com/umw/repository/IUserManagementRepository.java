package com.umw.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umw.entity.UserInfo;
import com.umw.model.ActivateUserDetails;

public interface IUserManagementRepository extends JpaRepository<UserInfo, Integer> {
	
	public UserInfo findByEmailId(String emailId);

}
