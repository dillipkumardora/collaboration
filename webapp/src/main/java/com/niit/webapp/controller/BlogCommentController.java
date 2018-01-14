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

import com.niit.webapp.dao.BlogCommentDAO;
import com.niit.webapp.dao.BlogDAO;
import com.niit.webapp.dao.UserDAO;
import com.niit.webapp.model.Blog;
import com.niit.webapp.model.BlogComments;
import com.niit.webapp.model.User;

@RestController
public class BlogCommentController {

	@Autowired
	Blog blog;
	
	@Autowired
	BlogDAO blogDAO;
	
	@Autowired
	BlogCommentDAO blogCommentsDAO;
	
	@Autowired
	User user;
	
	@Autowired
	UserDAO userDAO;
	
	//Method for creating a new blog comment
	
		@RequestMapping(value = {"/blog/comment/new/{id}"}, method = RequestMethod.POST)
		public ResponseEntity<BlogComments> addBlogComment(@PathVariable("id") int id, @RequestBody BlogComments blogComments) {
			System.out.println("Adding blog comment now");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDateTime now = LocalDateTime.now(); 
			blogComments.setCommentDate(LocalDate.parse(dtf.format(now)));
			blog = blogDAO.getBlog(id);
			blog.setNoOfComments(blog.getNoOfComments() + 1);
			blogDAO.updateBlog(blog);
			blogComments.setBlog(blog);
			blogComments.setUserProfileId(userDAO.getUser(blogComments.getUserId()).getProfile());
			blogCommentsDAO.addBlogComments(blogComments);
			
			return new ResponseEntity<BlogComments>(blogComments, HttpStatus.OK);	
		}
		
		//Method for fetching blog comment list
		@RequestMapping(value = {"/blog/comment/list/{id}"}, method = RequestMethod.GET)
		public ResponseEntity<List<BlogComments>> fetchBlogComments(@PathVariable("id") int id) {
			System.out.println("fetching list of blog comments");
			List<BlogComments> blogComments = blogCommentsDAO.list(id);
			return new ResponseEntity<List<BlogComments>>(blogComments, HttpStatus.OK);
		}
	
	
}
