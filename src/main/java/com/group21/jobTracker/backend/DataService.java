package com.group21.jobTracker.backend;

import java.io.Serializable;
import java.util.Collection;

import com.group21.jobTracker.backend.data.Jobs;
import com.group21.jobTracker.backend.mock.JobDataService;
import com.group21.jobTracker.backend.mock.ApplicationSearchDataService;
import com.group21.jobTracker.backend.mock.MockDataService;

/**
 * This interface is used to retrieve and update data to and from the CSV files. It is also 
 * used to populate grids on the UI. 
 */
@SuppressWarnings("serial")
public abstract class DataService implements Serializable {

	/**
	 * Returns a list of jobs from the CSV file.
	 * @return List of Jobs objects
	 */
    public abstract Collection<Jobs> getAllJobs();


    /**
     * Return a Jobs object from the CSV file given an id. 
     * @param jobId id of the Jobs object you are searching for
     * @return the Jobs object with the given id
     */
    public abstract Jobs getJobsbyId(int jobId);

    /**
     * Return an instance of MockDataService.
     * @return instance of MockDataService
     */
    public static DataService get() {
        return MockDataService.getInstance();
    }
    
    /**
     * Returns a JobDataService object 
     * @return JobDataService object
     */
    public static DataService getJob() {
        return JobDataService.getInstance();
    }

    /**
     * Returns a ApplicationSearchDataService object
     * @param keywords limits the search for API calls
     * @return ApplicationSearchDataService object
     */
    public static DataService getSearchResults(String keywords) {
        return ApplicationSearchDataService.getInstance(keywords);
    }
    

}
