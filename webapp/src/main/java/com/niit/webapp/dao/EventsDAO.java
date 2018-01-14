package com.niit.webapp.dao;

import java.util.List;

import com.niit.webapp.model.Events;

public interface EventsDAO {
	
	List<Events> list();
	List<Events> mainList();
	List<Events> getEventsByStatus(String status);
	List<Events> getUserEvents(int id);
	Events getEvent(int id);
	boolean addEvent(Events event);
	boolean updateEvent(Events event);
	boolean deleteEvent(Events event);

}
