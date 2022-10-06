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
		User user = new User("TestFullName", "TestEmail", "male", "12", "1.2", "Keywords");
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
	
	@Test
	void getByPriorityTest() {
		User user = new User("TestFullName", "TestEmail", "male", "12", "1.2", "Keywords");
		Jobs job = new Jobs();
		job.setJobTitle("Job7");
		job.setPriority(7);
		user.addJob(job);
		job = new Jobs();
		job.setJobTitle("Job1");
		job.setPriority(1);
		user.addJob(job);
		job = new Jobs();
		job.setJobTitle("Job4");
		job.setPriority(4);
		user.addJob(job);
		job = new Jobs();
		job.setJobTitle("Job5");
		job.setPriority(5);
		user.addJob(job);
		job = new Jobs();
		job.setJobTitle("Job2");
		job.setPriority(2);
		user.addJob(job);
		job = new Jobs();
		job.setJobTitle("Job8");
		job.setPriority(8);
		user.addJob(job);
		job = new Jobs();
		job.setJobTitle("Job6");
		job.setPriority(6);
		user.addJob(job);
		job = new Jobs();
		job.setJobTitle("Job3");
		job.setPriority(3);
		user.addJob(job);
		ArrayList<Jobs> list = user.getJobsByPriority();
		for(int i = 1; i < 6; i++) {
			assertEquals("Job" + i, list.get(i - 1).getJobTitle());
		}
	}

	@Test
	void testToString(){
		User user = new User("A B", "TestEmail", "Male", null, null, "TestKeywords");
		String expectedStr = "A B,TestEmail,Male,NULL,NULL,TestKeywords,";
		assertEquals(expectedStr, user.toString());
	}

	@Test
	void testToSaveString(){
		User user = new User("A B", "TestEmail", "Male", null, null, "TestKeywords");
		Jobs job1 = new Jobs("Job1", null, null, null, null, null, null, null, 0);
		Jobs job2 = new Jobs("Job2", null, null, null, null, null, null, null, 0);
		user.addJob(job1);
		user.addJob(job2);
		String expectedStr = "A B,TestEmail,Male,NULL,NULL,TestKeywords,\n0,Job1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0.0,\n1,Job2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0.0,";
		assertEquals(expectedStr, user.toSaveString());
	}

	@Test
	void testGetProcessedFullName(){
		User user = new User("Test Full", null);
		assertEquals("TestFull", user.getProcessedFullName());
	}

	@Test
	void testDeleteExistingJob(){
		User user = new User("TestDelete", null, null, null, null, null);
		Jobs job1 = new Jobs("Job1", null, null, null, null, null, null, null, 0);
		Jobs job2 = new Jobs("Job2", null, null, null, null, null, null, null, 0);
		Jobs job3 = new Jobs("Job3", null, null, null, null, null, null, null, 0);
		user.addJob(job1);
		user.addJob(job2);
		user.addJob(job3);
		ArrayList<Jobs> jobs = user.getJobs();
		for(int i=0; i<jobs.size(); i++){
			assertEquals("Job" + (i+1), jobs.get(i).getJobTitle());
			assertEquals(i, jobs.get(i).getId());
		}
		user.deleteExistingJob(job2);
		jobs = user.getJobs();
		assertEquals("Job1", jobs.get(0).getJobTitle());
		assertEquals("Job3", jobs.get(1).getJobTitle());
		for(int i=0; i<jobs.size(); i++){
			assertEquals(i, jobs.get(i).getId());
		}
	}

}
