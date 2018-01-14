package com.niit.webapp.dao;

import java.util.List;

import com.niit.webapp.model.Forum;

public interface ForumDAO {

	List<Forum> list();
	List<Forum> mainList();
	Forum getForum(int id);
	boolean addForum(Forum forum);
	boolean updateForum(Forum forum);
	boolean deleteForum(Forum forum);
}
