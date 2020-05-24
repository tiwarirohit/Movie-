package com.capgemini.onlinemoviebooking.service;

import java.util.List;

import com.capgemini.onlinemoviebooking.entity.Show;

public interface ShowService {
	public Show addShow(Show show);
	public Boolean deleteShow(Integer ShowId);
	
	public List<Show> showAllShow();
	public Show showShow(int id);

}
