package com.group21.jobTracker.ui.login;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hibernate.query.QueryParameter;

import com.group21.jobTracker.backend.data.User;
import com.group21.jobTracker.csv.Csv;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.QueryParameters;

/**
 * A form for editing a single product.
 */
public class UserRegisterForm extends Div {

    private final VerticalLayout content;

    private final TextField fullName;
    private final EmailField email;
    private final TextField gender;
    private final TextField age;
    private final NumberField experience;
    private final TextField keywords;
    private final Button save;

    /**
     * Method to create Registration Form
    */
    public UserRegisterForm() {
        setClassName("job-form");
        content = new VerticalLayout();
        content.setSizeUndefined();
        content.addClassName("job-form-content");
        add(content);

        fullName = new TextField();
        fullName.setLabel("Full Name");

        email = new EmailField();
        email.setLabel("Email address");

        gender = new TextField();
        gender.setLabel("Gender");

        age = new TextField();
        age.setLabel("Age");

        experience = new NumberField();
        experience.setLabel("Year of Experience");
        experience.setValue(0.0);
        Div experienceSuffix = new Div();
        experienceSuffix.setText("years");
        experience.setSuffixComponent(experienceSuffix);

        keywords = new TextField();
        keywords.setLabel("Job Keywords");

        save = new Button("Create User");
        save.addClickListener(event-> newUser());

        content.add(fullName, email, age, experience, keywords, save);
    }
    /**
     * Method to create new User
    */
    public void newUser() {
        User newUser = new User(
            fullName.getValue(),
            email.getValue(),
            gender.getValue(),
            age.getValue(),
            experience.getValue().toString(),
            keywords.getValue()
        );
        String processedName = fullName.getValue().replace(" ", "");
        File file = new File("data/" + processedName + ".csv");
        if(!file.exists()){
            Csv.saveUser(newUser);
            Notification.show("Registeration success! Please go back and log in.", 3000, Position.TOP_CENTER);
        } else {
            Notification.show("Registeration failed! User already exists.", 3000, Position.TOP_CENTER);
        }
    }

}
