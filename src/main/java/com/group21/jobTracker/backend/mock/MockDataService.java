package com.group21.jobTracker.backend.mock;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.group21.jobTracker.Api.ApiCalls;
import com.group21.jobTracker.backend.DataService;
import com.group21.jobTracker.backend.data.Jobs;
import com.group21.jobTracker.ui.MainLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;

/**
 * Mock data model. This implementation has very simplistic locking and does not
 * notify users of modifications.
 */
@SuppressWarnings("serial")
public class MockDataService extends DataService {
	/** Private static MocDatService to instantiate the dataservice instance for the app */
    private static MockDataService INSTANCE;
    /** Private List variable to hold jobs from api calls */
    private List<Jobs> jobs;
    /** Private int to maintain the unique job id for each new job and save with unique job Id*/
    private int nextJobId = 0;

    /**
	 * Make API calls for Linkedin to load jobs in the application
	 * 
	 */
    private MockDataService() {
        try {
            jobs = ApiCalls.linkedInJobSearch(MainLayout.userName);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Notification.show(e.getMessage(),3000, Position.TOP_CENTER);
        }

        if(jobs == null || jobs.size() == 0){
            jobs = new ArrayList<>();
            jobs.add(new Jobs("", "", null, null, "", "", "", "", 0.0));
        }
        nextJobId = jobs.size() + 1;
    }

    /**
	 * Initialize the DatService Instance 
	 * @return MocDataService instance which contain the list of  jobs from linkedin api 
	 * 
	 */
    public synchronized static DataService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MockDataService();
        }
        return INSTANCE;
    }

    /**
	 * This Function is to get all jobs for the user general dashboard
	 * @return a collection of Jobs for the User to show on Job Board View
	 */
    @Override
    public synchronized List<Jobs> getAllJobs() {
        return Collections.unmodifiableList(jobs);
    }


    /**
	 * This Function is to update any existing Job for the User
	 * if new job then it would add to the job list otherwise it would 
	 * update the job with necessary changes and return void
	 * The function will throw illegalArgument Exception if the job id is invalid
	 */
    @Override
    public synchronized void updateJob(Jobs j) {
        if (j.getId() < 0) {
            // New product
            j.setId(nextJobId++);
            jobs.add(j);
            return;
        }
        for (int i = 0; i < jobs.size(); i++) {
            if (jobs.get(i).getId() == j.getId()) {
            	jobs.set(i, j);
                return;
            }
        }

        throw new IllegalArgumentException("No Job with id " + j.getId()
                + " found");
    }

    /**
	 * This Function is to Search job by Id 
	 * @return null if no job found else return the job which was looking for
	 */
    @Override
    public synchronized Jobs getJobsbyId(int jobId) {
        for (int i = 0; i < jobs.size(); i++) {
            if (jobs.get(i).getId() == jobId) {
                return jobs.get(i);
            }
        }
        return null;
    }

    /**
	 * This Function is to delete job by Id 
	 * 
	 */
    @Override
    public synchronized void deleteJob(int jobId) {
        Jobs j = getJobsbyId(jobId);
        if (j == null) {
            throw new IllegalArgumentException("Job with id " + jobId
                    + " not found");
        }
        jobs.remove(j);
    }
}
