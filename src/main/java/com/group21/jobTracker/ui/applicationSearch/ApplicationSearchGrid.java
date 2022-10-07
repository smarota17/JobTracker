package com.group21.jobTracker.ui.applicationSearch;

import com.group21.jobTracker.backend.data.Jobs;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

/**
 * Grid of products, handling the visual presentation and filtering of a set of
 * items. This version uses an in-memory data source that is suitable for small
 * data sets.
 */
@SuppressWarnings("serial")
public class ApplicationSearchGrid extends Grid<Jobs> {

    public ApplicationSearchGrid() {

        setSizeFull();

        addColumn(Jobs::getJobTitle).setHeader("Job Title").setResizable(true)
                .setFlexGrow(20).setSortable(true).setKey("jobTitle");

        addColumn(Jobs::getCompany).setHeader("Company").setResizable(true)
                .setFlexGrow(20).setSortable(true).setKey("jobCompany");

        addColumn(Jobs::getNextAction).setHeader("Job Description").setResizable(true)
                .setFlexGrow(20).setSortable(true).setKey("jobDescription");
        addComponentColumn(job -> {
                    HorizontalLayout layout = new HorizontalLayout();
                    Button applyButton = new Button("Go To Job");
                    applyButton.addClickListener(e -> {
                        UI.getCurrent().getPage().open(job.getLink());
                    });
                    H5 applyhere = new H5("Add Application");
                    applyhere.setClassName("apply-here-button");
                    layout.add(applyButton, applyhere);
                    return layout;
                }).setHeader("Operations").setResizable(true).setFlexGrow(20).setKey("jobOperation");
        // If the browser window size changes, check if all columns fit on
        // screen
        // (e.g. switching from portrait to landscape mode)
        UI.getCurrent().getPage().addBrowserWindowResizeListener(
                e -> setColumnVisibility(e.getWidth()));
    }

    private void setColumnVisibility(int width) {
        getColumnByKey("jobTitle").setVisible(true);
        getColumnByKey("jobCompany").setVisible(true);
        getColumnByKey("jobDescription").setVisible(true);
        getColumnByKey("jobOperation").setVisible(true);
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
