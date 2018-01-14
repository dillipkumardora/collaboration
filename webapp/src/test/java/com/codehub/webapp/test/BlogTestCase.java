package com.codehub.webapp.test;

import java.time.LocalDate;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.webapp.dao.BlogCommentDAO;
import com.niit.webapp.dao.BlogDAO;
import com.niit.webapp.model.Blog;
import com.niit.webapp.model.BlogComments;

import junit.framework.Assert;

public class BlogTestCase {

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
	
	public BlogTestCase() {
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
	public void addBlogTest() {
		
		blog.setId(101);
		blog.setName("This is test");
		blog.setStatus("PENDING");
		//blog.setDescription("Test");
		blog.setPostDate(LocalDate.parse("2018-02-10"));
		blog.setNoOfLikes(100);
		blog.setNoOfComments(50);
		blog.setUserId(101);
		blog.setUserName("dillip");
	
		Assert.assertEquals(true, blogDAO.addBlog(blog));
	}
	@Ignore
	@Test
	public void updateBlog() {
		blog = blogDAO.getBlog(1);
		blog.setStatus("APPROVED");
		Assert.assertEquals(true, blogDAO.updateBlog(blog));
	}
	@Ignore
	@Test
	public void getAllBlogsTestCase() {
		
		int size = blogDAO.list().size();
		Assert.assertEquals(1, size);
	}
	@Ignore
	@Test
	public void deleteBlog() {
		blog = blogDAO.getBlog(5);
		Assert.assertEquals(true, blogDAO.deleteBlog(blog));
	}
	@Ignore
	@Test
	public void fetchBlogList() {
		List<Blog> bloglist = blogDAO.mainList();
		for(Blog b1 : bloglist) {
			System.out.println(b1.getNoOfViews());
		}
		Assert.assertEquals(5, blogDAO.mainList().size());
	}
}
