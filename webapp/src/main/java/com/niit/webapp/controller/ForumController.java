package com.niit.webapp.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.webapp.dao.ForumDAO;
import com.niit.webapp.dao.ForumPostDAO;
import com.niit.webapp.dao.ForumRequestDAO;
import com.niit.webapp.dao.UserDAO;
import com.niit.webapp.model.Forum;
import com.niit.webapp.model.ForumModel;
import com.niit.webapp.model.ForumPosts;
import com.niit.webapp.model.ForumRequest;
import com.niit.webapp.model.User;

@RestController
public class ForumController {

	@Autowired
	ForumDAO forumDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	Forum forum;
	
	@Autowired
	ForumPostDAO forumPostDAO;
	
	@Autowired
	ForumRequestDAO forumRequestDAO;
	
	//Method for creating new forum category
	@RequestMapping(value = {"/forum/new"}, method = RequestMethod.POST)
	public ResponseEntity<Forum> addForumCategory(@RequestBody Forum forum) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now(); 
		forum.setPostDate(LocalDate.parse(dtf.format(now)));
		forum.setStatus("APPROVED");
		forum.setNoOfPosts(0);
		User user = null;	//creating instance of user
		int id = forum.getUserId();	//retrieving user id from forum
		user = userDAO.getUser(id);	//fetching user detail by its id
		forumDAO.addForum(forum);
		int forumId = forum.getId();
		ForumRequest fr = new ForumRequest();
		fr.setUserId(id);
		fr.setUsername(user.getUsername());
		fr.setStatus("APPROVED");
		fr.setForum(forum);
		forumRequestDAO.addForumRequest(fr);
		
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}
	
	//Method for fetching list of all forum categories
	@RequestMapping(value = {"/forum/list"}, method = RequestMethod.GET)
	public ResponseEntity<List<Forum>> fetchForums() {
		System.out.println("Method called");
		List<Forum> forums = forumDAO.list();
		return new ResponseEntity<List<Forum>>(forums, HttpStatus.OK);
	}
	
	
	//Method for viewing single forum using forum id as a parameter
	
	@RequestMapping(value = {"/forum/{id}"}, method = RequestMethod.GET)
	public ResponseEntity<ForumModel> viewForum(@PathVariable("id") int id) {
		System.out.println("Calling method");
		ForumModel forumModel = new ForumModel();
		Forum forum = forumDAO.getForum(id);
		User user = userDAO.getUser(forum.getUserId());
		forumModel.setForum(forum);
		forumModel.setUser(user);
		return new ResponseEntity<ForumModel>(forumModel, HttpStatus.OK);
			
		}
	
	@RequestMapping(value = {"/forum/post/new/{id}"}, method = RequestMethod.POST)
	public ResponseEntity<ForumPosts> addForumPost(@PathVariable("id") int id, @RequestBody ForumPosts forumPosts) {
		System.out.println("Adding forum post now");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now(); 
		forumPosts.setPostDate(LocalDate.parse(dtf.format(now)));
		forum = forumDAO.getForum(id);
		forum.setNoOfPosts(forum.getNoOfPosts() + 1);
		forumDAO.updateForum(forum);
		forumPosts.setForum(forum);
		forumPosts.setUserProfileId(userDAO.getUser(forumPosts.getUserId()).getProfile());
		forumPostDAO.addForumPosts(forumPosts);
		
		return new ResponseEntity<ForumPosts>(forumPosts, HttpStatus.OK);	
	}
	
	 //Function to fetch forum post list
	 @RequestMapping(value = {"/forum/posts/list/{id}"}, method = RequestMethod.GET)
	 public ResponseEntity<List<ForumPosts>> fetchForumPosts(@PathVariable("id") int id) {
			System.out.println("fetching list of forum posts now");
			List<ForumPosts> forumPosts = forumPostDAO.list(id);
			return new ResponseEntity<List<ForumPosts>>(forumPosts, HttpStatus.OK);
	}
	
}
