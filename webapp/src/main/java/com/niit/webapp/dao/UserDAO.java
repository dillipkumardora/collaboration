package com.niit.webapp.dao;

import java.util.List;

import com.niit.webapp.model.User;

public interface UserDAO {
	
	List<User> list();
	List<User> list(String status);
	List<User> fetchOnlineFriends(int id);
	User getUser(int id);
	User getByUserName(String username);
	User validateUser(User user);
	boolean addUser(User user);
	boolean updateUser(User user);
	boolean deleteUser(User user);
	// for changing the profile picture
	boolean updateUserProfile(String fileName, Integer id);
	
}
