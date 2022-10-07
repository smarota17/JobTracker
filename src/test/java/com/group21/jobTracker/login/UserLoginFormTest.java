package com.group21.jobTracker.login;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.group21.jobTracker.backend.data.User;
import com.group21.jobTracker.csv.Csv;
import com.group21.jobTracker.ui.login.UserRegisterForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.testbench.NotificationElement;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;

class UserLoginFormTest extends Div{
    
    private VerticalLayout content;
    private TextField UserName = new TextField("User Name");
    private Button login = new Button("Login");
    private Button register;
    private User mockUser;
    private String msg;

    @BeforeEach
    public void init() {
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
            String processedName = UserName.getValue().replace(" ", "_");
            try {
                User user = Csv.loadUser(processedName);
                msg = user.getFullName();
            } catch (Exception e) {
                msg = e.getMessage();
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

        mockUser = new User("Test Name", "TestEmail", "Male", "12", "1.5", "SDE");
        Csv.saveUser(mockUser);
    }

   @Test
   void testValidUserName() {
       UserName.setValue("Test Name");
       login.click();
       assertEquals("Test Name", msg);
   }

   @Test
   void testInValidUserName() {
       UserName.setValue("Invalid Name");
       login.click();
       assertEquals("No saved data found", msg);
   }

}
