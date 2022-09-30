package com.group21.jobTracker.backend;

import org.junit.Before;
import org.junit.Test;

import com.group21.jobTracker.backend.DataService;
import com.group21.jobTracker.backend.data.Jobs;
import com.group21.jobTracker.backend.mock.MockDataService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Simple unit test for the back-end data service.
 */
public class DataServiceTest {

    private DataService service;

    @Before
    public void setUp() throws Exception {
        service = MockDataService.getInstance();
    }

    @Test
    public void testDataServiceCanFetchProducts() throws Exception {
        assertFalse(service.getAllJobs().isEmpty());
    }

    @Test
    public void testDataServiceCanFetchCategories() throws Exception {
        assertFalse(service.getAllCategories().isEmpty());
    }

    @Test
    public void testUpdateProduct_updatesTheProduct() throws Exception {
        Jobs p = service.getAllJobs().iterator().next();
        p.setJobTitle("My Test Name");
        service.updateJob(p);
        Jobs p2 = service.getAllJobs().iterator().next();
        assertEquals("My Test Name", p2.getJobTitle());
    }
}