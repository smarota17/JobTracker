package com.group21.jobTracker.backend.mock;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.group21.jobTracker.Api.ApiCalls;
import com.group21.jobTracker.backend.DataService;
import com.group21.jobTracker.backend.data.Category;
import com.group21.jobTracker.backend.data.Jobs;
import com.group21.jobTracker.csv.Csv;
import com.group21.jobTracker.ui.MainLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;

/**
 * Mock data model. This implementation has very simplistic locking and does not
 * notify users of modifications.
 */
public class JobDataService extends DataService {

    private static JobDataService INSTANCE;

    private List<Jobs> jobs;
    private int nextJobId = 0;
//    private int nextCategoryId = 0;

    private JobDataService() { 
	    try {
			jobs = Csv.loadUser(MainLayout.userName).getJobs();
			for(Jobs j: jobs) {
				System.out.println(j);
			}
		} catch (NumberFormatException e) {
	        Notification.show(e.getMessage(),3000, Position.TOP_CENTER);
		} catch (ParseException e) {
	        Notification.show(e.getMessage(),3000, Position.TOP_CENTER);
		}

        nextJobId = jobs.size() + 1;
    }

    public synchronized static DataService getInstance() {
        INSTANCE = new JobDataService();
        return INSTANCE;
    }

    /* returning jobs */
    public synchronized List<Jobs> getAllJobs() {
    	System.out.println("Getting jobs");
        return Collections.unmodifiableList(jobs);
    }

    @Override
    public synchronized List<Category> getAllCategories() {
//        return Collections.unmodifiableList(categories);
    	return null;
    }

    /* updating job */
    @Override
    public synchronized void updateJob(Jobs j) {
        if (j.getId() < 0) {
//            // New product
//            j.setId(nextJobId++);
            jobs.add(j);
            return;
        }
        jobs.set(j.getId(), j);
       return;
//        throw new IllegalArgumentException("No Job with id " + j.getId()
//                + " found");
    	
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

//    @Override
//    public void updateCategory(Category category) {
//        if (category.getId() < 0) {
//            category.setId(nextCategoryId++);
//            categories.add(category);
//        }
//    }

//    /* delete  jobs based on the category*/
//    @Override
//    public void deleteCategory(int categoryId) {
//        if (categories.removeIf(category -> category.getId() == categoryId)) {
//            getAllJobs().forEach(job -> {
//                job.getJobType().removeIf(category -> category.getId() == categoryId);
//            });
//        }
//    }

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
