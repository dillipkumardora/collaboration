package com.codehub.webapp.test;

import java.time.LocalDate;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.webapp.dao.EventsDAO;
import com.niit.webapp.dao.EventsJoinedDAO;
import com.niit.webapp.model.EventJoined;
import com.niit.webapp.model.Events;

import junit.framework.Assert;

public class EventTestCase {

	@Autowired
	Events events;
	
	@Autowired
	EventsDAO eventsDAO;
	
	@Autowired
	EventJoined eventJoined;
	
	@Autowired
	EventsJoinedDAO eventsJoinedDAO;
	
	@Autowired
	AnnotationConfigApplicationContext context;
	
	public EventTestCase() {
		super();
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.webapp");
		context.refresh();
		eventsDAO =  (EventsDAO) context.getBean("eventsDAO");
		events = (Events) context.getBean("events");
		eventsJoinedDAO = (EventsJoinedDAO) context.getBean("eventsJoinedDAO");
		eventJoined = (EventJoined) context.getBean("eventJoined");
	}
	
	@Test
	public void addEvent() {
		events.setId(101);
		events.setName("Test");
		events.setStartDate(LocalDate.parse("2007-02-10"));
		events.setEndDate(LocalDate.parse("2007-02-10"));
		events.setPostDate(LocalDate.parse("2007-02-10"));
		events.setDescription("This is a event");
		events.setUserId(102);
		events.setUsername("dillip");
		events.setVenue("puri");
		Assert.assertEquals(true, eventsDAO.addEvent(events));
		
	}
	@Ignore
	@Test
	public void updateEvents() {
		events = eventsDAO.getEvent(3);
		events.setVenue("Pune");
		Assert.assertEquals(true, eventsDAO.updateEvent(events));
	}
	@Ignore
	@Test
	public void getAllEventsTestCase() {
		
		int size = eventsDAO.list().size();
		Assert.assertEquals(1, size);
	}
	@Ignore
	@Test
	public void deleteEvents() {
		events = eventsDAO.getEvent(3);
		Assert.assertEquals(true, eventsDAO.deleteEvent(events));
	}
	
}
