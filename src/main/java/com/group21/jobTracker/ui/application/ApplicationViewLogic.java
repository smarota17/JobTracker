package com.group21.jobTracker.ui.application;

import java.io.Serializable;
import com.group21.jobTracker.backend.data.Jobs;
import com.group21.jobTracker.backend.mock.JobDataService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;

/**
 * This class provides an interface for the logical operations between the CRUD
 * view, its parts like the Job editor form and the data source, including
 * fetching and saving jobs.
 *
 * Having this separate from the view makes it easier to test various parts of
 * the system separately, and to e.g. provide alternative views for the same
 * data.
 */
@SuppressWarnings("serial")
public class ApplicationViewLogic implements Serializable {

	/** private final Application view instance to handle any CRUD operation from the UI*/
    private final ApplicationView view;

    /**
     * ApplicationViewLogic constructor to initialize the CRUD view object
     * @param simpleCrudView application view UI
     *
     */
    public ApplicationViewLogic(ApplicationView simpleCrudView) {
        view = simpleCrudView;
    }

    /**
     * The function simply remove everything fromt he application form if someone want to cancel 
     * any modification of any existing job or new job
     *
     */
    public void cancelJob() {
        setFragmentParameter("");
        view.clearSelection();
    }

    /**
     * Updates the fragment without causing ApplicationViewLogic navigator to
     * change view. It actually appends the JobId as a parameter to the URL.
     * The parameter is set to keep the view state the same during e.g. a
     * refresh and to enable bookmarking of individual job selections.
     * @param jobId id of the job to add to URL
     */
    private void setFragmentParameter(String jobId) {
        String fragmentParameter;
        if (jobId == null || jobId.isEmpty()) {
            fragmentParameter = "";
        } else {
            fragmentParameter = jobId;
        }

        UI.getCurrent().navigate(ApplicationView.class, fragmentParameter);
    }

    /**
     * Opens the Application form and clears its fields to make it ready for
     * entering a new Job if Id is null, otherwise loads the Jobs
     * with the given jobId and shows its data in the form fields so the
     * user can edit them.
     *
     * 
     * @param jobId of the job object to load
     * @throws NumberFormatException
     **/
    public void enter(String jobId) {
        if (jobId != null && !jobId.isEmpty()) {
            if (jobId.equals("new")) {
                newJob();
            } else {
                // Ensure this is selected even if coming directly here from
                // login
                try {
                    final int pid = Integer.parseInt(jobId);
                    final Jobs product = findJob(pid);
                    view.selectRow(product);
                } catch (final NumberFormatException e) {
                }
            }
        } else {
            view.showForm(false);
        }
    }

    /**
     * This function use JobId to find a specific job from the system 
     * by using JobDataService instance for the User
     * @param jobId id of the job to find
     * @return Jobs object with given id
     **/
    private Jobs findJob(int jobId) {
        return JobDataService.getJob().getJobsbyId(jobId);
    }

    /**
     * This function use a job object as parameter and check whether the job is a new job or not by isNewJob()
     * after that its clearing the fields on the application form. using the view instance to call 
     * the updatejob method to save or update the job 
     * @param job object to save
     * @throws IllegalArgumentException if you can't read or write to the file
     * @see Notification#show(String)
     **/
    public void saveProduct(Jobs job) {
        final boolean newJob = job.isNewJob();
        view.clearSelection();
        
        try {
			view.updateProduct(job);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Cannot read/write to file.");
		} 
        
        setFragmentParameter("");
        view.showNotification(job.getJobTitle()
                + (newJob ? " created" : " updated"));
    }

    /**
     * This function use a job object as parameter and utilize the view object to call 
     * the delete method for the job instance. After deleting the job a popup message will show
     * as the success of removing the job
     *
     * @param job object to delete
     * @see Notification#show(String)
     **/
    public void deleteJob(Jobs job) {
        view.clearSelection();
        view.removeProduct(job);
        setFragmentParameter("");
        view.showNotification(job.getJobTitle() + " removed");
    }

    /**
     * This function use a job object as parameter and utilize the view object to call 
     * the edit method for the job instance. After it will pop up the information from the 
     * desired job of grids to the application form.
     * @param job to edit 
     * 
     **/
    public void editJob(Jobs job) {
        if (job == null) {
            setFragmentParameter("");
        } else {
            setFragmentParameter(job.getId() + "");
        }
        view.editJob(job);
    }

    /**
     * This function use a job object as parameter and utilize the view to clear all previous selctions 
     * for adding new job. then it would call the edit function to load that jobo
     */
    public void newJob() {
        view.clearSelection();
        setFragmentParameter("new");
        view.editJob(new Jobs());
    }

    /**
     * Row selection method pop up the job selected from the dashboard grid and show it on the application form
     * @param job object for the row object
     * 
     **/
    public void rowSelected(Jobs job) {
        //check authentication
        if (true) {
            editJob(job);
        }
    }
}
