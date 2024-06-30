package com.umw.service;

import java.util.List;

import com.umw.model.ActivateUserDetails;
import com.umw.model.LoginUserInput;
import com.umw.model.UserRegisterDetails;

import jakarta.mail.MessagingException;

public interface IUserManagementService {
	
	public String registeruser(UserRegisterDetails details) throws MessagingException;
	
	public ActivateUserDetails getUserInfo(String mailId);
	
	public String activateUser(ActivateUserDetails details);
	
	public String validateUserLogin(LoginUserInput input) throws MessagingException;
	
	public String recoverPassword(String emailId) throws MessagingException;
	
	public List<UserRegisterDetails> getUsers();
	
	public UserRegisterDetails getUserDetails(String mailId);
	
	public String deleteUser(String mailId);
	
	public String updateUser(UserRegisterDetails details);
	
	public String alterStatus(String mailId,String status);
	
	
	
	
	

}
