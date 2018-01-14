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

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Component
public class Blog extends BaseDomain implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8902970462892867386L;
	
	@Id @GeneratedValue(strategy =GenerationType.SEQUENCE,generator="generator")
	@SequenceGenerator(name="generator", sequenceName="BLOG_SEQ", allocationSize = 1)
	@Column(name="BLOG_ID")
	private int id;
	
	@Column(name="Blog_Name")
	private String name;
	
	private String status;
	
	private String description;
	
	@Column(name="Post_Date")
	private LocalDate postDate;
	
	@Column(name="No_Of_Likes")
	private int noOfLikes;
	
	@Column(name="No_Of_Comments")
	private int noOfComments;
	
	@Column(name="No_Of_Views")
	private int noOfViews;
	
	@Column(name="User_Id")
	private int userId;
	
	@Column(name="User_Name")
	private String userName;
	
	@OneToMany(mappedBy="blog", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<BlogComments> blogComments;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public int getNoOfLikes() {
		return noOfLikes;
	}

	public void setNoOfLikes(int noOfLikes) {
		this.noOfLikes = noOfLikes;
	}

	public int getNoOfComments() {
		return noOfComments;
	}

	public void setNoOfComments(int noOfComments) {
		this.noOfComments = noOfComments;
	}

	public int getNoOfViews() {
		return noOfViews;
	}

	public void setNoOfViews(int noOfViews) {
		this.noOfViews = noOfViews;
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

	public List<BlogComments> getBlogComments() {
		return blogComments;
	}

	public void setBlogComments(List<BlogComments> blogComments) {
		this.blogComments = blogComments;
	}
	
	

}
