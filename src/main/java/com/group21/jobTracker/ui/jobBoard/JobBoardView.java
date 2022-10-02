package com.group21.jobTracker.ui.jobBoard;

import com.group21.jobTracker.backend.DataService;
import com.group21.jobTracker.backend.data.Jobs;
import com.group21.jobTracker.ui.MainLayout;
import com.group21.jobTracker.ui.application.ApplicationForm;
import com.group21.jobTracker.ui.application.ApplicationViewLogic;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.Theme;

/**
 * A view for performing create-read-update-delete operations on products.
 *
 * See also {@link JobBoardViewLogic} for fetching the data, the actual CRUD
 * operations and controlling the view based on events from outside.
 */
@Route(value = "Dashboard", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class JobBoardView extends HorizontalLayout
        implements HasUrlParameter<String> {

    public static final String VIEW_NAME = "Dashboard";
    
    private final JobGrid grid;
    private final JobApplicationForm form;
    private TextField filter;
    private final JobBoardViewLogic viewLogic = new JobBoardViewLogic(this);
    private Button newApplication;

    private final JobDataProvider dataProvider = new JobDataProvider();

    public JobBoardView() {
        // Sets the width and the height of InventoryView to "100%".
        setSizeFull();
        HorizontalLayout helloLayout = new HorizontalLayout();
        helloLayout.add( new H1("Hello, User!") );
        
        final HorizontalLayout topLayout = createTopBar();
        
        grid = new JobGrid();
        grid.setItems(dataProvider);
        // Allows user to select a single row in the grid.
        grid.asSingleSelect().addValueChangeListener(
                event -> viewLogic.rowSelected(event.getValue()));
        
        form = new JobApplicationForm(viewLogic);
        
        HorizontalLayout upcomingDeadlinesLayout = new HorizontalLayout();
        upcomingDeadlinesLayout.add( new H3("Upcoming Deadlines:") );
        upcomingDeadlinesLayout.setWidth("50%");
        
        HorizontalLayout gridAndDeadlinesLayout = new HorizontalLayout();
        gridAndDeadlinesLayout.add(grid);
        gridAndDeadlinesLayout.add(upcomingDeadlinesLayout);
        gridAndDeadlinesLayout.setFlexGrow(3, grid);
        gridAndDeadlinesLayout.setFlexGrow(1, upcomingDeadlinesLayout);
        gridAndDeadlinesLayout.expand(grid);
        gridAndDeadlinesLayout.setSizeFull();

        final VerticalLayout barAndGridLayout = new VerticalLayout();
        barAndGridLayout.add(helloLayout);
        barAndGridLayout.add(topLayout);
//        barAndGridLayout.add(grid);
        barAndGridLayout.add(gridAndDeadlinesLayout);
//        barAndGridLayout.setFlexGrow(1, grid);
        barAndGridLayout.setFlexGrow(0, topLayout);
        barAndGridLayout.setSizeFull();
//        barAndGridLayout.expand(grid);

       // add(helloLayout);
        add(barAndGridLayout);
        add(form);

        viewLogic.init();
    }

    public HorizontalLayout createTopBar() {
        filter = new TextField();
        filter.setPlaceholder("Filter name, availability or category");
        // Apply the filter to grid's data provider. TextField value is never
        filter.addValueChangeListener(
                event -> dataProvider.setFilter(event.getValue()));
        // A shortcut to focus on the textField by pressing ctrl + F
        filter.addFocusShortcut(Key.KEY_F, KeyModifier.CONTROL);

        final HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setWidth("100%");
        topLayout.add(filter);
        topLayout.setVerticalComponentAlignment(Alignment.START, filter);
        topLayout.expand(filter);
        return topLayout;
    }

    public void showError(String msg) {
        Notification.show(msg);
    }

    /**
     * Shows a temporary popup notification to the user.
     * 
     * @see Notification#show(String)
     * @param msg
     */
    public void showNotification(String msg) {
        Notification.show(msg);
    }

    /**
     * Enables/Disables the new product button.
     * 
     * @param enabled
     */
    public void setnewApplicationEnabled(boolean enabled) {
        newApplication.setEnabled(enabled);
    }

    /**
     * Deselects the selected row in the grid.
     */
    public void clearSelection() {
        grid.getSelectionModel().deselectAll();
    }

    /**
     * Selects a row
     * 
     * @param row
     */
    public void selectRow(Jobs row) {
        grid.getSelectionModel().select(row);
    }

    /**
     * Updates a product in the list of products.
     * 
     * @param product
     */
    public void updateProduct(Jobs job) {
        dataProvider.save(job);
    }

    /**
     * Removes a product from the list of products.
     * 
     * @param product
     */
    public void removeProduct(Jobs job) {
        dataProvider.delete(job);
    }

    /**
     * Displays user a form to edit a Product.
     * 
     * @param product
     */
    public void editJob(Jobs job) {
        showForm(job != null);
        //form.setCategories(job.getJobType());
        form.setJob(job);
        form.editJob(job);
    }

    /**
     * Shows and hides the new product form
     * 
     * @param show
     */
    public void showForm(boolean show) {
        form.setVisible(show);
        form.setEnabled(show);
    }

    @Override
    public void setParameter(BeforeEvent event,
            @OptionalParameter String parameter) {
        viewLogic.enter(parameter);
    }
}
