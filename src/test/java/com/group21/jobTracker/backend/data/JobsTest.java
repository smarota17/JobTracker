 package com.group21.jobTracker.backend.data;

 import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

 import org.junit.jupiter.api.Test;

 class JobsTest {
	
 	@Test
 	void jobsTest() {
 		String date1 = "2022-07-13";
		String date2 = "2022-12-31";
 		Jobs jobs = new Jobs();
 		jobs.setCompany("CompA");
 		jobs.setDateApplied(LocalDate.parse(date1));
 		jobs.setDueDate(LocalDate.parse(date2));
 		jobs.setId(0);
 		jobs.setJobDescription("This is my first job");
 		jobs.setJobTitle("SWE");
 		jobs.setNextAction("Submit");
 		jobs.setPriority(1);
 		jobs.setRemindMeOn("01/11/2022");
 		jobs.setSalary("10K");
 		jobs.setStatus("Accepted");
		
 		assertEquals("CompA", jobs.getCompany());
 		assertEquals("2022-07-13", Jobs.dateToString(jobs.getDateApplied()));
 		assertEquals("2022-12-31", Jobs.dateToString(jobs.getDueDate()));
 		assertEquals(0, jobs.getId());
 		assertEquals("This is my first job", jobs.getJobDescription());
 		assertEquals("SWE", jobs.getJobTitle());
 		assertEquals("Submit", jobs.getNextAction());
 		assertEquals("1", jobs.getPriority());
 		assertEquals("01/11/2022", jobs.getRemindMeOn());
 		assertEquals("10K", jobs.getSalary());
 		assertEquals("accepted", jobs.getStatus());
 	}

 }
