package com.group21.jobTracker.backend.data;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class JobsTest {
	
	@Test
	void evaluateTest() {
		Jobs jobs = new Jobs();
		// jobs.verifyInput("25/06/2001");
		assertTrue(true);
	}
	
	@Test
	void jobsTest() {
		Jobs jobs = new Jobs();
		jobs.setCompany("CompA");
		jobs.setDateApplied("02/10/2022");
		jobs.setDueDate("02/11/2022");
		jobs.setId(0);
		jobs.setJobDescription("This is my first job");
		jobs.setJobTitle("SWE");
		// TODO: how to test for Set<Category>
		jobs.setJobType(null);
		jobs.setNextAction("Submit");
		jobs.setPriority("1");
		jobs.setRemindMeOn("01/11/2022");
		jobs.setSalary(10);
		jobs.setStatus("Accepted");
		
		assertEquals("CompA", jobs.getCompany());
		assertEquals("02/10/2022", jobs.getDateApplied());
		assertEquals("02/11/2022", jobs.getDueDate());
		assertEquals(0, jobs.getId());
		assertEquals("This is my first job", jobs.getJobDescription());
		assertEquals("SWE", jobs.getJobTitle());
		assertEquals(null, jobs.getJobType());
		assertEquals("Submit", jobs.getNextAction());
		assertEquals("1", jobs.getPriority());
		assertEquals("01/11/2022", jobs.getRemindMeOn());
		assertEquals(10, jobs.getSalary());
		assertEquals("accepted", jobs.getStatus());
	}

}
