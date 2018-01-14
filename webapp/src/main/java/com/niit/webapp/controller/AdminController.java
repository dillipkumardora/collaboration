package com.niit.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.webapp.dao.BlogDAO;
import com.niit.webapp.dao.EventsDAO;
import com.niit.webapp.dao.ForumRequestDAO;
import com.niit.webapp.dao.JobDAO;
import com.niit.webapp.dao.UserDAO;
import com.niit.webapp.model.Blog;
import com.niit.webapp.model.Events;
import com.niit.webapp.model.ForumRequest;
import com.niit.webapp.model.Job;
import com.niit.webapp.model.User;

@RestController
public class AdminController {
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	BlogDAO blogDAO;
	
	@Autowired
	JobDAO jobDAO;
	
	@Autowired
	EventsDAO eventsDAO;
	
	
		//Method for fetching approved user list by status
		@RequestMapping(value = {"/user/manage/list"}, method = RequestMethod.GET)
		public ResponseEntity<List<User>> fetchApprovedUsers() {
				System.out.println("fetching list of approved users");
				List<User> user = userDAO.list("APPROVED");
				return new ResponseEntity<List<User>>(user, HttpStatus.OK);
		}
		
		//Method for fetching approved blog list by status
		@RequestMapping(value = {"/blog/manage/list"}, method = RequestMethod.GET)
		public ResponseEntity<List<Blog>> fetchApprovedBlogs() {
				System.out.println("fetching list of approved blogs");
				List<Blog> blog = blogDAO.getBlogsByStatus("APPROVED");
				return new ResponseEntity<List<Blog>>(blog, HttpStatus.OK);
		}
		
		//Method for fetching approved jobs list by status
		@RequestMapping(value = {"/job/manage/list"}, method = RequestMethod.GET)
		public ResponseEntity<List<Job>> fetchApprovedJobs() {
				System.out.println("fetching list of approved jobs");
				List<Job> job = jobDAO.list("APPROVED");
				return new ResponseEntity<List<Job>>(job, HttpStatus.OK);
		}
		
		//Method for changing user role
		@RequestMapping(value = {"/user/role/manage"}, method = RequestMethod.POST)
		public ResponseEntity<User> changeUserRole(@RequestBody User user) {
				System.out.println("changing user role");
				userDAO.updateUser(user);
				return new ResponseEntity<User>(user, HttpStatus.OK);
				}
		
		//Method for fetching approved event list by status
		@RequestMapping(value = {"/event/manage/list"}, method = RequestMethod.GET)
		public ResponseEntity<List<Events>> fetchApprovedEvents() {
				System.out.println("fetching list of approved events");
				List<Events> events = eventsDAO.getEventsByStatus("APPROVED");
						return new ResponseEntity<List<Events>>(events, HttpStatus.OK);
				}
		
		
}
