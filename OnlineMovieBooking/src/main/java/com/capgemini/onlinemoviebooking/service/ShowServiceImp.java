package com.capgemini.onlinemoviebooking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.capgemini.onlinemoviebooking.dao.ShowDao;
import com.capgemini.onlinemoviebooking.entity.Show;

@Service
@Transactional
public class ShowServiceImp implements ShowService{

	@Autowired
	ShowDao showDao;
	
	@Override
	public Show addShow(Show show) {
		show.setNoOfAvailableSeats(show.getTotalNoOfSeats());
		return showDao.save(show);
		
	}

	@Override
	public Boolean deleteShow(Integer showId) {
		showDao.deleteById(showId);
		
		return true;
	}

	@Override
	public List<Show> showAllShow() {
		return showDao.findAll();
		
	}
	@Override
	public Show showShow(int id) {
		Optional<Show> show=showDao.findById(id);
		if(show.isPresent())
			return show.get();
		
		else 
			return null;
	}

	
}
