package com.niit.webapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Component
@Table(name="FORUM")
public class Forum implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2264478909075896498L;
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="generator")
	@SequenceGenerator(name="generator", sequenceName="forum_category_seq", allocationSize = 1)
	@Column(name="FORUM_ID")
	private int id;
	
	@Column(name="FORUM_Name")
	private String name;
	
	private String description;
	
	@Column(name="Post_Date")
	private LocalDate postDate;
	
	private String status;
	
	@OneToMany(mappedBy="forum", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonManagedReference
	List<ForumPosts> forumPosts;
	
	@Column(name="Number_Of_Posts")
	private int noOfPosts;
	
	@Column(name="User_Id")
	private int userId;
	
	@Column(name="User_Name")
	private String userName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getPostDate() {
		return postDate;
	}

	public void setPostDate(LocalDate postDate) {
		this.postDate = postDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ForumPosts> getForumPosts() {
		return forumPosts;
	}

	public void setForumPosts(List<ForumPosts> forumPosts) {
		this.forumPosts = forumPosts;
	}

	public int getNoOfPosts() {
		return noOfPosts;
	}

	public void setNoOfPosts(int noOfPosts) {
		this.noOfPosts = noOfPosts;
	}
	
	
	
}
