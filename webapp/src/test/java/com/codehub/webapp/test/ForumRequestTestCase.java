package com.codehub.webapp.test;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.webapp.dao.ForumDAO;
import com.niit.webapp.dao.ForumPostDAO;
import com.niit.webapp.dao.ForumRequestDAO;
import com.niit.webapp.model.Forum;
import com.niit.webapp.model.ForumPosts;
import com.niit.webapp.model.ForumRequest;

import junit.framework.Assert;

public class ForumRequestTestCase {

	@Autowired
	Forum forum;
	
	@Autowired
	ForumDAO forumDAO;
	
	@Autowired
	Forum forumCategory;
	
	@Autowired
	ForumDAO forumCategoryDAO;
	
	@Autowired
	ForumPosts forumPosts;
	
	@Autowired
	ForumPostDAO forumPostDAO;
	
	@Autowired
	ForumRequest forumRequest;
	
	@Autowired
	ForumRequestDAO forumRequestDAO;
	
	@Autowired
	AnnotationConfigApplicationContext context;
	
	public ForumRequestTestCase() {
		super();
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.webapp");
		context.refresh();
		forumDAO = (ForumDAO) context.getBean("forumDAO");
		forum = (Forum) context.getBean("forum");
		forumPostDAO = (ForumPostDAO) context.getBean("forumPostDAO");
		forumPosts = (ForumPosts) context.getBean("forumPosts");
		forumRequestDAO = (ForumRequestDAO) context.getBean("forumRequestDAO");
		forumRequest = (ForumRequest) context.getBean("forumRequest");
	}
	
	@Test
	public void addForumRequest() {
		forumRequest.setId(2);
		forumRequest.setUserId(102);
		forumRequest.setUsername("dillip");
		forumRequest.setStatus("Pending");
		forum = forumDAO.getForum(6);
		forumRequest.setForum(forum);
		
		
		Assert.assertEquals(true, forumRequestDAO.addForumRequest(forumRequest));
	}
	@Ignore
	@Test
	public void updateForumRequest() {
		forumRequest = forumRequestDAO.getForumRequest(1);
		forumRequest.setStatus("Approved");
		Assert.assertEquals(true, forumRequestDAO.updateForumRequest(forumRequest));
	}
	@Ignore
	@Test
	public void getAllForumRequestTestCase() {
		
		int size = forumRequestDAO.list().size();
		Assert.assertEquals(1, size);
	}
	@Ignore
	@Test
	public void deleteForumRequest() {
		forumRequest = forumRequestDAO.getForumRequest(1);
		Assert.assertEquals(true, forumRequestDAO.deleteForumRequest(forumRequest));
	}
}
