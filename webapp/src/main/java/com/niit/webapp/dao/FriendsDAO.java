package com.niit.webapp.dao;

import java.util.List;

import com.niit.webapp.model.Friends;
import com.niit.webapp.model.User;

public interface FriendsDAO {
	
	List<Friends> list();
	List<Friends> list(int id);
	List<Friends> list(String status);
	Friends getFriend(int id);
	boolean addFriend(Friends friends);
	boolean updateFriend(Friends friends);
	boolean deleteFriend(Friends friends);
	List<User> noFriends(int id);
	List<User> myFriends(int id);

}
