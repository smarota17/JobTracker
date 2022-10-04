package com.group21.jobTracker.backend;

import java.io.Serializable;
import java.util.Collection;

import com.group21.jobTracker.backend.data.Category;
import com.group21.jobTracker.backend.data.Jobs;
import com.group21.jobTracker.backend.mock.ApplicationSearchDataService;
import com.group21.jobTracker.backend.mock.MockDataService;

/**
 * Back-end service interface for retrieving and updating product data.
 */
public abstract class DataService implements Serializable {

    public abstract Collection<Jobs> getAllJobs();

    public abstract void updateJob(Jobs p);

    public abstract void deleteJob(int jobId);

    public abstract Jobs getJobsbyId(int jobId);

    public static DataService get() {
        return MockDataService.getInstance();
    }

    public static DataService getSearchResults(String keywords) {
        return ApplicationSearchDataService.getInstance(keywords);
    }
    

}
