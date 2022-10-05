package com.group21.jobTracker.ui;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.group21.jobTracker.backend.mock.ApplicationSearchDataService;

public class dataServiceTest {
	   private ApplicationSearchDataService service;

	    @Before
	    public void setUp() throws Exception {
	        service = (ApplicationSearchDataService) ApplicationSearchDataService.getInstance("Software Engineer");
	    }

	    @Test
	    public void testDataServiceCanFetchProducts() throws Exception {
	    	ApplicationSearchDataService.getInstance("Software Engineer");
	    	assertFalse(service.getAllJobs().isEmpty());
	    	assertNotNull(service.getJobsbyId(0));
	    }
	    
	}
