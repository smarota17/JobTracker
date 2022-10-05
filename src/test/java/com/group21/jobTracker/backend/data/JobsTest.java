package com.group21.jobTracker.backend.data;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

import java.util.Set;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class JobsTest {
	
	@Test
	void evaluateTest() {
		Jobs jobs = new Jobs();
		assertTrue(true);
	}
	
	@Test
	void jobsTest() {
		Jobs jobs = new Jobs();
		jobs.setJobTitle("SWE");
		jobs.setCompany("CompA");
		jobs.setDateApplied(LocalDate.of(2022, 10, 4));
		jobs.setDueDate(LocalDate.of(2022, 11, 8));
		jobs.setSalary("10");
		jobs.setJobDescription("This is my first job");
		jobs.setNextAction("Submit");
		jobs.setStatus("Accepted");
		jobs.setPriority("1");
				
		
		assertEquals("SWE", jobs.getJobTitle());
		assertEquals("CompA", jobs.getCompany());
		assertEquals(LocalDate.of(2022, 10, 4), jobs.getDateApplied());
		assertEquals(LocalDate.of(2022, 11, 8), jobs.getDueDate());
		assertEquals("10", jobs.getSalary());
		assertEquals("This is my first job", jobs.getJobDescription());
		assertEquals("Submit", jobs.getNextAction());
		assertEquals("accepted", jobs.getStatus());
		assertEquals("1", jobs.getPriority());		
		
	}

}
