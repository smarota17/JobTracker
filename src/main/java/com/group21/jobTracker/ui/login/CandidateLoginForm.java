package com.group21.jobTracker.ui.login;


import java.io.*;
import java.util.*;

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
public class CandidateLoginForm extends Div {

    private final VerticalLayout content;

    private final TextField candidateName;
    private Button login;
    private Button register;


    public CandidateLoginForm() {
        

        content = new VerticalLayout();
        content.setSizeUndefined();
        content.addClassName("job-form-content");
        add(content);
        
        setClassName("candidateLogin-form");
        H1 label = new H1("Welcome, candidate!");
        label.setClassName("form-header");
        content.add(label);

        candidateName = new TextField("Candidate Name");
        candidateName.setWidth("100%");
        candidateName.setRequired(true);
        candidateName.setValueChangeMode(ValueChangeMode.EAGER);
        
        content.add(candidateName);
        
        
        
        login = new Button("Login");
        login.setWidth("100%");
        login.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        login.addClickListener(event -> {
            boolean hasName = false;
            String name = candidateName.getValue();
            try (BufferedReader br = new BufferedReader(new FileReader("./data/candidate_data.csv"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    if(values[0].equals(name)) hasName = true;
                }
            } catch (Exception e) {
                // TODO: handle exception
                Notification.show(e.getMessage());
                Notification.show("Error reading account data");
            }

            if (hasName) {
                MainLayout.candidateName = name;
                getUI().get().navigate("");
            } else {
                Notification.show("The Candidate does not exist", 3000, Position.TOP_CENTER);
            }
        });

        content.add(login);

        Dialog dialog = new Dialog();

        dialog.setHeaderTitle("New Candidate");

        dialog.add(new CandidateRegisterForm());
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
