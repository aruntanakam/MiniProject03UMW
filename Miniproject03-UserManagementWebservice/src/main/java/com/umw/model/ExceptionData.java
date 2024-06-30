package com.umw.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionData {
	
	private String errorMessage;
	
	private LocalDateTime timeStamp;

}
