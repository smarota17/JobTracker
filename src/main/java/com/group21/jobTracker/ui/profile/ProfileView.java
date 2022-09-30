package com.group21.jobTracker.ui.profile;

import com.group21.jobTracker.ui.MainLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.Version;

@Route(value = "Profile", layout = MainLayout.class)
@PageTitle("My Profile")
public class ProfileView extends HorizontalLayout {

    public static final String VIEW_NAME = "My Profile";

    public ProfileView() {
        // add(VaadinIcon.INFO_CIRCLE.create());
        // add(new Span(" This application is using Vaadin version "
        //         + Version.getFullVersion() + "."));
        // setSizeFull();
        // setJustifyContentMode(JustifyContentMode.CENTER);
        // setAlignItems(Alignment.CENTER);
        VerticalLayout profileLayout = new VerticalLayout();

        TextField nameField = new TextField();
        nameField.setLabel("Full Name");
        profileLayout.add(nameField);

        EmailField emailField = new EmailField();
        emailField.setLabel("Email address");
        profileLayout.add(emailField);

        TextField ageField = new TextField();
        ageField.setLabel("Age");
        profileLayout.add(ageField);

        NumberField experienceField = new NumberField();
        experienceField.setLabel("Year of Experience");
        experienceField.setValue(0.0);
        Div experienceSuffix = new Div();
        experienceSuffix.setText("years");
        experienceField.setSuffixComponent(experienceSuffix);
        profileLayout.add(experienceField);

        TextField keywordField = new TextField();
        keywordField.setLabel("Job Keywords");
        profileLayout.add(keywordField);

        add(profileLayout);

    }
}
