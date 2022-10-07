package com.group21.jobTracker.ui.jobBoard;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

import com.group21.jobTracker.backend.data.Jobs;

import com.group21.jobTracker.backend.DataService;
import com.group21.jobTracker.backend.mock.JobDataService;
import com.vaadin.flow.data.provider.ListDataProvider;

/**
 * This class allows for filtering and "CRUD" operations for the "Dashboard" page of the application. 
 */ 
@SuppressWarnings("serial")
public class JobDataProvider extends ListDataProvider<Jobs> {

    /** Text filter that can be changed separately. */
    private String filterText = "";

    /**
     * Constructor
     */
    public JobDataProvider() {
        super( ((JobDataService) DataService.getJob()).getPriority());
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
                "Cannot provide an id for a null product.");

        return job.getId();
    }
}
