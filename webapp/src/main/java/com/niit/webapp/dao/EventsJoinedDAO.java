package com.niit.webapp.dao;

import java.util.List;

import com.niit.webapp.model.EventJoined;
import com.niit.webapp.model.Events;

public interface EventsJoinedDAO {
	
	List<EventJoined> list();
	List<EventJoined> list(int id);
	EventJoined getEventJoined(int id);
	boolean addEventJoined(EventJoined eventJoined);
	boolean updateEventJoined(EventJoined eventJoined);
	boolean deleteEventJoined(EventJoined eventJoined);

}
