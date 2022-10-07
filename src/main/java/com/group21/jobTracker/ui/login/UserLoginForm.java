package com.group21.jobTracker.ui.login;

import com.group21.jobTracker.backend.data.User;
import com.group21.jobTracker.csv.Csv;
import com.group21.jobTracker.ui.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;

/**
 * A form for editing a single product.
 */
public class UserLoginForm extends Div {
	/** Layout for content */
    private final VerticalLayout content;
    /** Text field for username */
    private final TextField UserName;
    /** Button to log in with */
    private Button login;
    /** Button to register with */
    private Button register;

    /**
     * Method to create Login Form
    */
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
            String processedName = UserName.getValue().replaceAll(" ", "_");
            try {
                User user = Csv.loadUser(processedName);
                MainLayout.userName = user.getFullName();
                MainLayout.email = user.getEmailAddress();
                getUI().get().navigate("");
            } catch (Exception e) {
                Notification.show(e.getMessage(), 3000, Position.TOP_CENTER);
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
