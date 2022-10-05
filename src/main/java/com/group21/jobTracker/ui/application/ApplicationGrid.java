package com.group21.jobTracker.ui.application;

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
public class ApplicationGrid extends Grid<Jobs> {

    public ApplicationGrid() {
    	
    	System.out.println("inside the application grid: ");
        setSizeFull();

        addColumn(Jobs::getJobTitle).setHeader("Job Title").setResizable(true)
                .setFlexGrow(20).setSortable(true).setKey("jobTitle");

        addColumn(Jobs::getCompany).setHeader("Company").setResizable(true)
                .setFlexGrow(20).setSortable(true).setKey("jobCompany");
        
        addColumn(Jobs::getDateApplied).setHeader("Date Applied").setResizable(true)
        .setFlexGrow(20).setSortable(true).setKey("jobDateApplied");
        
        addColumn(Jobs::getDueDate).setHeader("Due Date").setResizable(true)
        .setFlexGrow(20).setSortable(true).setKey("jobDueDate");
                
        addColumn(Jobs::getSalary).setHeader("Salary").setResizable(true)
        .setFlexGrow(20).setSortable(true).setKey("jobSalary");
        
        addColumn(Jobs::getJobDescription).setHeader("Description").setResizable(true)
        .setFlexGrow(20).setSortable(true).setKey("jobDescription");

        addColumn(Jobs::getNextAction).setHeader("Next Action").setResizable(true)
                .setFlexGrow(20).setSortable(true).setKey("jobNextAction");

        addColumn(Jobs::getStatus).setHeader("Status").setResizable(true)
                .setFlexGrow(20).setSortable(true).setKey("jobStatus");
        
        addColumn(Jobs::getPriority).setHeader("Priority").setResizable(true)
        .setFlexGrow(20).setSortable(true).setKey("jobPriority");
        

        // If the browser window size changes, check if all columns fit on
        // screen
        // (e.g. switching from portrait to landscape mode)
        UI.getCurrent().getPage().addBrowserWindowResizeListener(
                e -> setColumnVisibility(e.getWidth()));
    }
    
    private void setColumnVisibility(int width) {
        if (width > 800) {
            getColumnByKey("jobTitle").setVisible(true);
            getColumnByKey("jobCompany").setVisible(true);
            getColumnByKey("jobStatus").setVisible(true);
            getColumnByKey("jobDescription").setVisible(false);
            getColumnByKey("jobDateApplied").setVisible(true);
            getColumnByKey("jobDueDate").setVisible(true);
            getColumnByKey("jobSalary").setVisible(true);
            getColumnByKey("jobNextAction").setVisible(false);
            getColumnByKey("jobPriority").setVisible(true);

        } else if (width > 550) {
            getColumnByKey("jobTitle").setVisible(true);
            getColumnByKey("jobCompany").setVisible(true);
            getColumnByKey("jobStatus").setVisible(true);
            getColumnByKey("jobDescription").setVisible(false);
            getColumnByKey("jobDateApplied").setVisible(false);
            getColumnByKey("jobDueDate").setVisible(false);
            getColumnByKey("jobSalary").setVisible(true);
            getColumnByKey("jobNextAction").setVisible(false);
            getColumnByKey("jobPriority").setVisible(true);
        } else {
            getColumnByKey("jobTitle").setVisible(true);
            getColumnByKey("jobCompany").setVisible(true);
            getColumnByKey("jobStatus").setVisible(true);
            getColumnByKey("jobDescription").setVisible(false);
            getColumnByKey("jobDateApplied").setVisible(false);
            getColumnByKey("jobDueDate").setVisible(false);
            getColumnByKey("jobSalary").setVisible(false);
            getColumnByKey("jobNextAction").setVisible(false);
            getColumnByKey("jobPriority").setVisible(true);
        }
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
