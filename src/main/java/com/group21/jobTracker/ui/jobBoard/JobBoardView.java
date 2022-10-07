package com.group21.jobTracker.ui.jobBoard;

import com.group21.jobTracker.ui.MainLayout;
import com.group21.jobTracker.ui.login.LoginScreen;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
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

	/** private static final parameter representing the name of the page view*/
    public static final String VIEW_NAME = "Dashboard";
    /** private final parameter representing the job grid from the page*/
    private final JobGrid grid;
    /** Represents the login for the dashboard */
    private final JobBoardViewLogic viewLogic = new JobBoardViewLogic(this);

    /** A Data provider instance for the Job board page which contain the CRUD functions as well as other filtering functions to feed data into the page*/
    private final JobDataProvider dataProvider = new JobDataProvider();

    /**
     * JobBoardView constructor to initialize all the instance 
     * like JobDataProvider, initiating the Job Grid
     * Job form in the Job Board page.
     *
     */
    public JobBoardView() {
        // Sets the width and the height of view to "100%".
        setSizeFull();
        HorizontalLayout helloLayout = new HorizontalLayout();
        helloLayout.add(new H1("Hello, " + MainLayout.userName + "!"));
                
        grid = new JobGrid();
        grid.setItems(dataProvider.getItems());
        
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
        barAndGridLayout.add(gridAndDeadlinesLayout);
        barAndGridLayout.setFlexGrow(1, gridAndDeadlinesLayout);
        barAndGridLayout.setSizeFull();
        add(barAndGridLayout);
    }

    @Override
    public void setParameter(BeforeEvent event,
            @OptionalParameter String parameter) {

        // Location location = event.getLocation();
        // QueryParameters queryParameters = location.getQueryParameters();

        // Map<String, List<String>> parametersMap = queryParameters.getParameters();
        // candidateName = parametersMap.get("candidateName").get(0);
        // helloLabel.setText("Hello, " + candidateName + "!");
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
            Notification.show("Please Login First!", 3000, Position.TOP_CENTER);
        }
    }
    
}
