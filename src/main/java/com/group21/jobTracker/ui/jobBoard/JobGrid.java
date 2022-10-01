package com.group21.jobTracker.ui.jobBoard;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.stream.Collectors;

import com.group21.jobTracker.backend.data.Category;
import com.group21.jobTracker.backend.data.Jobs;
import com.group21.jobTracker.backend.data.Product;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.function.ValueProvider;

/**
 * Grid of products, handling the visual presentation and filtering of a set of
 * items. This version uses an in-memory data source that is suitable for small
 * data sets.
 */
public class JobGrid extends Grid<Jobs> {

    public JobGrid() {

    	System.out.println("inside the job grid:");
        setSizeFull();

        addColumn(Jobs::getJobTitle).setHeader("Job Title")
                .setFlexGrow(20).setSortable(true).setKey("jobtitle");
                
        addColumn(Jobs::getCompany).setHeader("Company")
                .setFlexGrow(20).setSortable(true).setKey("jobCompany");

        // Show all categories the product is in, separated by commas
        addColumn(this::formatCategories).setHeader("Category").setFlexGrow(12)
                .setKey("category");
        addComponentColumn(job -> {
            Button editButton = new Button("Edit");
            editButton.addClickListener(e -> {
                if (getEditor().isOpen())
                	getEditor().cancel();
                	getEditor().editItem(job);
            });
            return editButton;
        }).setWidth("150px").setFlexGrow(0);

        // If the browser window size changes, check if all columns fit on
        // screen
        // (e.g. switching from portrait to landscape mode)
        UI.getCurrent().getPage().addBrowserWindowResizeListener(
                e -> setColumnVisibility(e.getWidth()));
    }


	private void setColumnVisibility(int width) {
        if (width > 800) {
            getColumnByKey("jobtitle").setVisible(true);
//            getColumnByKey("jobname").setVisible(true);
            getColumnByKey("jobCompany").setVisible(true);
            getColumnByKey("category").setVisible(true);
        } else if (width > 550) {
            getColumnByKey("jobtitle").setVisible(true);
//            getColumnByKey("jobname").setVisible(true);
            getColumnByKey("jobCompany").setVisible(false);
            getColumnByKey("category").setVisible(false);
        } else {
            getColumnByKey("jobtitle").setVisible(true);
//            getColumnByKey("jobname").setVisible(true);
            getColumnByKey("jobCompany").setVisible(false);
            getColumnByKey("category").setVisible(false);
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

    private String formatCategories(Jobs job) {
        if (job.getJobType() == null || job.getJobType().isEmpty()) {
            return "";
        }
        return job.getJobType().stream()
                .sorted(Comparator.comparing(Category::getId))
                .map(Category::getjobType).collect(Collectors.joining(", "));
    }
}
