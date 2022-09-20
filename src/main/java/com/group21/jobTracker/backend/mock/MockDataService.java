package com.group21.jobTracker.backend.mock;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.group21.jobTracker.backend.DataService;
import com.group21.jobTracker.backend.data.Category;
import com.group21.jobTracker.backend.data.Jobs;

/**
 * Mock data model. This implementation has very simplistic locking and does not
 * notify users of modifications.
 */
public class MockDataService extends DataService {

    private static MockDataService INSTANCE;

    private List<Jobs> jobs;
    private List<Category> categories;
    private int nextJobId = 0;
    private int nextCategoryId = 0;

    private MockDataService() {
        categories = MockDataGenerator.createCategories();
        jobs = MockDataGenerator.createJobs(categories);
        nextJobId = jobs.size() + 1;
        nextCategoryId = categories.size() + 1;
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

    @Override
    public synchronized List<Category> getAllCategories() {
        return Collections.unmodifiableList(categories);
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

    @Override
    public void updateCategory(Category category) {
        if (category.getId() < 0) {
            category.setId(nextCategoryId++);
            categories.add(category);
        }
    }

    /* delete  jobs based on the category*/
    @Override
    public void deleteCategory(int categoryId) {
        if (categories.removeIf(category -> category.getId() == categoryId)) {
            getAllJobs().forEach(job -> {
                job.getCategory().removeIf(category -> category.getId() == categoryId);
            });
        }
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
