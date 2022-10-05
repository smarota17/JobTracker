package com.group21.jobTracker.ui.applicationSearch;

import java.io.Serializable;

import com.group21.jobTracker.backend.DataService;
import com.group21.jobTracker.backend.data.Jobs;
import com.vaadin.flow.component.UI;

/**
 * This class provides an interface for the logical operations between the CRUD
 * view, its parts like the product editor form and the data source, including
 * fetching and saving products.
 *
 * Having this separate from the view makes it easier to test various parts of
 * the system separately, and to e.g. provide alternative views for the same
 * data.
 */
public class ApplicationSearchViewLogic implements Serializable {

    private final ApplicationSearchView view;

    public ApplicationSearchViewLogic(ApplicationSearchView simpleCrudView) {
        view = simpleCrudView;
    }

    /**
     * Does the initialization of the inventory view including disabling the
     * buttons if the user doesn't have access.
     */
    public void init() {
        //check authentication
        if (false) {
            view.setnewApplicationEnabled(false);
        }
    }

    public void cancelProduct() {
        setFragmentParameter("");
        view.clearSelection();
    }

    /**
     * Updates the fragment without causing InventoryViewLogic navigator to
     * change view. It actually appends the productId as a parameter to the URL.
     * The parameter is set to keep the view state the same during e.g. a
     * refresh and to enable bookmarking of individual product selections.
     *
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
     * Opens the product form and clears its fields to make it ready for
     * entering a new product if productId is null, otherwise loads the product
     * with the given productId and shows its data in the form fields so the
     * user can edit them.
     *
     * 
     * @param jobId
     */
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

    private Jobs findJob(int jobId) {
        return DataService.get().getJobsbyId(jobId);
    }

    public void saveProduct(Jobs job) {
        final boolean newJob = job.isNewJob();
        view.clearSelection();
        view.updateProduct(job);
        setFragmentParameter("");
        view.showNotification(job.getJobTitle()
                + (newJob ? " created" : " updated"));
    }

    public void editJob(Jobs job) {
        if (job == null) {
            setFragmentParameter("");
        } else {
            setFragmentParameter(job.getId() + "");
        }
        view.editJob(job);
    }

    public void newJob() {
        view.clearSelection();
        setFragmentParameter("new");
        view.editJob(new Jobs());
    }

    public void rowSelected(Jobs job) {
        //check authentication
        if (true) {
            editJob(job);
        }
    }
}
