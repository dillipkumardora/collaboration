package com.codehub.webapp.test;

import java.time.LocalDate;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.webapp.dao.UserDAO;
import com.niit.webapp.model.User;

import junit.framework.Assert;

public class UserTestCase {

	@Autowired
	User user;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	AnnotationConfigApplicationContext context;
	
	
	public UserTestCase() {
		super();
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.webapp");
		context.refresh();
		userDAO = (UserDAO) context.getBean("userDAO");
		user = (User) context.getBean("user");
	}
	
	@Test
	public void addUser() {
		user.setId(1);
		user.setUsername("mallik");
		user.setFirstname("mallik");
		user.setLastname("sahoo");
		user.setPassword("mallik");
		user.setEmailId("mallik@gmail.com");
		user.setBirthDate(LocalDate.parse("1994-11-23")); //yyyy-mm-dd
		user.setGender('M');
		user.setRole("employer");
		user.setStatus("APPROVED");
		user.setEnabled(true);
		user.setOnline(true);
		user.setProfile("noDP.png");
		
		Assert.assertEquals(true, userDAO.addUser(user));
		
	}
	
	@Ignore
	@Test
	public void updateUser() {
	user = userDAO.getByUserName("dillip");
	user.setRole("Admin");
		Assert.assertEquals(true, userDAO.updateUser(user));
	}
	
	@Ignore
	@Test
	public void deleteUser() {
		user = userDAO.getUser(27);
		Assert.assertEquals(true, userDAO.deleteUser(user));
	}
	@Ignore
	@Test
	public void getAllUserTestCase() {
	
		int size = userDAO.list("APPROVED").size();
		Assert.assertEquals(9, size);
	}
	
	@Ignore
	@Test
	public void testMyOnlineFriends() {
		List<User> users = userDAO.fetchOnlineFriends(29);
		Assert.assertEquals("Test failed!", 2, users.size());
	}
	
}
