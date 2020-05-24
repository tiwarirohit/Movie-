package com.capgemini.onlinemoviebooking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.capgemini.onlinemoviebooking.exception.ErrorInfo;
import com.capgemini.onlinemoviebooking.exception.ShowException;

@RestControllerAdvice
public class ShowControllerAdvice {
	@ExceptionHandler(value = {ShowException.class})
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public ErrorInfo handleException1(Exception ex) {
		return new ErrorInfo(ex.getMessage());
	}

}
