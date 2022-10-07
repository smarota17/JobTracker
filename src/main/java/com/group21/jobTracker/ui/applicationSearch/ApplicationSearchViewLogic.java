package com.group21.jobTracker.ui.applicationSearch;

import java.io.Serializable;

import com.group21.jobTracker.backend.DataService;
import com.group21.jobTracker.backend.data.Jobs;
import com.vaadin.flow.component.UI;

/**
 * This class provides an interface for the logical operations between the CRUD
 * view, its parts like the job editor form and the backend data, including
 * fetching and saving jobs.
 */
@SuppressWarnings("serial")
public class ApplicationSearchViewLogic implements Serializable {

	/** Represents the UI */
    private final ApplicationSearchView view;

    /**
     * Constructor
     * @param simpleCrudView UI for the logic
     */
    public ApplicationSearchViewLogic(ApplicationSearchView simpleCrudView) {
        view = simpleCrudView;
    }

    /**
     * Method to cancel editing a job
     */
    public void cancelProduct() {
        setFragmentParameter("");
        view.clearSelection();
    }

    /**
     * Updates the fragment without causing the UI to
     * change view. Appends the product ID to the URL to keep the same object in view
     * when editing/refreshing. 
     * @param jobId id of the job we are editing to append to the url
     */
    private void setFragmentParameter(String jobId) {
        String fragmentParameter;
        if (jobId == null || jobId.isEmpty()) {
            fragmentParameter = "";
        } else {
            fragmentParameter = jobId;
        }

        UI.getCurrent().navigate(ApplicationSearchView.class, fragmentParameter);
    }

    /**
     * Opens the form and clears its fields to make it ready for
     * entering a new job if job id is null, otherwise loads the form with the
     * data of the given job id. 
     * 
     * @param jobId id of the job to show in form
     */
    public void enter(String jobId) {
        if (jobId != null && !jobId.isEmpty()) {
            if (jobId.equals( "new" )) {
                newJob();
            } else {
                // Ensure this is selected even if coming directly here from
                // login
                try {
                    final int pid = Integer.parseInt(jobId);
                    final Jobs product = findJob(pid);
                    view.selectRow(product);
                } catch (final NumberFormatException e) {
                	System.out.println(e.getMessage());
                }
            }
        } else {
            view.showForm(false);
        }
    }

    private Jobs findJob(int jobId) {
        return DataService.get().getJobsbyId(jobId);
    }

    /**
     * Method to save a job object from the Application Search page.
     * @param job object to save
     */
    public void saveProduct(Jobs job) {
        final boolean newJob = job.isNewJob();
        view.clearSelection();
        view.updateProduct(job);
        setFragmentParameter("");
        view.showNotification(job.getJobTitle()
                + (newJob ? " created" : " updated"));
    }

    /**
     * Method to edit a job from the Application Search page.
     * @param job object to edit
     */
    public void editJob(Jobs job) {
        if (job == null) {
            setFragmentParameter("");
        } else {
            setFragmentParameter(job.getId() + "");
        }
        view.editJob(job);
    }

    /**
     * Method to create a new job object for the form.
     */
    public void newJob() {
        view.clearSelection();
        setFragmentParameter("new");
        view.editJob(new Jobs());
    }

    /**
     * Method for when a row is selected in the ApplicationSearch grid.
     * @param job object to edit when selected in the row
     */
    public void rowSelected(Jobs job) {
        //check authentication
        if (true) {
            editJob(job);
        }
    }
}
