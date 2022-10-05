package com.group21.jobTracker.backend;

import java.io.Serializable;
import java.util.Collection;

import com.group21.jobTracker.backend.data.Category;
import com.group21.jobTracker.backend.data.Jobs;
import com.group21.jobTracker.backend.mock.JobDataService;
import com.group21.jobTracker.backend.mock.ApplicationSearchDataService;
import com.group21.jobTracker.backend.mock.MockDataService;

/**
 * This interface is used to retrieve and update data from the CSV files.
 */
public abstract class DataService implements Serializable {

	/**
	 * Returns a list of jobs from the CSV file.
	 * @return List of Jobs objects
	 */
    public abstract Collection<Jobs> getAllJobs();

    /**
     * Method to update a given Jobs object.
     * @param p Jobs object to update in the CSV file.
     */
    public abstract void updateJob(Jobs p);

    /**
     * Method to delete a given Jobs object.
     * @param jobId ID of the job object to delete
     */
    public abstract void deleteJob(int jobId);

    /**
     * Return a Jobs object from the CSV file given an id. 
     * @param jobId id of the Jobs object you are searching for
     * @return the Jobs object with the given id
     */
    public abstract Jobs getJobsbyId(int jobId);

    public static DataService get() {
        return MockDataService.getInstance();
    }
    
    public static DataService getJob() {
        return JobDataService.getInstance();
    }

    public static DataService getSearchResults(String keywords) {
        return ApplicationSearchDataService.getInstance(keywords);
    }
    

}
