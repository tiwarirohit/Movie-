package com.capgemini.onlinemoviebooking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.onlinemoviebooking.dao.BookingDao;
import com.capgemini.onlinemoviebooking.dao.ShowDao;
import com.capgemini.onlinemoviebooking.entity.Booking;
import com.capgemini.onlinemoviebooking.entity.BookingState;
import com.capgemini.onlinemoviebooking.entity.Show;
import com.capgemini.onlinemoviebooking.exception.BookingException;


@Service
@Transactional
public class BookingServiceImpl implements BookingService {

	@Autowired
	ShowDao showDao;
	@Autowired 
	BookingDao bookingDao;
	@Override
	public List<Show> showShows() {
		List<Show> listOfShows=showDao.findAll();
		
		return listOfShows;
	}
	
	@Override
	public Show showShow(int id) {
		Optional<Show> optionalShow=showDao.findById(id);
		if(optionalShow.isPresent())
			return optionalShow.get();
		else 
			return null;
	}

	@Override
	public Show addShow(Show show) {
		return showDao.save(show);
	}

	@Override
	public Booking addBooking(Booking booking) {
		int showId=booking.getShowRef().getShowId();
		Optional<Show> optionalShow=showDao.findById(showId);
		Show show;
		if(optionalShow.isPresent()) {
			show=optionalShow.get();
			int noOfAvailableSeats=show.getNoOfAvailableSeats();
			int noOfSeats=booking.getNoOfSeats();
			int remainingSeatCount=noOfAvailableSeats-noOfSeats;
			if(remainingSeatCount>=0)
				//showDao.updateNoOfAvailableSeats(remainingSeatCount, booking.getShowRef().getShowId());
				show.setNoOfAvailableSeats(remainingSeatCount);
			if(remainingSeatCount<0)
				throw new BookingException(noOfSeats+" Seats are not available");
		}
			
		return bookingDao.save(booking);
	}

	@Override
	public Boolean cancleBooking(int bookingId) throws BookingException {
		Optional<Booking> optionalBooking=bookingDao.findById(bookingId);
		Booking booking;
		if(optionalBooking.isPresent()) {
			booking=optionalBooking.get();
		}
		else
			throw new BookingException("Booking Not Found");
		
		//bookingDao.updateBookingStatus(BookingState.Cancelled.toString(), bookingId);
		booking.setBookingStatus(BookingState.Cancelled.toString());
		if(booking!=null) {
			int showId=booking.getShowRef().getShowId();
			Show show;
			Optional<Show> optionalShow=showDao.findById(showId);
			if(optionalShow.isPresent()) {
				show=optionalShow.get();
				int count=show.getNoOfAvailableSeats()+booking.getNoOfSeats();
				//showDao.updateNoOfAvailableSeats(count, showId);
				show.setNoOfAvailableSeats(count);
			}
		}
		return true;
	}

	@Override
	public List<Booking> showBookings() {
		
		return bookingDao.findAll();
	}

	@Override
	public Booking showBooking(int bookingId) {
		Optional<Booking> optionalBooking=bookingDao.findById(bookingId);
		if(optionalBooking.isPresent())
			return optionalBooking.get();
		else 
			return null;
	}
	
	
	

}
