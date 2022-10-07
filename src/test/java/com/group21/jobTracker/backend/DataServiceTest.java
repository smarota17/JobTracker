package com.group21.jobTracker.backend;

import org.junit.Test;

import com.group21.jobTracker.backend.data.Jobs;
import com.group21.jobTracker.backend.data.User;
import com.group21.jobTracker.backend.mock.ApplicationSearchDataService;
import com.group21.jobTracker.backend.mock.JobDataService;
import com.group21.jobTracker.backend.mock.MockDataService;
import com.group21.jobTracker.csv.Csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

/**
 * Simple unit test for the back-end data service.
 */
public class DataServiceTest {

	/** DataService Object used for testing */
    private DataService service;

    /**
     * Tests the ApplicationSearchDataService class
     */
    @Test
    public void ApplicationSearchDataServiceTest() {
    	service = ApplicationSearchDataService.getInstance("Software Engineer");
    	Object[] jobs = service.getAllJobs().toArray();
    	assertEquals(5, jobs.length);
		for(int i = 0; i < 5; i++) {
			assertNotNull(((Jobs)jobs[i]).getJobTitle());
    		assertNotNull(((Jobs)jobs[i]).getJobDescription());
    		assertNotNull(((Jobs)jobs[i]).getCompany());
		}
    }
    
    /**
     * Tests the ApplicationSearchTest class
     */
    @Test
    public void ApplicationSearchTest() {
    	service = MockDataService.getInstance();
    	Object[] jobs = service.getAllJobs().toArray();
    	assertEquals(5, jobs.length);
    	for(int i = 0; i < 5; i++) {
    		assertNotNull(((Jobs)jobs[i]).getJobTitle());
    		assertNotNull(((Jobs)jobs[i]).getJobDescription());
    		assertNotNull(((Jobs)jobs[i]).getCompany());
    	}
    }
    
    /**
     * Tests the JobDataService class
     */
    @Test
    public void JobDataServiceTest()  {
    	
		User user = new User("TestName", "TestEmail", "male", null, "1.5", "Software engineer" );
		Jobs job1 = new Jobs("Title", "Company",null, null, "40k", "description", "action", "status", 1);
		Jobs job2 = new Jobs("Title2", "Company2", null, null, "50k", "description2", "action2", "status2", 2);
		user.addJob(job1);
		user.addJob(job2);
		Csv.saveUser(user);
		service = new JobDataService("TestName");
		Object[] jobs =  service.getAllJobs().toArray();
		assertTrue(job1.equals((Jobs) jobs[0]));
		assertTrue(job2.equals((Jobs) jobs[1]));
		
		File file = new File("data/TestName.csv");
		file.delete();
    }
}
