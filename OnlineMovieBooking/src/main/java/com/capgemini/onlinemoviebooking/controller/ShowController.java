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
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.capgemini.onlinemoviebooking.entity.Show;
import com.capgemini.onlinemoviebooking.exception.ShowException;
import com.capgemini.onlinemoviebooking.service.ShowService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class ShowController {
	@Autowired
	ShowService showService;
	
	//@CrossOrigin
	/*
	 * @PostMapping("/addShow") public ResponseEntity<String>
	 * addShow(@Valid @RequestBody Show show, BindingResult br)throws ShowException{
	 * String err=""; if(br.hasErrors()) { List<FieldError> errors=
	 * br.getFieldErrors(); for(FieldError error:errors) err
	 * +=error.getDefaultMessage() +"<br/>"; throw new ShowException(err); } try {
	 * showService.addShow(show); return new ResponseEntity<String>("Show added",
	 * HttpStatus.OK); } catch(DataIntegrityViolationException ex) { throw new
	 * ShowException("ID already exists"); } }
	 */
	
	//@CrossOrigin
	@DeleteMapping("/deleteShow/{id}")
	public ResponseEntity<Boolean> deleteShow(@PathVariable int id)
	{
		showService.deleteShow(id);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	//@CrossOrigin
	@GetMapping("/viewAllShow")
	public ResponseEntity<List<Show>> viewAllShow(){
		List<Show> listOfShow=showService.showAllShow();
		return new ResponseEntity<List<Show>>(listOfShow,HttpStatus.OK);
		
	}
	
	@GetMapping("/viewShow/{id}")
	public ResponseEntity<Show> viewShow(@PathVariable int id) throws ShowException{
		Show show=showService.showShow(id);
		if(show!=null)
			return new ResponseEntity<Show>(show, HttpStatus.OK);
		else
			throw new ShowException("ID doesn't exists");
	}
	

}
