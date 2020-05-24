package com.capgemini.onlinemoviebooking.service;

import java.util.List;

import com.capgemini.onlinemoviebooking.entity.Booking;
import com.capgemini.onlinemoviebooking.entity.Show;


public interface BookingService {
	List<Show> showShows();
	public Show showShow(int id);
	public Show addShow(Show show);
	
	public Booking addBooking(Booking booking);
	public Boolean cancleBooking(int bookingId);
	public List<Booking> showBookings();
	public Booking showBooking(int bookingId);


}
