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
		User user = new User("John", "Smith", "male", "Field", "Education", "Keywords");
		assertEquals(user.getFirstName(), "John");
		assertEquals(user.getLastName(), "Smith");
		assertEquals(user.getGender(), "Male");
		assertEquals(user.getField(), "Field");
		assertEquals(user.getEducation(), "Education");
		assertEquals(user.getKeywords(), "Keywords");
		assertNull(user.getEmailAddress());
	}

}
