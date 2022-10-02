package com.group21.jobTracker.csv;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;

import com.group21.jobTracker.backend.data.Jobs;
import com.group21.jobTracker.backend.data.User;

class CsvTest {

	@Test
	void test() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String date1 = "07/12/2022";
		String date2 = "02/03/2022";
		try {
			User user = new User("Test1_Parker", "male", null, "GED", "Software engineer");
			Jobs job = new Jobs("Title", "Company", formatter.parse(date1), formatter.parse(date2), "40k", "description", "action", "status", "priority");
			Jobs job2 = new Jobs("Title2", "Company2", formatter.parse(date1), formatter.parse(date2), "50k", "description2", "action2", "status2", "priority2");
			user.addJob(job);
			user.addJob(job2);
			Csv.saveUser(user);
			User user2 = Csv.loadUser("Test1_Parker");
			assertEquals(user.getName(), user2.getName());
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
