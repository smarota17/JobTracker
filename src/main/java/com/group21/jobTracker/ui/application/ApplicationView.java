package com.group21.jobTracker.ui.application;

import java.text.ParseException;
import com.group21.jobTracker.backend.data.Jobs;
import com.group21.jobTracker.ui.MainLayout;
import com.group21.jobTracker.ui.login.LoginScreen;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * A view for performing create-read-update-delete operations on Jobs.
 *
 * See also {@link ApplicationViewLogic} for fetching the data, the actual CRUD
 * operations and controlling the view based on events from outside.
 */
@SuppressWarnings("serial")
@Route(value = "Application", layout = MainLayout.class)
@PageTitle("My Applications")
public class ApplicationView extends HorizontalLayout
        implements HasUrlParameter<String>, BeforeEnterObserver  {

	/** private static final parameter representing the name of the page view*/
    public static final String VIEW_NAME = "My Applications";
    /** private final parameter representing the application grid from the page*/
    private final ApplicationGrid grid;
    /** private final parameter representing the application form from the page*/
    private final ApplicationForm form;
    /** private Textfield to enter the keywords to filter the job search*/
    private TextField filter;

    /** private final ApplicationViewLogic instance to handle the CRUD operation for Application page*/
    private final ApplicationViewLogic viewLogic = new ApplicationViewLogic(this);
    /** A Button variable for adding new application*/
    private Button newApplication;

    /** A Data provider instance for the application page which contain the CRUG functions as well as other filtering fucntions to feed data into the page*/
    private final ApplicationDataProvider dataProvider;

    /**
     * ApplicationView constructor to initialize all the instance 
     * like ApplicationDataProvider, initiating the APplication Grid
     * Application form in the application page.
     *
     */
    public ApplicationView() {
        // Sets the width and the height of InventoryView to "100%".
    	dataProvider = new ApplicationDataProvider();
        setSizeFull();
        final HorizontalLayout topLayout = createTopBar();
        grid = new ApplicationGrid();
        grid.setItems(dataProvider.getItems());
        // Allows user to select a single row in the grid.
        grid.asSingleSelect().addValueChangeListener(
                event -> viewLogic.rowSelected(event.getValue()));
        form = new ApplicationForm(viewLogic);

        final VerticalLayout barAndGridLayout = new VerticalLayout();
        barAndGridLayout.add(topLayout);
        barAndGridLayout.add(grid);
        barAndGridLayout.setFlexGrow(1, grid);
        barAndGridLayout.setFlexGrow(0, topLayout);
        barAndGridLayout.setSizeFull();
        barAndGridLayout.expand(grid);

        add(barAndGridLayout);
        add(form);

    }
    
    /**
     * ApplicationView filtering and new  Application form will be assigned int he page UI.
     * Application form in the application page.
     * @return layout for filtering and new application
     **/
    public HorizontalLayout createTopBar() {
		filter = new TextField();
		filter.setPlaceholder("Filter by name");
		filter.setValueChangeMode(ValueChangeMode.LAZY);
		filter.addValueChangeListener(event -> grid.setItems( dataProvider.updateList(event.getValue())));

        newApplication = new Button("New application");
        // Setting theme variant of new production button to LUMO_PRIMARY that
        // changes its background color to blue and its text color to white
        newApplication.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        newApplication.setIcon(VaadinIcon.PLUS_CIRCLE.create());
        newApplication.addClickListener(click -> viewLogic.newJob());
        // A shortcut to click the new product button by pressing ALT + N
        newApplication.addClickShortcut(Key.KEY_N, KeyModifier.ALT);
        final HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setWidth("100%");
        topLayout.add(filter);
        topLayout.add(newApplication);
        topLayout.setVerticalComponentAlignment(Alignment.START, filter);
        topLayout.expand(filter);
        return topLayout;
    }

    /**
     * Showing error message using Notification.show from Vaadin
     * @see Notification#show(String)
     * @param msg
     **/
    public void showError(String msg) {
        Notification.show(msg);
    }

    /**
     * Shows a temporary popup notification to the user.
     * 
     * @see Notification#show(String)
     * @param msg to show
     */
    public void showNotification(String msg) {
        Notification.show(msg);
    }

    /**
     * Enables/Disables the new Jobs button.
     * 
     * @param enabled whether the new jobs button should appear or not
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
     * @param row that is selected
     */
    public void selectRow(Jobs row) {
        grid.getSelectionModel().select(row);
    }

    /**
     * Updates a Jobs in the list of jobs.
     * 
     * @param job Jobs that should be updated
     * @throws ParseException thrown when the CSV files cannot be loaded
     * @throws NumberFormatException thrown when the CSV files cannot be loaded
     */
    public void updateProduct(Jobs job) {
        dataProvider.save(job);
        UI.getCurrent().getPage().reload();
    }

    /**
     * Removes a Job from the list of Jobs.
     * 
     * @param job object to be removed
     */
    public void removeProduct(Jobs job) {
        dataProvider.delete(job);
        UI.getCurrent().getPage().reload();
    }

    /**
     * Displays user a form to edit a Job.
     * 
     * @param job object to be edited
     */
    public void editJob(Jobs job) {
        showForm(job != null);
        form.setJob(job);
        form.editJob(job);
        
    }

    /**
     * Shows and hides the new job form
     * 
     * @param show whether the form should be shown or hidden
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

    /**
     * Before-entering to the app one can access the job-tracker site as null user.
     * 
     * @param event a before-enter event
     */
    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if(MainLayout.userName == null){
            event.rerouteTo(LoginScreen.class);
        }
    }


    
}
