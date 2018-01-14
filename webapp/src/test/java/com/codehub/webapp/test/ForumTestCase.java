package com.codehub.webapp.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.webapp.dao.ForumDAO;
import com.niit.webapp.dao.ForumPostDAO;
import com.niit.webapp.dao.ForumRequestDAO;
import com.niit.webapp.model.Blog;
import com.niit.webapp.model.Forum;
import com.niit.webapp.model.ForumPosts;
import com.niit.webapp.model.ForumRequest;

import junit.framework.Assert;

public class ForumTestCase {
	
	@Autowired
	Forum forum;
	
	@Autowired
	ForumDAO forumDAO;

	@Autowired
	AnnotationConfigApplicationContext context;
	
	public ForumTestCase() {
		super();
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.webapp");
		context.refresh();
		forumDAO = (ForumDAO) context.getBean("forumDAO");
		forum = (Forum) context.getBean("forum");

	}
	

	@Test
	public void fetchForumList() {
		List<Forum> forumlist = forumDAO.mainList();
		for(Forum f1 : forumlist ) {
			System.out.println(f1.getPostDate());
		}
		Assert.assertEquals(3, forumDAO.mainList().size());
	}

}
