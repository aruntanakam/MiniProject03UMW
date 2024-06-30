package com.umw.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.umw.model.ExceptionData;

@RestControllerAdvice
public class ErrorController {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ExceptionData> handleException(Exception e)
	{
		ExceptionData data=ExceptionData.builder().errorMessage(e.getMessage()).timeStamp(LocalDateTime.now()).build();
		
		return new ResponseEntity<ExceptionData>(data,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
