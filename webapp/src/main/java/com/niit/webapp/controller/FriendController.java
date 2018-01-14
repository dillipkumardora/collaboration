package com.niit.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.webapp.dao.FriendsDAO;
import com.niit.webapp.dao.UserDAO;
import com.niit.webapp.model.Blog;
import com.niit.webapp.model.Forum;
import com.niit.webapp.model.ForumRequest;
import com.niit.webapp.model.FriendModel;
import com.niit.webapp.model.Friends;
import com.niit.webapp.model.User;

@RestController
public class FriendController {

	@Autowired
	UserDAO userDAO;
	
	@Autowired
	FriendsDAO friendsDAO; 
	
		//Method to send friend request
		@RequestMapping(value = {"/user/friendRequest/{id}"}, method = RequestMethod.POST)
		public ResponseEntity<Friends> sendFriendRequest(@PathVariable("id") int id, @RequestBody Integer initId) {
				System.out.println("Sending friend request now!");
				Friends friends = new Friends();
				User user = userDAO.getUser(id); //Fetching friend by friend id
				friends.setFriendId(id);
				friends.setInitiatorId(initId);
				friends.setStatus("PENDING");
				friendsDAO.addFriend(friends);
				return new ResponseEntity<Friends>(friends, HttpStatus.OK);
			}
		
		//Method to fetch friend requests
		@RequestMapping(value = {"/user/friendRequest/list/{id}"}, method = RequestMethod.GET)
		public ResponseEntity<List<User>> fetchRequest(@PathVariable("id") int userId) {
				System.out.println("Fetchng list of friend request received");
				List<Friends> friends = friendsDAO.list(userId);
				List<User> users = new ArrayList<>();
				for(Friends fr : friends) {
					if(fr.getStatus().equals("PENDING")) {
						User user = userDAO.getUser(fr.getInitiatorId());
						users.add(user);
					}
				}
				return new ResponseEntity<List<User>>(users, HttpStatus.OK);
			}
		
		//Method to fetch friend requests
				@RequestMapping(value = {"/user/friendRequest/approve/{id}"}, method = RequestMethod.POST)
				public ResponseEntity<Friends> approveRequest(@PathVariable("id") int id, @RequestBody Integer userId) {
						System.out.println("Fetchng list of friend request received");
						List<Friends> friends = friendsDAO.list(userId);
						List<User> users = new ArrayList<>();
						for(Friends fr : friends) {
							if(fr.getInitiatorId() == id) {
								fr.setStatus("APPROVED");
								friendsDAO.updateFriend(fr);
							}
						}
						return new ResponseEntity<Friends>(HttpStatus.OK);
					}
		
				//Method to check user's friends
//				@RequestMapping(value = {"/user/friends/check/{id}"}, method = RequestMethod.GET)
//				public ResponseEntity<List<Friends>> fetchFriends(@PathVariable("id") int userId) {
//						System.out.println("Fetchng friends");
//						List<Friends> friends = friendsDAO.list(userId);
//						
//						return new ResponseEntity<List<Friends>>(friends, HttpStatus.OK);
//					}
				
				//Method to fetch friendsModel
				@RequestMapping(value = {"/user/friends/model/{id}"}, method = RequestMethod.GET)
				public ResponseEntity<List<User>> users(@PathVariable("id") int userId) {
						System.out.println("Fetchng friends");
						List<User> users = friendsDAO.noFriends(userId);  
						return new ResponseEntity<List<User>>(users, HttpStatus.OK);
					}
				
				//function to fetch user's friends
				@RequestMapping(value = {"/my/friends/{id}"}, method = RequestMethod.GET)
				public ResponseEntity<List<User>> fetchMyFriends(@PathVariable("id") int userId) {
						System.out.println("Fetchng friends");
						List<User> users = friendsDAO.myFriends(userId);
						List<User> myFriends = new ArrayList<>();
						for(User user1 : users) {
							if(user1.getId() != userId) {
								myFriends.add(user1);
							}
						}
						System.out.println("Successfully fetch friends");
						return new ResponseEntity<List<User>>(myFriends, HttpStatus.OK);
					}
				
				
	
}
