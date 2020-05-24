package com.capgemini.onlinemoviebooking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.capgemini.onlinemoviebooking.exception.BookingException;
import com.capgemini.onlinemoviebooking.exception.ErrorInfo;


@RestControllerAdvice
public class BookingControllerAdvice {
	@ExceptionHandler(value = {BookingException.class})
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public ErrorInfo handleException1(Exception ex) {
		return new ErrorInfo(ex.getMessage());
	}

}
