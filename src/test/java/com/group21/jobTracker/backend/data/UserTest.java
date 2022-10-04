/**
 * 
 */
package com.group21.jobTracker.backend.data;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * Tests the User class
 */
class UserTest {

	@Test
	void test() {
		User user = new User("TestFullName", "TestEmail", "male", "12", "1.5", "Keywords");
		assertEquals(user.getFullName(), "TestFullName");
		assertEquals(user.getEmailAddress(), "TestEmail");
		assertEquals(user.getGender(), "male");
		assertEquals(user.getAge(), "12");
		assertEquals(user.getExperience(), "1.5");
		assertEquals(user.getKeywords(), "Keywords");
	}
	
	@Test
	void getByDateTest() {
		User user = new User("TestFullName", "TestEmail", "male", "12", "1.5", "Keywords");
		Jobs job = new Jobs();
		job.setJobTitle("Job7");
		job.setDueDate(LocalDate.parse("2022-10-07"));
		job.setDateApplied(LocalDate.parse("2022-10-07"));
		user.addJob(job);
		job = new Jobs();
		job.setJobTitle("Job1");
		job.setDueDate(LocalDate.parse("2022-10-01"));
		job.setDateApplied(LocalDate.parse("2022-10-01"));
		user.addJob(job);
		job = new Jobs();
		job.setJobTitle("Job4");
		job.setDueDate(LocalDate.parse("2022-10-04"));
		job.setDateApplied(LocalDate.parse("2022-10-04"));
		user.addJob(job);
		job = new Jobs();
		job.setJobTitle("Job5");
		job.setDueDate(LocalDate.parse("2022-10-05"));
		job.setDateApplied(LocalDate.parse("2022-10-05"));
		user.addJob(job);
		job = new Jobs();
		job.setJobTitle("Job2");
		job.setDueDate(LocalDate.parse("2022-10-02"));
		job.setDateApplied(LocalDate.parse("2022-10-02"));
		user.addJob(job);
		job = new Jobs();
		job.setJobTitle("Job8");
		job.setDueDate(LocalDate.parse("2022-10-08"));
		job.setDateApplied(LocalDate.parse("2022-10-08"));
		user.addJob(job);
		job = new Jobs();
		job.setJobTitle("Job6");
		job.setDueDate(LocalDate.parse("2022-10-06"));
		job.setDateApplied(LocalDate.parse("2022-10-06"));
		user.addJob(job);
		job = new Jobs();
		job.setJobTitle("Job3");
		job.setDueDate(LocalDate.parse("2022-10-03"));
		job.setDateApplied(LocalDate.parse("2022-10-03"));
		user.addJob(job);
		ArrayList<Jobs> list = user.getJobsByDate("DueDate");
		for(int i = 1; i < 6; i++) {
			assertEquals("Job" + i, list.get(i - 1).getJobTitle());
		}
		list = user.getJobsByDate("DateApplied");
		for(int i = 0; i < 5; i++) {
			assertEquals("Job" + (8 - i), list.get(i).getJobTitle());
		}
	}

}
