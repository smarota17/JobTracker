package com.group21.jobTracker.ui.applicationSearch;


import com.group21.jobTracker.backend.data.Jobs;
import com.group21.jobTracker.ui.MainLayout;
import com.group21.jobTracker.ui.login.LoginScreen;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * Represents the UI for the "Application Search" page, incorporates functionality for "CRUD" 
 * operations as well. 
 */
@SuppressWarnings("serial")
@Route(value = "ApplicationSearch", layout = MainLayout.class)
@PageTitle("Application Search")
public class ApplicationSearchView extends HorizontalLayout
        implements HasUrlParameter<String>, BeforeEnterObserver {

	/** Represents the name of the page */
    public static final String VIEW_NAME = "Application Search";
    /** Represents the Vaadin grid (really a table) that is populated with Jobs objects from API calls */
    private final ApplicationSearchGrid grid;
    /** Represents the form to add applications to the database */
    private final ApplicationSearchForm form;
    /** Represents the logic for this page */
    private final ApplicationSearchViewLogic viewLogic = new ApplicationSearchViewLogic(this);
    /** Field that represents the string for searches */
    private TextField searchInput;
    /** Button to search for more applications */
    private Button searchApplications;
    /** Represents the data provider for the page, with no keywords used*/
    private  ApplicationSearchDataProvider dataProvider = new ApplicationSearchDataProvider(null);

    /**
     * Constructor for the ApplicationSearchView object
     */
    public ApplicationSearchView() {
        // Sets the width and the height of InventoryView to "100%".
        setSizeFull();
        final HorizontalLayout topLayout = createTopBar();
        topLayout.setAlignItems(Alignment.CENTER);

        grid = new ApplicationSearchGrid();
        grid.setItems(dataProvider.getItems());
        // Allows user to select a single row in the grid.
        grid.asSingleSelect().addValueChangeListener(
                event -> viewLogic.rowSelected(event.getValue()));
        // Allows user to select a single row in the grid.
//        grid.asSingleSelect().addValueChangeListener(
//                event -> viewLogic.rowSelected(event.getValue()));
        form = new ApplicationSearchForm(viewLogic);

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
     * Method to create the top bar on the UI (Search bar and button to search for new apps)
     * @return a HorizontalLayout container
     */
    public HorizontalLayout createTopBar() {
        searchInput = new TextField();
        searchInput.setPlaceholder("Input search keywords");
        searchApplications = new Button("Search for more applications");
        // Setting theme variant of new production button to LUMO_PRIMARY that
        // changes its background color to blue and its text color to white
        searchApplications.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        searchApplications.setIcon(VaadinIcon.SEARCH.create());
//        newApplication.addClickListener(click -> viewLogic.newJob());
        // A shortcut to click the new product button by pressing ALT + N
        searchApplications.addClickShortcut(Key.KEY_N, KeyModifier.ALT);
        searchApplications.addClickListener(event -> startSearching());
        final HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setWidth("100%");
        topLayout.setAlignItems(Alignment.CENTER);
        topLayout.add(searchInput, searchApplications);

        return topLayout;
    }

    /**
     * Method to start searching for job applications given an input from the search field.
     */
    private void startSearching(){
        dataProvider = new ApplicationSearchDataProvider(searchInput.getValue());
        grid.setItems(dataProvider.getItems());
    }

    /**
     * Method to show error messages.
     * @param msg error message
     */
    public void showError(String msg) {
        Notification.show(msg);
    }

    /**
     * Shows a temporary popup notification to the user.
     * 
     * @see Notification#show(String)
     * @param msg message to display
     */
    public void showNotification(String msg) {
        Notification.show(msg);
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
     * @param row given row to select in the grid
     */
    public void selectRow(Jobs row) {
        grid.getSelectionModel().select(row);
    }

    /**
     * Updates a job in the list of products.
     * 
     * @param job to be updated
     */
    public void updateProduct(Jobs job) {
        dataProvider.save(job);
    }


    /**
     * Displays user a form to edit a job.
     * 
     * @param job to be edited
     */
    public void editJob(Jobs job) {
        showForm(job != null);
        form.setJob(job);
        form.editJob(job);
    }

    /**
     * Shows and hides the new product form
     * 
     * @param show whether the form should be shown or not
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

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if(MainLayout.userName == null){
            event.rerouteTo(LoginScreen.class);
            Notification.show("Please Login First!", 3000, Position.TOP_CENTER);
        }
    }
}
