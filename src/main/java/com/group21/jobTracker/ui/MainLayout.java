package com.group21.jobTracker.ui;

import com.group21.jobTracker.authentication.AccessControl;
import com.group21.jobTracker.authentication.AccessControlFactory;
import com.group21.jobTracker.ui.application.ApplicationView;
import com.group21.jobTracker.ui.jobBoard.JobBoardView;
import com.group21.jobTracker.ui.profile.ProfileView;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

/**
 * The main layout. Contains the navigation menu.
 */
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/menu-buttons.css", themeFor = "vaadin-button")
public class MainLayout extends AppLayout implements RouterLayout {

    private final Button logoutButton;

    public MainLayout() {

        // Header of the menu (the navbar)

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
        final String resolvedImage = VaadinService.getCurrent().resolveResource(
                "img/table-logo.png");

        final Image image = new Image(resolvedImage, "");
        final Label title = new Label("Job Tracker");
        top.add(image, title);
        top.add(title);
        
        Button darkMode = new Button("Dark Mode", click -> {
            ThemeList themeList = UI.getCurrent().getElement().getThemeList(); // (1)

            if (themeList.contains(Lumo.DARK)) { // (2)
              themeList.remove(Lumo.DARK);
            } else {
              themeList.add(Lumo.DARK);
            }
          });
        
        top.add(darkMode);
        addToNavbar(top);

        addToDrawer(createMenuLink(ProfileView.class, ProfileView.VIEW_NAME,
                VaadinIcon.DOCTOR.create()));
        
        // Navigation items
        addToDrawer(createMenuLink(JobBoardView.class, JobBoardView.VIEW_NAME,
                VaadinIcon.BOOK.create()));

        addToDrawer(createMenuLink(ApplicationView.class, ApplicationView.VIEW_NAME,
                VaadinIcon.EDIT.create()));

        // Create logout button but don't add it yet; admin view might be added
        // in between (see #onAttach())
        logoutButton = createMenuButton("Logout", VaadinIcon.SIGN_OUT.create());
        logoutButton.addClickListener(e -> logout());
        logoutButton.getElement().setAttribute("title", "Logout (Ctrl+L)");

    }

    private void logout() {
        AccessControlFactory.getInstance().createAccessControl().signOut();
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

    private void registerAdminViewIfApplicable(AccessControl accessControl) {
        // register the admin view dynamically only for any admin user logged in
        if (accessControl.isUserInRole(AccessControl.ADMIN_ROLE_NAME)
                && !RouteConfiguration.forSessionScope()
                        .isRouteRegistered(AdminView.class)) {
            RouteConfiguration.forSessionScope().setRoute(AdminView.VIEW_NAME,
                    AdminView.class, MainLayout.class);
            // as logout will purge the session route registry, no need to
            // unregister the view on logout
        }
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);

        // User can quickly activate logout with Ctrl+L
        attachEvent.getUI().addShortcutListener(() -> logout(), Key.KEY_L,
                KeyModifier.CONTROL);

        // add the admin view menu item if user has admin role
        final AccessControl accessControl = AccessControlFactory.getInstance()
                .createAccessControl();
        if (accessControl.isUserInRole(AccessControl.ADMIN_ROLE_NAME)) {

            // Create extra navigation target for admins
            registerAdminViewIfApplicable(accessControl);

            // The link can only be created now, because the RouterLink checks
            // that the target is valid.
            addToDrawer(createMenuLink(AdminView.class, AdminView.VIEW_NAME,
                    VaadinIcon.INFO_CIRCLE_O.create()));
        }

        // Finally, add logout button for all users
        addToDrawer(logoutButton);
    }

}
