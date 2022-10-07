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
public class ApplicationSearchDataService extends DataService {

    private static ApplicationSearchDataService INSTANCE;

    private List<Jobs> jobs;

    private ApplicationSearchDataService(String keywords) {
        try {
            jobs = ApiCalls.linkedInJobSearch(keywords);
        } catch (IOException e) {
            Notification.show(e.getMessage(),3000, Position.TOP_CENTER);
        }

        if(jobs == null || jobs.size() == 0){
            jobs = new ArrayList<>();
            jobs.add(new Jobs("", "", null, null, "", "", "", "", 0.0));
        }
    }

    public synchronized static DataService getInstance(String keywords) {
        INSTANCE = new ApplicationSearchDataService(keywords);
        return INSTANCE;
    }

    /* returning jobs */
    @Override
    public synchronized List<Jobs> getAllJobs() {
        return Collections.unmodifiableList(jobs);
    }

    @Override
    public synchronized Jobs getJobsbyId(int jobId) {
        return jobs.get(jobId);
    }
}
