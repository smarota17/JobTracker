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
 *
 */
class ApiCallsTest {

	/**
	 * Tests the linkedIn web crawler
	 */
	@Test
	void linkedInTest(){
		try {
			ArrayList<Jobs> jobs = ApiCalls.linkedInJobSearch("Software Developer");
//			assertEquals(5, jobs.size());
//			assertNotNull(jobs.get(1).getName());
		} catch (Exception e) {
			fail();
		}
		
	}

	
	/**
	 * Tests the CareerOneStop web crawler
	 */
	@Test
	void careerOneStopTest(){
		try {
			ArrayList<Jobs> jobs = ApiCalls.careerOneStopJobSearch("Software Developer");
//			assertEquals(5, jobs.size());
//			assertNotNull(jobs.get(1).getName());
		} catch (Exception e) {
			fail();
		}
		
	}
}
