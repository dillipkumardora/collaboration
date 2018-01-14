package com.niit.webapp.dao;

import java.util.List;

import com.niit.webapp.model.ForumRequest;

public interface ForumRequestDAO {
	
	List<ForumRequest> list();
	List<ForumRequest> list(String status);
	List<ForumRequest> list(int id);
	ForumRequest getForumRequest(int id);
	boolean addForumRequest(ForumRequest forumRequest);
	boolean updateForumRequest(ForumRequest forumRequest);
	boolean deleteForumRequest(ForumRequest forumRequest);

}
