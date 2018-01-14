package com.niit.webapp.dao;

import java.util.List;

import com.niit.webapp.model.Blog;

public interface BlogDAO {

	List<Blog> list();
	List<Blog> getBlogsByStatus(String status);
	List<Blog> getUsersBlogs(int id);
	List<Blog> mainList();
	Blog getBlog(int id);
	boolean addBlog(Blog blog);
	boolean updateBlog(Blog blog);
	boolean deleteBlog(Blog blog);
}
