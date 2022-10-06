package com.group21.jobTracker.ui.profile;

import java.io.*;

import com.group21.jobTracker.backend.data.Jobs;
import com.group21.jobTracker.backend.data.User;
import com.group21.jobTracker.csv.Csv;
import com.group21.jobTracker.ui.MainLayout;
import com.group21.jobTracker.ui.login.LoginScreen;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.Version;

@Route(value = "Profile", layout = MainLayout.class)
@PageTitle("My Profile")
public class ProfileView extends HorizontalLayout implements BeforeEnterObserver{
    public static final String VIEW_NAME = "My Profile";

    public ProfileView() {
    	setClassName("profile-form");
        VerticalLayout profileLayout = new VerticalLayout();

        TextField nameField = new TextField();
        nameField.setLabel("Full Name");

        EmailField emailField = new EmailField();
        emailField.setLabel("Email address");

        TextField genderField = new TextField();
        genderField.setLabel("Gender");

        TextField ageField = new TextField();
        ageField.setLabel("Age");


        NumberField experienceField = new NumberField();
        experienceField.setLabel("Year of Experience");
        experienceField.setValue(0.0);
        Div experienceSuffix = new Div();
        experienceSuffix.setText("years");
        experienceField.setWidth("12em");
        experienceField.setSuffixComponent(experienceSuffix);

        TextField keywordField = new TextField();
        keywordField.setLabel("Job Keywords");

        profileLayout.add(nameField, emailField, ageField, experienceField, keywordField);

        if(MainLayout.userName != null){
            try{
                User user = Csv.loadUser(MainLayout.userName.replace(" ", ""));
                try{
                    nameField.setValue(user.getFullName());
                    emailField.setValue(user.getEmailAddress());
                    genderField.setValue(user.getGender());
                    ageField.setValue(user.getAge());
                    experienceField.setValue(Double.valueOf(user.getExperience()));
                    keywordField.setValue(user.getKeywords());
                } catch (Exception e) {
                    Notification.show("There are errors with the retieved data");
                }
            } catch (Exception e) {
                // TODO: handle exception
                Notification.show(e.getMessage());
            }
        }

        add(profileLayout);
        

    }


    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if(MainLayout.userName == null){
            event.rerouteTo(LoginScreen.class);
            Notification.show("Please Login First!",3000, Position.TOP_CENTER);
        }
    }
}
