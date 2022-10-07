package com.group21.jobTracker.login;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import org.junit.jupiter.api.BeforeEach;

import com.group21.jobTracker.backend.data.User;
import com.group21.jobTracker.csv.Csv;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;

import org.junit.jupiter.api.Test;
/**
 * Class to test the user register functionality. 
 */
class UserRegisterFormTest extends Div {
	/** Represents layout for content */
    private VerticalLayout content;
    /** Full name field */
    private TextField fullName;
    /** Email field */
    private EmailField email;
    /** Gender field */
    private TextField gender;
    /** Age field */
    private TextField age;
    /** Experience field */
    private NumberField experience;
    /** Keywords field */
    private TextField keywords;
    /** Save button */
    private Button save;
    /** Message string */
    private String msg;


    @BeforeEach
    public void init(){
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
        save.addClickListener(event -> newUser());

        content.add(fullName, email, age, experience, keywords, save);
    }

    public void newUser() {
        User newUser = new User(
            fullName.getValue(),
            email.getValue(),
            gender.getValue(),
            age.getValue(),
            experience.getValue().toString(),
            keywords.getValue()
        );
        String processedName = fullName.getValue().replace(" ", "_");
        File file = new File("data/" + processedName + ".csv");
        if(!file.exists()){
            Csv.saveUser(newUser);
        } else {
            msg = "Registeration failed! User already exists.";
        }
    }

    @Test
    void testRegisteration() {
       
        fullName.setValue("Test Test");
        email.setValue("Test Email");
        gender.setValue("Male");
        age.setValue("12");
        experience.setValue(1.5);
        keywords.setValue("Test Key Word");

        File generated = new File("data/" + fullName.getValue().replace(" ", "_") + ".csv");
        if(generated.exists()){
            generated.delete();
        }
        
        save.click();
        assertTrue(generated.exists());
        assertEquals(null, msg);
        generated.delete();
    }

    @Test
    void testDuplicate() {
        fullName.setValue("Test Duplicate");
        email.setValue("Test Email");
        gender.setValue("Male");
        age.setValue("12");
        experience.setValue(1.5);
        keywords.setValue("Test Key Word");
        
        File generated = new File("data/" + fullName.getValue().replace(" ", "_") + ".csv");
        if(generated.exists()){
            generated.delete();
        }

        save.click();
        save.click();
       
        assertTrue(generated.exists());
        assertEquals("Registeration failed! User already exists.", msg);
        generated.delete();
    }
}
