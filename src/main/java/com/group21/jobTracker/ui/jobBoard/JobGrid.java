package com.group21.jobTracker.ui.jobBoard;

import com.group21.jobTracker.backend.data.Jobs;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;

/**
 * Grid of Jobs, handling the visual presentation and filtering of a set of
 * items. This version uses an in-memory data source that is suitable for small
 * data sets.
 */
@SuppressWarnings("serial")
public class JobGrid extends Grid<Jobs> {

	/**
	 * Job Grid Constructor which is defining the grid designing by adding the desired column on the Job
	 * Grid. We used Job Title, Company, Status from the jobs object to to show in grid.
	 * This is a grid view of all applied or selected jobs for a particular user.
	 */
    public JobGrid() {

        setSizeFull();

        addColumn(Jobs::getJobTitle).setHeader("Job Title").setResizable(true)
                .setFlexGrow(20).setSortable(false).setKey("jobTitle");
                
        addColumn(Jobs::getCompany).setHeader("Company").setResizable(true)
                .setFlexGrow(20).setSortable(false).setKey("jobCompany");
        
        addColumn(Jobs::getStatus).setHeader("Status").setResizable(true)
        .setFlexGrow(20).setSortable(false).setKey("jobStatus");
        
        addColumn(Jobs::getPriority).setHeader("Priority").setResizable(true)
        .setFlexGrow(20).setSortable(false).setKey("priority");

        UI.getCurrent().getPage().addBrowserWindowResizeListener(
                e -> setColumnVisibility(e.getWidth()));
    }

    /**
	 * Application Grid Column visibility based on the information length at a given time for the user.
	 * 
	 * @param width to maintain the width of each columns based on the length of the job objects informations
	 */
	private void setColumnVisibility(int width) {
		getColumnByKey("jobTitle").setVisible(true);
        getColumnByKey("jobCompany").setVisible(true);
        getColumnByKey("jobStatus").setVisible(true);
        getColumnByKey("priority").setVisible(true);
    }
	/**
	 * This function is to attach documents if any
	 * 
	 * @param AttachEvent to set for attachment to the job application.
	 */
    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);

        // fetch browser width
        UI.getCurrent().getInternals().setExtendedClientDetails(null);
        UI.getCurrent().getPage().retrieveExtendedClientDetails(e -> {
            setColumnVisibility(e.getBodyClientWidth());
        });
    }

    /**
   	 * This function is to select row
   	 * 
   	 * @return selected row
   	 */
    public Jobs getSelectedRow() {
        return asSingleSelect().getValue();
    }

    /**
	 * This function is to refresh the selection and view of the application grid
	 * 
	 * 
	 */
    public void refresh(Jobs job) {
        getDataCommunicator().refresh(job);
    }
}
