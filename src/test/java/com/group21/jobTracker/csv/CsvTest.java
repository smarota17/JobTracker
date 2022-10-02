package com.group21.jobTracker.csv;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.group21.jobTracker.backend.data.Jobs;
import com.group21.jobTracker.backend.data.User;

class CsvTest {

	@Test
	void test() {
		String date1 = "2022-07-13";
		String date2 = "2022-12-31";
		try {
			User user = new User("Test1_Parker", "LastName", "male", null, "GED", "Software engineer" );
			Jobs job = new Jobs("Title", "Company", LocalDate.parse(date1), LocalDate.parse(date2), "40k", "description", "action", "status", "priority");
			Jobs job2 = new Jobs("Title2", "Company2", LocalDate.parse(date1), LocalDate.parse(date2), "50k", "description2", "action2", "status2", "priority2");
			user.addJob(job);
			user.addJob(job2);
			Csv.saveUser(user);
			User user2 = Csv.loadUser("Test1_Parker");
			assertEquals(user.getFirstName(), user2.getFirstName());
			assertEquals(user.getGender(), user2.getGender());
			assertEquals(user.getEducation(), user2.getEducation());
			assertEquals(user.getField(), user2.getField());
			assertEquals(user.getField(), user2.getField());
			assertEquals(user.getField(), user2.getField());
			assertTrue(user.getJobs().get(0).equals(user2.getJobs().get(0)));
			assertTrue(user.getJobs().get(1).equals(user2.getJobs().get(1)));
			
		} catch (Exception e) {
			fail();
		}
	}

}
