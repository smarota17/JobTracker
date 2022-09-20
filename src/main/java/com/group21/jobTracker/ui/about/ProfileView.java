package com.group21.jobTracker.ui.about;

import com.group21.jobTracker.ui.MainLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.Version;

@Route(value = "Profile", layout = MainLayout.class)
@PageTitle("My Profile")
public class ProfileView extends HorizontalLayout {

    public static final String VIEW_NAME = "My Profile";

    public ProfileView() {
        add(VaadinIcon.INFO_CIRCLE.create());
        add(new Span(" This application is using Vaadin version "
                + Version.getFullVersion() + "."));
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

    }
}
