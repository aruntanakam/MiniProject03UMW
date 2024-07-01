package com.umw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umw.model.ActivateUserDetails;
import com.umw.model.LoginUserInput;
import com.umw.model.UserRegisterDetails;
import com.umw.service.IUserManagementService;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/umw/api")
public class UserManagementController {
	
	@Autowired
	private IUserManagementService service;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@Parameter(schema = @Schema(implementation = UserRegisterDetails.class)) @RequestBody UserRegisterDetails details) throws MessagingException
	{
		return new ResponseEntity<>(service.registeruser(details),HttpStatus.OK);
	}
	
	
	@GetMapping("/activationDetails/{mailId}")
	public ResponseEntity<ActivateUserDetails> getActivationDetails(@PathVariable String mailId)
	{
		return new ResponseEntity<>(service.getUserInfo(mailId),HttpStatus.OK);
	}
	
	@PostMapping("/activateAccount")
	public ResponseEntity<String> activateUserAccount(@RequestBody ActivateUserDetails details)
	{
		return new ResponseEntity<String>(service.activateUser(details),HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginUserInput input) throws MessagingException
	{
		return new ResponseEntity<>(service.validateUserLogin(input),HttpStatus.OK);
	}
	
	@GetMapping("/recoverpwd/{mailId}")
	public ResponseEntity<String> recoverPassword(@PathVariable String mailId) throws MessagingException
	{
		return new ResponseEntity<String>(service.recoverPassword(mailId),HttpStatus.OK);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<UserRegisterDetails>> getUsers()
	{
		return new ResponseEntity<>(service.getUsers(),HttpStatus.OK);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<UserRegisterDetails> getUser(@PathVariable Integer id)
	{
		return new ResponseEntity<>(service.getUserDetails(id),HttpStatus.OK);
	}
	
	@PutMapping("/updateUser")
	public ResponseEntity<String> updateUser(@RequestBody UserRegisterDetails details)
	{
		return new ResponseEntity<String>(service.updateUser(details),HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer id )
	{
		return new ResponseEntity<>(service.deleteUser(id),HttpStatus.OK);
	}
	
	@PutMapping("/updateStatus/{id}/{status}")
	public ResponseEntity<String> alterStatus(@PathVariable Integer id,@PathVariable String status)
	{
		return new ResponseEntity<String>(service.alterStatus(id, status),HttpStatus.OK);
	}

}
