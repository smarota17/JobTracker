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

    private static MockDataService INSTANCE;

    private List<Jobs> jobs;
    private int nextJobId = 0;

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

    public synchronized static DataService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MockDataService();
        }
        return INSTANCE;
    }

    /* returning jobs */
    @Override
    public synchronized List<Jobs> getAllJobs() {
        return Collections.unmodifiableList(jobs);
    }


    /* updating job */
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

    /* Search jobs by Id*/
    @Override
    public synchronized Jobs getJobsbyId(int jobId) {
        for (int i = 0; i < jobs.size(); i++) {
            if (jobs.get(i).getId() == jobId) {
                return jobs.get(i);
            }
        }
        return null;
    }

    /* Delete method for Jobs*/
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
