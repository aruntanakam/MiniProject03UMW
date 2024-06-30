package com.umw.model;

import lombok.Data;

@Data
public class ActivateUserDetails {
	
	private String emailId;
	
    private String password;
    
    private String tempPassword;

}
