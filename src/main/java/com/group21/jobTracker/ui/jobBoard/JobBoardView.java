package com.group21.jobTracker.ui.jobBoard;

import com.group21.jobTracker.backend.DataService;
import com.group21.jobTracker.backend.data.Jobs;
import com.group21.jobTracker.ui.MainLayout;
import com.group21.jobTracker.ui.login.LoginScreen;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Header;
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
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

/**
 * The JobBoardView class is used to generate the frontend page for the "Dashboard" tab on the UI. 
 * It generates several Horizontal and Vertical layouts to format the contents of the page.
 */
@Route(value = "Dashboard", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class JobBoardView extends HorizontalLayout
        implements HasUrlParameter<String>, BeforeEnterObserver {

	/** Name of the page */
    public static final String VIEW_NAME = "Dashboard";
    /** Grid object to display jobs */
    private final JobGrid grid;
    /** Form to create a Job object */
//    private final JobApplicationForm form;
    /** Represents the "filter" search box */
    private TextField filter;
    /** Represents the login for the dashboard */
    private final JobBoardViewLogic viewLogic = new JobBoardViewLogic(this);

    private final JobDataProvider dataProvider = new JobDataProvider();

    public JobBoardView() {
        // Sets the width and the height of InventoryView to "100%".
        setSizeFull();
        HorizontalLayout helloLayout = new HorizontalLayout();
        helloLayout.add(new H1("Hello, "+ MainLayout.userName + "!"));
        
        final HorizontalLayout topLayout = createTopBar();
        
        grid = new JobGrid();
        grid.setItems(dataProvider.getItems());
//        // Allows user to select a single row in the grid.
//        grid.asSingleSelect().addValueChangeListener(
//                event -> viewLogic.rowSelected(event.getValue()));
        
//        form = new JobApplicationForm(viewLogic);
        
        HorizontalLayout upcomingDeadlinesHeader = new HorizontalLayout();
        upcomingDeadlinesHeader.add( new H3("Upcoming Deadlines:") );
        upcomingDeadlinesHeader.setWidth("50%");
        
        VerticalLayout upcomingDeadlinesLayout = new VerticalLayout();
        Button reminderEmail = new Button("Send reminder email");
        // Setting theme variant of new production button to LUMO_PRIMARY that
        // changes its background color to blue and its text color to white
        reminderEmail.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        reminderEmail.setIcon(VaadinIcon.PLUS_CIRCLE.create());
        reminderEmail.addClickListener(click -> viewLogic.sendEmail());
        upcomingDeadlinesLayout.add(upcomingDeadlinesHeader);
        upcomingDeadlinesLayout.add(reminderEmail);
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
//        add(form);

//        viewLogic.init();
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

//    public void showError(String msg) {
//        Notification.show(msg);
//    }
//
//    /**
//     * Shows a temporary popup notification to the user.
//     * 
//     * @see Notification#show(String)
//     * @param msg
//     */
//    public void showNotification(String msg) {
//        Notification.show(msg);
//    }
//
//    /**
//     * Deselects the selected row in the grid.
//     */
//    public void clearSelection() {
//        grid.getSelectionModel().deselectAll();
//    }
//
//    /**
//     * Selects a row
//     * 
//     * @param row
//     */
//    public void selectRow(Jobs row) {
//        grid.getSelectionModel().select(row);
//    }
//
//    /**
//     * Updates a product in the list of products.
//     * 
//     * @param product
//     */
//    public void updateProduct(Jobs job) {
//        dataProvider.save(job);
//    }
//
//    /**
//     * Removes a product from the list of products.
//     * 
//     * @param product
//     */
//    public void removeProduct(Jobs job) {
//        dataProvider.delete(job);
//    }
//
//    /**
//     * Displays user a form to edit a Product.
//     * 
//     * @param product
//     */
//    public void editJob(Jobs job) {
//        showForm(job != null);
//        //form.setCategories(job.getJobType());
//        form.setJob(job);
//        form.editJob(job);
//    }

//    /**
//     * Shows and hides the new product form
//     * 
//     * @param show
//     */
//    public void showForm(boolean show) {
//        form.setVisible(show);
//        form.setEnabled(show);
//    }

    @Override
    public void setParameter(BeforeEvent event,
            @OptionalParameter String parameter) {

        // Location location = event.getLocation();
        // QueryParameters queryParameters = location.getQueryParameters();

        // Map<String, List<String>> parametersMap = queryParameters.getParameters();
        // candidateName = parametersMap.get("candidateName").get(0);
        // helloLabel.setText("Hello, " + candidateName + "!");
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        System.out.println("beforeEnter");
        // TODO Auto-generated method stub
        if(MainLayout.userName == null){
            event.rerouteTo(LoginScreen.class);
            Notification.show("Please Login First!",3000, Position.TOP_CENTER);
        }
    }
    
}
