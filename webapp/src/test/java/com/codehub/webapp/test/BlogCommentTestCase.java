package com.codehub.webapp.test;

import java.time.LocalDate;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.webapp.dao.BlogCommentDAO;
import com.niit.webapp.dao.BlogDAO;
import com.niit.webapp.model.Blog;
import com.niit.webapp.model.BlogComments;

import junit.framework.Assert;

public class BlogCommentTestCase {
	
	@Autowired
	Blog blog;
	
	@Autowired
	BlogDAO blogDAO;
	
	@Autowired
	BlogComments blogComments;
	
	@Autowired
	BlogCommentDAO blogCommentDAO;
	
	@Autowired
	AnnotationConfigApplicationContext context;
	
	public BlogCommentTestCase() {
		super();
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.webapp");
		context.refresh();
		blogDAO = (BlogDAO) context.getBean("blogDAO");
		blog = (Blog) context.getBean("blog");
		blogCommentDAO = (BlogCommentDAO) context.getBean("blogCommentDAO");
		blogComments = (BlogComments) context.getBean("blogComments");
	}
	
	@Test
	public void addBlogCommentTest() {
		
		blogComments.setId(10);
		blogComments.setBlogComment("This is a comment");
		
		blogComments.setUserId(101);
		blogComments.setUsername("Avadhoot");
		blogComments.setCommentDate(LocalDate.parse("2007-02-10"));
		blog = blogDAO.getBlog(2);
		blogComments.setBlog(blog);
		Assert.assertEquals(true, blogCommentDAO.addBlogComments(blogComments));
	}
	@Ignore
	@Test
	public void updateBlogCommentTestCase() {
		blogComments = blogCommentDAO.getBlogComments(6);
		Assert.assertEquals(true, blogCommentDAO.updateBlogComments(blogComments));
	}
	

	@Ignore
	@Test
	public void deleteBlogComment() {
		blogComments = blogCommentDAO.getBlogComments(7);
		Assert.assertEquals(true, blogCommentDAO.deleteBlogComments(blogComments));
	}
}
