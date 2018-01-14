package com.codehub.webapp.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.webapp.dao.FriendsDAO;
import com.niit.webapp.model.Friends;
import com.niit.webapp.model.User;

import junit.framework.Assert;

public class FriendsTestCase {

	@Autowired
	Friends friends;
	
	@Autowired
	FriendsDAO friendsDAO;
	
	
	@Autowired
	AnnotationConfigApplicationContext context;
	
	public FriendsTestCase() {
		super();
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.webapp");
		context.refresh();
		friendsDAO = (FriendsDAO) context.getBean("friendsDAO");
		friends = (Friends) context.getBean("friends");
	}
	
	@Test
	public void addFriend() {
		
		friends.setId(01);
		friends.setInitiatorId(05);
		friends.setFriendId(10);
		friends.setStatus("PENDING");
		
		Assert.assertEquals(true, friendsDAO.addFriend(friends));
	}
	
//	@Test
//	public void updateFriend() {
//		friends = friendsDAO.getFriend(1);
//		friends.setStatus("APPROVED");
//		Assert.assertEquals(true, friendsDAO.updateFriend(friends));
//	}

//	@Test
//	public void getAllFriends() {
//		
//		int size = friendsDAO.list().size();
//		Assert.assertEquals(1, size);
//	}
	
//	@Test
//	public void deleteFriend() {
//		friends = friendsDAO.getFriend(1);
//		Assert.assertEquals(true, friendsDAO.deleteFriend(friends));
//	}
	
	
//	@Test
//	public void testNoFriends() {
//		List<User> users = friendsDAO.noFriends(29);
//		Assert.assertEquals("Test failed!", 7, users.size());
//	}
	
//	@Test
//	public void testMyFriends() {
//		List<User> users = friendsDAO.myFriends(30);
//		Assert.assertEquals("Test failed!", 3, users.size());
//	}
	

	
}
