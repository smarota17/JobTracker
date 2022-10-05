package com.group21.jobTracker.ui.jobBoard;

import com.group21.jobTracker.backend.data.Jobs;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;

/**
 * Grid of products, handling the visual presentation and filtering of a set of
 * items. This version uses an in-memory data source that is suitable for small
 * data sets.
 */
@SuppressWarnings("serial")
public class JobGrid extends Grid<Jobs> {

    public JobGrid() {

    	System.out.println("inside the job grid:");
        setSizeFull();

        addColumn(Jobs::getJobTitle).setHeader("Job Title").setResizable(true)
                .setFlexGrow(20).setSortable(true).setKey("jobTitle");
                
        addColumn(Jobs::getCompany).setHeader("Company").setResizable(true)
                .setFlexGrow(20).setSortable(true).setKey("jobCompany");
        
        addColumn(Jobs::getStatus).setHeader("Status").setResizable(true)
        .setFlexGrow(20).setSortable(true).setKey("jobStatus");

        UI.getCurrent().getPage().addBrowserWindowResizeListener(
                e -> setColumnVisibility(e.getWidth()));
    }


	private void setColumnVisibility(int width) {
		getColumnByKey("jobTitle").setVisible(true);
        getColumnByKey("jobCompany").setVisible(true);
        getColumnByKey("jobStatus").setVisible(true);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);

        // fetch browser width
        UI.getCurrent().getInternals().setExtendedClientDetails(null);
        UI.getCurrent().getPage().retrieveExtendedClientDetails(e -> {
            setColumnVisibility(e.getBodyClientWidth());
        });
    }

    public Jobs getSelectedRow() {
        return asSingleSelect().getValue();
    }

    public void refresh(Jobs job) {
        getDataCommunicator().refresh(job);
    }
}
