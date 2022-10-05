package com.group21.jobTracker.csv;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.group21.jobTracker.backend.data.Jobs;
import com.group21.jobTracker.backend.data.User;

class CsvTest {

	/**
	 * Tests reading and writing to a file
	 */
	@Test
	void CSVTest() {
		String date1 = "2022-07-13";
		String date2 = "2022-12-31";
		try {
			User user = new User("TestName", "TestEmail", "male", null, "1.5", "Software engineer" );
			Jobs job = new Jobs("Title", "Company", LocalDate.parse(date1), LocalDate.parse(date2), "40k", "description", "action", "status", 1);
			Jobs job2 = new Jobs("Title2", "Company2", LocalDate.parse(date1), LocalDate.parse(date2), "50k", "description2", "action2", "status2", 2);
			user.addJob(job);
			user.addJob(job2);
			Csv.saveUser(user);
			User user2 = Csv.loadUser("TestName");
			assertEquals(user.getFullName(), user2.getFullName());
			assertEquals(user.getGender(), user2.getGender());
			assertEquals(user.getAge(), user2.getAge());
			assertEquals(user.getExperience(), user2.getExperience());
			assertEquals(user.getEmailAddress(), user2.getEmailAddress());
			assertEquals(user.getKeywords(), user2.getKeywords());
			assertTrue(user.getJobs().get(0).equals(user2.getJobs().get(0)));
			assertTrue(user.getJobs().get(1).equals(user2.getJobs().get(1)));
			File file = new File("data/TestName.csv");
			file.delete();
		} catch (Exception e) {
			fail();
		}
	}

}
