package com.niit.webapp.model;

import java.util.List;

public class UserModel {

	private User user;
	
	private List<Events> events;
	
	private List<Job> job;
	
	private List<Blog> blog;
	
	private List<Events> joinedEvents;
	
	private List<Job> appliedJobList;
	
	private List<User> myFriends;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Events> getEvents() {
		return events;
	}

	public void setEvents(List<Events> events) {
		this.events = events;
	}

	public List<Job> getJob() {
		return job;
	}

	public void setJob(List<Job> job) {
		this.job = job;
	}

	public List<Blog> getBlog() {
		return blog;
	}

	public void setBlog(List<Blog> blog) {
		this.blog = blog;
	}

	public List<Events> getJoinedEvents() {
		return joinedEvents;
	}

	public void setJoinedEvents(List<Events> joinedEvents) {
		this.joinedEvents = joinedEvents;
	}

	public List<Job> getAppliedJobList() {
		return appliedJobList;
	}

	public void setAppliedJobList(List<Job> appliedJobList) {
		this.appliedJobList = appliedJobList;
	}

	public List<User> getMyFriends() {
		return myFriends;
	}

	public void setMyFriends(List<User> myFriends) {
		this.myFriends = myFriends;
	}

	
	
	
	
	
	
}
