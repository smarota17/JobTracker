package com.group21.jobTracker.backend.mock;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.group21.jobTracker.Api.ApiCalls;
import com.group21.jobTracker.backend.DataService;
import com.group21.jobTracker.backend.data.Jobs;
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

    /**
	 * Make API calls for Linkedin to load jobs in the application
	 * @throws IOException
	 * 
	 **/
    private MockDataService() {
        try {
            jobs = ApiCalls.linkedInJobSearch(null);
        } catch (IOException e) {
            Notification.show(e.getMessage(),3000, Position.TOP_CENTER);
        }

        if(jobs == null || jobs.size() == 0){
            jobs = new ArrayList<>();
            jobs.add(new Jobs("", "", null, null, "", "", "", "", 0.0));
        }
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

}
