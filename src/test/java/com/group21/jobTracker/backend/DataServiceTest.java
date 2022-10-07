package com.group21.jobTracker.backend;

import org.junit.Test;

import com.group21.jobTracker.backend.data.Jobs;
import com.group21.jobTracker.backend.data.User;
import com.group21.jobTracker.backend.mock.JobDataService;
import com.group21.jobTracker.csv.Csv;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

/**
 * Simple unit test for the back-end data service.
 */
public class DataServiceTest {
    
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
		DataService service = new JobDataService("TestName");
		Object[] jobs =  service.getAllJobs().toArray();
		assertTrue(job1.equals((Jobs) jobs[0]));
		assertTrue(job2.equals((Jobs) jobs[1]));
		
		File file = new File("data/TestName.csv");
		file.delete();
    }
}
