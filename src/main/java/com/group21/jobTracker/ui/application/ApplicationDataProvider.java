package com.group21.jobTracker.ui.application;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import com.group21.jobTracker.backend.DataService;
import com.group21.jobTracker.backend.data.Jobs;

import com.group21.jobTracker.backend.data.User;
import com.group21.jobTracker.backend.mock.JobDataService;
import com.group21.jobTracker.csv.Csv;
import com.group21.jobTracker.ui.MainLayout;
import com.vaadin.flow.data.provider.ListDataProvider;

/**
 * This class provides filtering and other "CRUD" operations for Jobs objects on the "My Application"
 * page.
 */
@SuppressWarnings("serial")
public class ApplicationDataProvider extends ListDataProvider<Jobs> {

    /** Text filter that can be changed separately. */
    private String filterText = "";

    /**
     * Constructor to pull a list of job objects to the data service.
     */
    public ApplicationDataProvider() {
        super(JobDataService.getJob().getAllJobs());
    }
    
    /**
     * Store given product to the backing data service.
     *
     * @param job the updated or new product
     * @throws IllegalArgumentException  thrown if there is a format issue parsing the CSV files for the user
     */
    public void save(Jobs job) {
        final boolean newProduct = job.isNewJob();
        User currentUser;
        
		try {
			currentUser = Csv.loadUser(MainLayout.userName.replaceAll("\\s", "_"));
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Cannot read/write to file.");
		} catch (ParseException e) {
			throw new IllegalArgumentException("Cannot read/write to file.");
		}
        
        if (!newProduct) {
            currentUser.deleteExistingJob(job);
        }
        
        currentUser.addJob(job);
        Csv.saveUser(currentUser);
        JobDataService.getInstance();
        refreshAll();
    }

    /**
     * Delete given product from the backing data service.
     *
     * @param job the job to be deleted
     * @throws IllegalArgumentException thrown if the data cannot be stored to the CSV file
     * @throws NumberFormatException thrown if the data cannot be stored to the CSV file
     *            
     */
    public void delete(Jobs job) {
    	final boolean newProduct = job.isNewJob();
        User currentUser;
		try {
			currentUser = Csv.loadUser(MainLayout.userName.replaceAll("\\s", "_"));
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Cannot read/write to file.");
		} catch (ParseException e) {
			throw new IllegalArgumentException("Cannot read/write to file.");
		}
        
        if (!newProduct) {
            currentUser.deleteExistingJob(job);
        }
        Csv.saveUser(currentUser);
        
        refreshAll();
        
    }

    /**
     * Filters the data for the specific filter text
     * @param filterText string to filter by
     * @return list of jobs that match the filter text
     */
    public List<Jobs> updateList(String filterText) {
    	if (filterText == null || filterText.isEmpty()) {
    		return ((JobDataService) DataService.getJob()).getPriority();
    	}
    	return ((JobDataService) DataService.getJob()).getJobsByName(filterText);
    }

    /**
     * Get jobid based on job object 
     *
     * @param job object
     *            
     */
    @Override
    public Integer getId(Jobs job) {
        Objects.requireNonNull(job,
                "Cannot provide an id for a null Job.");

        return job.getId();
    }
}
