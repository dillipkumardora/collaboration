package com.niit.webapp.dao;

import java.util.List;

import com.niit.webapp.model.BlogComments;

public interface BlogCommentDAO {

	List<BlogComments> list(int id);
	BlogComments getBlogComments(int id);
	boolean addBlogComments(BlogComments blogComments);
	boolean updateBlogComments(BlogComments blogComments);
	boolean deleteBlogComments(BlogComments blogComments);
}
