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
