package com.group21.jobTracker.backend.mock;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.group21.jobTracker.backend.DataService;
import com.group21.jobTracker.backend.data.Jobs;
import com.group21.jobTracker.csv.Csv;
import com.group21.jobTracker.ui.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;

/**
 * A Data Service class dedicated to User Job board. The implementation has very simplistic locking and does not
 * notify users of modifications.
 */
@SuppressWarnings("serial")
public class JobDataService extends DataService {
	/** Private static JobDataService object to instantiate the JobDataService instance for the User */
    private static JobDataService INSTANCE;

    /** Private List variable to hold jobs from the User File*/
    private List<Jobs> jobs;

    /**
	 * Taking the user name from the main layout to load the User saved Job into the system
	 * This method check nul pointer exception and other exception showed the exception message in the User page.
	 */
    private JobDataService() { 
      
        try {
            String processedUserName = MainLayout.userName.replace(" ", "");
            jobs = Csv.loadUser(processedUserName).getJobs();
        }
        catch(NullPointerException e){
            Notification.show("User Name Invalid",3000, Position.TOP_CENTER);
        }
        catch(Exception e){
            Notification.show(e.getMessage(),3000, Position.TOP_CENTER);
        } 

        if(jobs == null){
            jobs = new ArrayList<>();
            jobs.add(new Jobs("", "", null, null, "", "", "", "", 0.0));
        }
	    

    }

    /**
	 * Initialize the JobDatService Instance 
	 * @return MocDataService instance which contain the list of  jobs from User
	 */
    public synchronized static DataService getInstance() {
        INSTANCE = new JobDataService();
        return INSTANCE;
    }

    /**
	 * This Function is to get all jobs for the user. 
	 * @return a collection of Jobs for the USer to show on Application view for User
	 */
    public synchronized List<Jobs> getAllJobs() {
    	System.out.println("Getting jobs");
        return Collections.unmodifiableList(jobs);
    }

    /**
	 * This Function is to update any existing Job for the User
	 * 
	 */
    @Override
	public synchronized void updateJob(Jobs j) {
		if (j.getId() < 0) {
			jobs.add(j);
			return;
		}
		jobs.set(j.getId(), j);
		return;
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
