package com.codehub.webapp.test;

import java.time.LocalDate;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.webapp.dao.JobAppliedDAO;
import com.niit.webapp.dao.JobDAO;
import com.niit.webapp.model.Job;
import com.niit.webapp.model.JobApplied;

import junit.framework.Assert;

public class JobAppliedTestCase {

	@Autowired
	Job job;
	
	@Autowired
	JobDAO jobDAO;
	
	@Autowired
	JobApplied jobApplied;
	
	@Autowired
	JobAppliedDAO jobAppliedDAO;
	
	@Autowired
	AnnotationConfigApplicationContext context;
	
	public JobAppliedTestCase() {
		super();
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.webapp");
		context.refresh();
		jobDAO = (JobDAO) context.getBean("jobDAO");
		job = (Job) context.getBean("job");
		jobAppliedDAO = (JobAppliedDAO) context.getBean("jobAppliedDAO");
		jobApplied = (JobApplied) context.getBean("jobApplied");
	}
	
	@Test
	public void addJobAppliedTest() {
		jobApplied.setId(1);
		jobApplied.setUserId(101);
		jobApplied.setUsername("dillip");
		jobApplied.setStatus("Pending");
		jobApplied.setAppliedDate(LocalDate.parse("2007-02-10"));
		job = jobDAO.getJob(1);
		jobApplied.setJob(job);		
		Assert.assertEquals(true, jobAppliedDAO.addJobApplied(jobApplied));
		
	}
	@Ignore
	@Test
	public void updateJobApplied() {
	
		jobApplied = jobAppliedDAO.getJobApplied(1);
		jobApplied.setStatus("Approved");
		Assert.assertEquals(true, jobAppliedDAO.updateJobApplied(jobApplied));
	}
	@Ignore
	@Test
	public void getAllJobsAppliedTestCase() {
		
		int size = jobAppliedDAO.list().size();
		Assert.assertEquals(1, size);
		
	}
	@Ignore
	@Test
	public void deleteJobApplied() {
		
		jobApplied = jobAppliedDAO.getJobApplied(1);
		Assert.assertEquals(true, jobAppliedDAO.deleteJobApplied(jobApplied));
		
	}
}
