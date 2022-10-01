package com.group21.jobTracker.ui.login;


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
            if (true) {
                String name = candidateName.getValue();
                QueryParameters parameter = QueryParameters.of("candidateName", name);
                getUI().get().navigate("", parameter);
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
