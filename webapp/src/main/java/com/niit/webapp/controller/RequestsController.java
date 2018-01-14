package com.niit.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
public class RequestsController {
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	JobDAO jobDAO;
	
	@Autowired
	EventsDAO eventsDAO;
	
	@Autowired
	ForumRequestDAO forumRequestDAO;
	
	@Autowired
	BlogDAO blogDAO;
	
	
			//Method for fetching pending user list by status
			@RequestMapping(value = {"/user/request/list"}, method = RequestMethod.GET)
			public ResponseEntity<List<User>> fetchPendingUsers() {
				System.out.println("fetching list of pending users");
				List<User> user = userDAO.list("PENDING");
				return new ResponseEntity<List<User>>(user, HttpStatus.OK);
			}
			
			
			//Method to change user registration status
			@RequestMapping(value = {"/user/request/approval/{id}"}, method = RequestMethod.POST)
			public ResponseEntity<User> changeStatus(@PathVariable("id") int id) {
					System.out.println("changing status");
					User user = new User();
					user = userDAO.getUser(id);
					user.setStatus("APPROVED");
					userDAO.updateUser(user);
					return new ResponseEntity<User>(user, HttpStatus.OK);
			}
			
			//Method for fetching list of all forum request with pending status
			@RequestMapping(value = {"/forum/request/list"}, method = RequestMethod.GET)
			public ResponseEntity<List<ForumRequest>> fetchForumRequests() {
				System.out.println("Method called");
				List<ForumRequest> forumsRequests = forumRequestDAO.list("PENDING");
				return new ResponseEntity<List<ForumRequest>>(forumsRequests, HttpStatus.OK);
			}
			
			//Method to change forum request status
			@RequestMapping(value = {"/forum/request/approval/{id}"}, method = RequestMethod.POST)
			public ResponseEntity<ForumRequest> changeFRStatus(@PathVariable("id") int id) {
				 	System.out.println("changing status");
					ForumRequest forumRequest = new ForumRequest();
					forumRequest = forumRequestDAO.getForumRequest(id);
					forumRequest.setStatus("APPROVED");
					forumRequestDAO.updateForumRequest(forumRequest);
							return new ResponseEntity<ForumRequest>(forumRequest, HttpStatus.OK);
					}

			//Method for fetching pending blog list by status
			@RequestMapping(value = {"/blog/request/list"}, method = RequestMethod.GET)
			public ResponseEntity<List<Blog>> fetchPendingBlogs() {
					System.out.println("fetching list of pending blogs");
					List<Blog> blog = blogDAO.getBlogsByStatus("PENDING");
						return new ResponseEntity<List<Blog>>(blog, HttpStatus.OK);
			}
			
			//Method to change blog request status
			@RequestMapping(value = {"/blog/request/approval/{id}"}, method = RequestMethod.POST)
			public ResponseEntity<Blog> changeBlogStatus(@PathVariable("id") int id) {
					System.out.println("changing blog status");
					Blog blog = null;
					blog = blogDAO.getBlog(id);
					blog.setStatus("APPROVED");
					blogDAO.updateBlog(blog);
					return new ResponseEntity<Blog>(blog, HttpStatus.OK);
			}
			
			//Method for fetching pending job list by status
			@RequestMapping(value = {"/job/request/list"}, method = RequestMethod.GET)
			public ResponseEntity<List<Job>> fetchPendingJobs() {
				System.out.println("fetching list of pending Jobs");
				List<Job> job = jobDAO.list("PENDING");
				return new ResponseEntity<List<Job>>(job, HttpStatus.OK);
			}
			
			//Method to approve jobs
			@RequestMapping(value = {"/job/request/approval/{id}"}, method = RequestMethod.POST)
			public ResponseEntity<Job> approveJobs(@PathVariable("id") int id) {
					System.out.println("approving jobs");
					Job job = null;
					job = jobDAO.getJob(id);
					job.setStatus("APPROVED");
					jobDAO.updateJob(job);
					return new ResponseEntity<Job>(job, HttpStatus.OK);
			}
			
			//Method for fetching pending event list by status
			@RequestMapping(value = {"/event/request/list"}, method = RequestMethod.GET)
			public ResponseEntity<List<Events>> fetchPendingEvents() {
				System.out.println("fetching list of pending Events");
				List<Events> events = eventsDAO.getEventsByStatus("PENDING");
				return new ResponseEntity<List<Events>>(events, HttpStatus.OK);
			}
			
			//Method to approve events
			@RequestMapping(value = {"/event/request/approval/{id}"}, method = RequestMethod.POST)
			public ResponseEntity<Events> approveEvents(@PathVariable("id") int id) {
					System.out.println("approving events");
					Events events = null;
					events = eventsDAO.getEvent(id);
					events.setStatus("APPROVED");
					eventsDAO.updateEvent(events);
					return new ResponseEntity<Events>(events, HttpStatus.OK);
			}
}
