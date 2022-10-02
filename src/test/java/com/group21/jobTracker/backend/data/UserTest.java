/**
 * 
 */
package com.group21.jobTracker.backend.data;

import static org.junit.jupiter.api.Assertions.*;

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

}
