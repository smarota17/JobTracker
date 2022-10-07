package com.group21.jobTracker.backend.mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.group21.jobTracker.backend.DataService;
import com.group21.jobTracker.backend.data.Jobs;
import com.group21.jobTracker.csv.Csv;
import com.group21.jobTracker.ui.MainLayout;
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
	 * This method check null pointer exception and other exception showed the exception message in the User page.
	 */
    private JobDataService() { 
      
        try {
            String processedUserName = MainLayout.userName.replaceAll(" ", "_");
            jobs = Csv.loadUser(processedUserName).getJobs();
        }
        catch(NullPointerException e){
            Notification.show("User Name Invalid", 3000, Position.TOP_CENTER);
        }
        catch(Exception e){
            Notification.show(e.getMessage(), 3000, Position.TOP_CENTER);
        } 

        if(jobs == null){
            jobs = new ArrayList<>();
            jobs.add(new Jobs("", "", null, null, "", "", "", "", 0.0));
        }
	    

    }

    /**
	 * Constructor used for JUnit testing
	 * @param name of the user
	 */
    public JobDataService(String name) { 
      
        try {
            String processedUserName = name.replaceAll(" ", "_");
            jobs = Csv.loadUser(processedUserName).getJobs();
        }
        catch(NullPointerException e){
            Notification.show("User Name Invalid", 3000, Position.TOP_CENTER);
        }
        catch(Exception e){
            Notification.show(e.getMessage(), 3000, Position.TOP_CENTER);
        } 

        if(jobs == null){
            jobs = new ArrayList<>();
            jobs.add(new Jobs("", "", null, null, "", "", "", "", 0.0));
        }
        INSTANCE = this;

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
        return Collections.unmodifiableList(jobs);
    }
    
    /**
	 * This function gets the top 5 highest priority jobs
	 * @return a collection of the top 5 highest priority jobs
	 */
    public synchronized List<Jobs> getPriority() {
    	try {
            String processedUserName = MainLayout.userName.replaceAll(" ", "_");
            return Collections.unmodifiableList(Csv.loadUser(processedUserName).getJobsByPriority());
        }
        catch(NullPointerException e){
            Notification.show("User Name Invalid", 3000, Position.TOP_CENTER);
        }
        catch(Exception e){
            Notification.show(e.getMessage(), 3000, Position.TOP_CENTER);
        } 

        jobs = new ArrayList<>();
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
    
    /**
	 * This function is to Search job by string text 
	 * @param filterText the text to search for jobs by
	 * @return an empty list if no jobs are found found else return a list of matching jobs
	 */
    public synchronized List<Jobs> getJobsByName(String filterText) {
    	List<Jobs> list = new ArrayList<Jobs>();
        for (int i = 0; i < jobs.size(); i++) {
            if (jobs.get(i).getJobTitle().toLowerCase().contains(filterText.toLowerCase())) {
                list.add(jobs.get(i));
            }
        }
        return list;
    }
}
