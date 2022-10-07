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
 * This class represents the DataService used for the "ApplicationSearch" feature of JobTracker.
 * The data is populated by using API calls to LinkedIn and CareerOneStop. 
 */
@SuppressWarnings("serial")
public class ApplicationSearchDataService extends DataService {

	/** Represents an instance of an ApplicationSearchDataService object */
    private static ApplicationSearchDataService INSTANCE;

    /** List of jobs for the ApplicationDataService returned by the api calls */
    private List<Jobs> jobs;

    /**
     * Constructor for ApplicationSearchDataService, which uses a string of key words to 
     * to search through LinkedIn for job postings. 
     * @param keywords string to limit the search (eg "Software Internship")
     */
    private ApplicationSearchDataService(String keywords) {
        try {
            jobs = ApiCalls.linkedInJobSearch(keywords);
        } catch (IOException e) {
            Notification.show(e.getMessage(), 3000, Position.TOP_CENTER);
        }

        if(jobs == null || jobs.size() == 0){
            jobs = new ArrayList<>();
            jobs.add(new Jobs("", "", null, null, "", "", "", "", 0.0));
        }
    }

    /**
     * Returns an instance of ApplicationSearchDataService.
     * @param keywords string to limit the search
     * @return an ApplicationSearchDataService object
     */
    public synchronized static DataService getInstance(String keywords) {
        INSTANCE = new ApplicationSearchDataService(keywords);
        return INSTANCE;
    }

    @Override
    public synchronized List<Jobs> getAllJobs() {
        return Collections.unmodifiableList(jobs);
    }

    @Override
    public synchronized Jobs getJobsbyId(int jobId) {
        return jobs.get(jobId);
    }
}
