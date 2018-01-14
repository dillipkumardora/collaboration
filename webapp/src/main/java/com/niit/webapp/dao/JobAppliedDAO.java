package com.niit.webapp.dao;

import java.util.List;

import com.niit.webapp.model.JobApplied;

public interface JobAppliedDAO {
	
	List<JobApplied> list();
	List<JobApplied> list(int id);
	JobApplied getJobApplied(int id);
	boolean addJobApplied(JobApplied jobApplied);
	boolean updateJobApplied(JobApplied jobApplied);
	boolean deleteJobApplied(JobApplied jobApplied);

}
