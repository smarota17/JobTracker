package com.group21.jobTracker.ui.application;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.stream.Collectors;

import com.group21.jobTracker.backend.data.Category;
import com.group21.jobTracker.backend.data.Jobs;
import com.group21.jobTracker.backend.data.Product;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.data.renderer.TemplateRenderer;

/**
 * Grid of products, handling the visual presentation and filtering of a set of
 * items. This version uses an in-memory data source that is suitable for small
 * data sets.
 */
public class ApplicationGrid extends Grid<Jobs> {

    public ApplicationGrid() {
    	
    	System.out.println("inside the application grid: ");
        setSizeFull();

        addColumn(Jobs::getJobTitle).setHeader("Job Title")
                .setFlexGrow(20).setSortable(true).setKey("jobTitle");

        addColumn(Jobs::getCompany).setHeader("Company")
                .setFlexGrow(20).setSortable(true).setKey("jobCompany");
        
        addColumn(Jobs::getDateApplied).setHeader("Date Applied")
        .setFlexGrow(20).setSortable(true).setKey("jobDateApplied");
        
        addColumn(Jobs::getDueDate).setHeader("Due Date")
        .setFlexGrow(20).setSortable(true).setKey("jobDueDate");
                
        addColumn(Jobs::getSalary).setHeader("Salary")
        .setFlexGrow(20).setSortable(true).setKey("jobSalary");
        
        addColumn(Jobs::getJobDescription).setHeader("Description")
        .setFlexGrow(20).setSortable(true).setKey("jobDescription");

        addColumn(Jobs::getNextAction).setHeader("Next Action")
                .setFlexGrow(20).setSortable(true).setKey("jobNextAction");

        addColumn(Jobs::getStatus).setHeader("Status")
                .setFlexGrow(20).setSortable(true).setKey("jobStatus");
        
        addColumn(Jobs::getPriority).setHeader("Priority")
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

//    private String formatCategories(Jobs job) {
//        if (job.getJobType() == null || job.getJobType().isEmpty()) {
//            return "";
//        }
//        return job.getJobType().stream()
//                .sorted(Comparator.comparing(Category::getId))
//                .map(Category::getjobType).collect(Collectors.joining(", "));
//    }
}
