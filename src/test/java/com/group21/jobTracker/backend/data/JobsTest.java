 package com.group21.jobTracker.backend.data;

 import static org.junit.Assert.assertEquals;
 import static org.junit.jupiter.api.Assertions.*;

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
 		assertEquals("1.0", Double.toString(jobs.getPriority()));
 		assertEquals("01/11/2022", jobs.getRemindMeOn());
 		assertEquals("10K", jobs.getSalary());
 		assertEquals("accepted", jobs.getStatus());
 		
 		Jobs jobs1 = new Jobs("SWE", "CompA", LocalDate.parse(date1), LocalDate.parse(date2), "10K", "This is my first job", "Submit", "Accepted", 1.0);
 		assertEquals("CompA", jobs1.getCompany());
 		assertEquals("2022-07-13", jobs1.dateToString(jobs.getDateApplied()));
 		assertEquals("2022-12-31", jobs1.dateToString(jobs.getDueDate()));
 		assertEquals("This is my first job", jobs1.getJobDescription());
 		assertEquals("SWE", jobs1.getJobTitle());
 		assertEquals("Submit", jobs1.getNextAction());
 		assertEquals("1.0", Double.toString(jobs1.getPriority()));
 		assertEquals("10K", jobs1.getSalary());
 		assertEquals("accepted", jobs1.getStatus());
 	}
 	
 	@Test
 	void verifyTest() {
 		try {
 			Jobs jobs = new Jobs();
 			jobs.setRemindMeOn("1st-Nov-2022");
 		} catch (Exception e){
 			String msg = e.getMessage();
 		}
 	}
 	
 	@Test
 	void stringIdTest() {
 		Jobs jobs = new Jobs();
 		jobs.setStringId("5");
 		assertEquals("5", jobs.getStringId());
 	}
 	
 	@Test
 	void statusTest() {
 		Jobs jobs = new Jobs();
 		jobs.setStatus("In Progress");
 		assertEquals("in progress", jobs.getStatus());
 		jobs.setStatus("Rejected");
 		assertEquals("rejected", jobs.getStatus());
 		jobs.setStatus("accepted");
 		assertEquals("accepted", jobs.getStatus());
 		jobs.setStatus("None");
 		assertEquals("None", jobs.getStatus());
 	}
 	
 	@Test
 	void isNewJobTest() {
 		Jobs jobs = new Jobs();
 		assertTrue(jobs.isNewJob());
 	}
 	
 	@Test
 	void toStringTest() {
 		Jobs jobs = new Jobs();
 		jobs.setJobTitle("SWE");
 		jobs.setCompany("AAA");
 		jobs.setPriority(1.0);
 		assertEquals("SWE AAA " + Double.toString(1.0), jobs.toString());
 	}

 }
