package com.umw.model;

import lombok.Data;

@Data 
public class RecoverPasswordInput {

	private String userName;
	
	private String emailId;
}
