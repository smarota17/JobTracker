package com.group21.jobTracker.ui;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.group21.jobTracker.ui.application.ApplicationView;
import com.group21.jobTracker.ui.applicationSearch.ApplicationSearchView;
import com.group21.jobTracker.ui.jobBoard.JobBoardView;
import com.group21.jobTracker.ui.profile.ProfileView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.Lumo;

/**
 * The main layout. Contains the navigation menu.
 */
@SuppressWarnings("serial")
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/menu-buttons.css", themeFor = "vaadin-button")
//@CssImport(value = "./styles/vaadin-app-layout-styles.css", themeFor = "vaadin-app-layout")
public class MainLayout extends AppLayout {

    private final Button logoutButton;
    public static String userName;
    public static String email;

    public MainLayout() {
    	
        // menu toggle
        final DrawerToggle drawerToggle = new DrawerToggle();
        drawerToggle.addClassName("menu-toggle");
        addToNavbar(drawerToggle);

        // image, logo
        final HorizontalLayout top = new HorizontalLayout();
        top.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        top.setClassName("menu-header");

        // Note! Image resource url is resolved here as it is dependent on the
        // execution mode (development or production) and browser ES level
        // support
//        final String resolvedImage = VaadinService.getCurrent().resolveResource(
//                "img/table-logo.png");
//
//        final Image image = new Image(resolvedImage, "");
        final Label title = new Label("Job Tracker");
       // top.add(image, title);
        top.add(title);
        //Button darkMode = new Button("Dark Mode");
        
        Button darkMode = new Button("Dark Mode", click -> {
            ThemeList themeList = UI.getCurrent().getElement().getThemeList(); // (1)

            if (themeList.contains(Lumo.DARK)) { // (2)
              themeList.remove(Lumo.DARK);
              //darkMode.setText("Light Mode");
            } else {
              themeList.add(Lumo.DARK);
              //darkMode.setText("Dark Mode");
            }
          });
        darkMode.setClassName("dark-mode-button");
        
        top.add(darkMode);
        addToNavbar(top);
        
        // dashboard tab
        addToDrawer(createMenuLink(JobBoardView.class, JobBoardView.VIEW_NAME,
                VaadinIcon.BOOK.create()));

        // my applications tab
        addToDrawer(createMenuLink(ApplicationView.class, ApplicationView.VIEW_NAME,
                VaadinIcon.EDIT.create()));

        // application search tab
        addToDrawer(createMenuLink(ApplicationSearchView.class, ApplicationSearchView.VIEW_NAME,
                VaadinIcon.SEARCH.create()));
        
        // profile tab
        addToDrawer(createMenuLink(ProfileView.class, ProfileView.VIEW_NAME,
                VaadinIcon.USER.create()));
        
        // Create logout button but don't add it yet; admin view might be added
        // in between (see #onAttach())
        logoutButton = createMenuButton("Logout", VaadinIcon.SIGN_OUT.create());
        logoutButton.addClickListener(e -> logout());
        logoutButton.getElement().setAttribute("title", "Logout (Ctrl+L)");
        addToDrawer(logoutButton);

    }

    private void logout() {
        //logout logic
        MainLayout.userName = null;
        MainLayout.email = null;
        getUI().get().navigate("Login");
    }

    private RouterLink createMenuLink(Class<? extends Component> viewClass,
            String caption, Icon icon) {
        final RouterLink routerLink = new RouterLink((String)null, viewClass);
        routerLink.setClassName("menu-link");
        routerLink.add(icon);
        routerLink.add(new Span(caption));
        icon.setSize("24px");
        return routerLink;
    }

    private Button createMenuButton(String caption, Icon icon) {
        final Button routerButton = new Button(caption);
        routerButton.setClassName("menu-button");
        routerButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        routerButton.setIcon(icon);
        icon.setSize("24px");
        return routerButton;
    }

}
