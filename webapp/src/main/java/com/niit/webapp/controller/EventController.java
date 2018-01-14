package com.niit.webapp.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.webapp.dao.EventsDAO;
import com.niit.webapp.dao.EventsJoinedDAO;
import com.niit.webapp.dao.UserDAO;
import com.niit.webapp.model.EventJoined;
import com.niit.webapp.model.Events;
import com.niit.webapp.model.Job;
import com.niit.webapp.model.JobApplied;
import com.niit.webapp.model.User;

import antlr.debug.Event;

@RestController
public class EventController {

	@Autowired
	EventsDAO eventsDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	EventsJoinedDAO eventJoinedDAO;
	
	
	//Method for creating a new event
	
	@RequestMapping(value = {"/events/new"}, method = RequestMethod.POST)
	public ResponseEntity<Events> addEvents(@RequestBody Events events) {
		System.out.println("Adding events now");
		int id = events.getUserId();
		User user = userDAO.getUser(id);
		if( user.getRole().equals("Super_Admin") || user.getRole().equals("Admin") ) {
			events.setStatus("APPROVED");
		} else {
			events.setStatus("PENDING");
		}
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now(); 
		events.setPostDate(LocalDate.parse(dtf.format(now)));
		eventsDAO.addEvent(events);
		
		return new ResponseEntity<Events>(events, HttpStatus.OK);		
	}
	
	//Method for fetching event list by status
	@RequestMapping(value = {"/events/list/status"}, method = RequestMethod.GET)
	public ResponseEntity<List<Events>> fetchApprovedEvents(String status) {
		System.out.println("fetching list of events by status");
		List<Events> events = eventsDAO.getEventsByStatus("APPROVED");
		return new ResponseEntity<List<Events>>(events, HttpStatus.OK);
	}
	
	//Method for fetching user's jobs
	@RequestMapping(value = {"/user/events/list/{id}"}, method = RequestMethod.GET)
	public ResponseEntity<List<Events>> fetchUserEvents(@PathVariable("id") int id) {
		System.out.println("Fetching users events");
		List<Events> events = eventsDAO.getUserEvents(id);
		return new ResponseEntity<List<Events>>(events, HttpStatus.OK);
		}
	
	//Method to join event
	@RequestMapping(value = {"/event/join/{id}"}, method = RequestMethod.POST)
	public ResponseEntity<EventJoined> joinEvent(@PathVariable("id") int id,  @RequestBody Integer userId) {
			System.out.println("Applying for event");
			Events events= eventsDAO.getEvent(id);
			User user = userDAO.getUser(userId);
			EventJoined eventJoined= new EventJoined();
			eventJoined.setEvents(events);
			eventJoined.setUserId(userId);
			eventJoined.setUsername(user.getUsername());
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDateTime now = LocalDateTime.now(); 
			eventJoined.setJoinedDate(LocalDate.parse(dtf.format(now)));
			eventJoined.setStatus("APPROVED");
			eventJoinedDAO.addEventJoined(eventJoined);
			return new ResponseEntity<EventJoined>(eventJoined, HttpStatus.OK);	
		}
	

}
