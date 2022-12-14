/**
 * 
 */
package com.group21.jobTracker.Api;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.group21.jobTracker.backend.data.Jobs;

/**
 * Test class for the ApiCalls class. Tests methods and functionality.
 */
class ApiCallsTest {

	/**
	 * Tests the CareerOneStop web crawler
	 */
	@Test
	void careerOneStopTest(){
		try {
			ArrayList<Jobs> jobs = ApiCalls.careerOneStopJobSearch("Software Developer");
			assertEquals(5, jobs.size());
			assertNotNull(jobs.get(1).getJobTitle());
		} catch (Exception e) {
			fail();
		}
	}
}
