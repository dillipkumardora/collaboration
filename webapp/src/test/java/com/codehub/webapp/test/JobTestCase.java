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

public class JobTestCase {
	
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
	
	public JobTestCase() {
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
	public void addJobTest() {
		
		job.setId(1);
		job.setCompanyName("niit");
		job.setSubTitle("Post for programmer");
		job.setAbout("this is the post of tech mentor. Will you join me");
				
		job.setJobProfile("A job description may include relationships with other people in the organization: Supervisory level, managerial requirements, and relationships with other colleagues. "
				         + "organization: Supervisory level, managerial requirements, and relationships with other colleagues.");
		
		job.setQualification("Xth : 90% + XIIth with science: 75% Graduated in Computer Engineering with 60%+ 2 years of experience at least");
		job.setContactInfo("Send your resume to following address:Puri Taluk of Puri District Telephone No	06752-222051 Address"
				+"Puri King Palace, ବଡଦାଣ�?ଡ, Puri, Odisha 752001, India"
                             + "Contact Number : 9898989898 email id : xynz@gmail.com");
		job.setPostDate(LocalDate.parse("2017-02-10"));
		job.setUserId(101);
		job.setUsername("dillip");
		
		Assert.assertEquals(true, jobDAO.addJob(job));
	}

	@Ignore
	@Test
	public void getAllJobsTestCase() {
		int size = jobDAO.list().size();
		
		Assert.assertEquals(1, size);
		
	}
	@Ignore
	@Test
	public void deleteJob() {
		job = jobDAO.getJob(1);
		
		Assert.assertEquals(true, jobDAO.deleteJob(job));
	}

}
