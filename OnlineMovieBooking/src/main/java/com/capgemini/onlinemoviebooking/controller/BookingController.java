package com.capgemini.onlinemoviebooking.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.capgemini.onlinemoviebooking.entity.Booking;
import com.capgemini.onlinemoviebooking.entity.Show;
import com.capgemini.onlinemoviebooking.exception.BookingException;
import com.capgemini.onlinemoviebooking.service.BookingService;

@CrossOrigin
@RestController
public class BookingController {
	
	@Autowired
	BookingService bookingService;
	
	@GetMapping("/getShows")
	public ResponseEntity<List<Show>> getAllShows()
	{
		
		return new ResponseEntity<List<Show>>(bookingService.showShows(),HttpStatus.OK);
	}
	
	@GetMapping("/getShow/{id}")
	public ResponseEntity<Show> getShow(@PathVariable int id)
	{
		Show result=bookingService.showShow(id);
		
		if(result!=null)
			return new ResponseEntity<Show>(result,HttpStatus.OK);
		throw new BookingException("Show Not Found");
	}
	
	@PostMapping("/addShow")
	public ResponseEntity<String> addShow(@Valid @RequestBody Show show, BindingResult br) throws BookingException{
		String err="";
		if(br.hasErrors()) {
			List<FieldError> errors= br.getFieldErrors();
			for(FieldError error:errors)
				err +=error.getDefaultMessage() +"<br/>";
			throw new BookingException(err);
		}
		try {
			bookingService.addShow(show);
			return new ResponseEntity<String>("Show added", HttpStatus.OK);
			
		}
		catch(DataIntegrityViolationException ex) {
			throw new BookingException("Show already exists");
		}
	}
	
	@PostMapping("/addBooking")
	public ResponseEntity<String> addBooking(@Valid @RequestBody Booking booking, BindingResult br) throws BookingException{
		String err="";
		if(br.hasErrors()) {
			List<FieldError> errors= br.getFieldErrors();
			for(FieldError error:errors)
				err +=error.getDefaultMessage() +"<br/>";
			throw new BookingException(err);
		}
		try {
			bookingService.addBooking(booking);
			return new ResponseEntity<String>("Booking Confirmed, Thank You!", HttpStatus.OK);
			
		}
		catch(DataIntegrityViolationException ex) {
			throw new BookingException("Booking Rejected");
		}
	}
	
	@DeleteMapping("/CancleBooking/{id}")
	public ResponseEntity<String> cancleBooking(@PathVariable int id){
		Boolean result=bookingService.cancleBooking(id);
		if(result) {
			return new ResponseEntity<String>("Booking Cancled", HttpStatus.ACCEPTED);
		}
		else
			throw new BookingException("Booking Cancelation got rejected");
	}
	
	@GetMapping("/getBookings")
	public ResponseEntity<List<Booking>> getBookings(){
		return new ResponseEntity<List<Booking>>(bookingService.showBookings(),HttpStatus.OK);
	}
	
	@GetMapping("/getBooking/{bookingId}")
	public ResponseEntity<Booking> getBooking(@PathVariable int bookingId){
		Booking result=bookingService.showBooking(bookingId);
		
		if(result!=null)
			return new ResponseEntity<Booking>(result,HttpStatus.OK);
		throw new BookingException("Booking Not Found");
	}

}
