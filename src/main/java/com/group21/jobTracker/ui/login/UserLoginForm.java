package com.group21.jobTracker.ui.login;


import java.io.*;
import java.util.*;

import com.group21.jobTracker.backend.data.User;
import com.group21.jobTracker.csv.Csv;
import com.group21.jobTracker.ui.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.QueryParameters;

/**
 * A form for editing a single product.
 */
public class UserLoginForm extends Div {

    private final VerticalLayout content;

    private final TextField UserName;
    private Button login;
    private Button register;


    public UserLoginForm() {
        

        content = new VerticalLayout();
        content.setSizeUndefined();
        content.addClassName("job-form-content");
        add(content);
        
        setClassName("UserLogin-form");
        H1 label = new H1("Welcome, User!");
        label.setClassName("form-header");
        content.add(label);

        UserName = new TextField("User Name");
        UserName.setWidth("100%");
        UserName.setRequired(true);
        UserName.setValueChangeMode(ValueChangeMode.EAGER);
        
        content.add(UserName);
        
        
        
        login = new Button("Login");
        login.setWidth("100%");
        login.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        login.addClickListener(event -> {
            String processedName = UserName.getValue().replace(" ", "");
            try {
                User user = Csv.loadUser(processedName);
                MainLayout.userName = user.getFullName();
                getUI().get().navigate("");
            } catch (Exception e) {
                // TODO: handle exception
                Notification.show(e.getMessage(),3000, Position.TOP_CENTER);
            }
        });

        content.add(login);

        Dialog dialog = new Dialog();

        dialog.setHeaderTitle("New User");

        dialog.add(new UserRegisterForm());
        Button cancelButton = new Button("Cancel", e -> dialog.close());
        dialog.getFooter().add(cancelButton);

        register = new Button("Don't have an account? Register now!");
        register.setClassName("register-label");
        register.addClickListener(event -> {
             dialog.open();
        });

        content.add(register);



    }

}
