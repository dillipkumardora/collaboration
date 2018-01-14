package com.niit.webapp.dao;

import java.util.List;

import com.niit.webapp.model.ForumPosts;

public interface ForumPostDAO {

	List<ForumPosts> list();
	List<ForumPosts> list(int id);
	ForumPosts getForumPosts(int id);
	boolean addForumPosts(ForumPosts forumPosts);
	boolean updateForumPosts(ForumPosts forumPosts);
	boolean deleteForumPosts(ForumPosts forumPosts);
}
