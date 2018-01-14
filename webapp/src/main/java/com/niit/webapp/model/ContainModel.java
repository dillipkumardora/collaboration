package com.niit.webapp.model;

import java.util.List;

public class ContainModel {
	
	private List<Blog> top5Blogs;
	
	private List<Forum> top3Forums;
	
	private List<Job> top3Jobs;
	
	private List<Events> top3Events;

	public List<Blog> getTop5Blogs() {
		return top5Blogs;
	}

	public void setTop5Blogs(List<Blog> top5Blogs) {
		this.top5Blogs = top5Blogs;
	}

	public List<Forum> getTop3Forums() {
		return top3Forums;
	}

	public void setTop3Forums(List<Forum> top3Forums) {
		this.top3Forums = top3Forums;
	}

	public List<Job> getTop3Jobs() {
		return top3Jobs;
	}

	public void setTop3Jobs(List<Job> top3Jobs) {
		this.top3Jobs = top3Jobs;
	}

	public List<Events> getTop3Events() {
		return top3Events;
	}

	public void setTop3Events(List<Events> top3Events) {
		this.top3Events = top3Events;
	}
	
	

}
