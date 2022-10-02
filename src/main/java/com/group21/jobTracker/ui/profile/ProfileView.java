package com.group21.jobTracker.ui.profile;

import java.io.*;

import com.group21.jobTracker.backend.data.Jobs;
import com.group21.jobTracker.backend.data.User;
import com.group21.jobTracker.ui.MainLayout;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.Version;

@Route(value = "Profile", layout = MainLayout.class)
@PageTitle("My Profile")
public class ProfileView extends HorizontalLayout {
	private Button save;
	private final Binder<User> binder;
    public static final String VIEW_NAME = "My Profile";

    public ProfileView() {
    	setClassName("profile-form");
        // add(VaadinIcon.INFO_CIRCLE.create());
        // add(new Span(" This application is using Vaadin version "
        //         + Version.getFullVersion() + "."));
        // setSizeFull();
        // setJustifyContentMode(JustifyContentMode.CENTER);
        // setAlignItems(Alignment.CENTER);
        VerticalLayout profileLayout = new VerticalLayout();
        String[] targetvalue = null;
        try (BufferedReader br = new BufferedReader(new FileReader("./data/candidate_data.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if(values[0].equals(MainLayout.candidateName)) targetvalue = values;
            }
        } catch (Exception e) {
            // TODO: handle exception
            Notification.show(e.getMessage());
            Notification.show("Error reading account data");
        }

        TextField nameField = new TextField();
        nameField.setLabel("Full Name");

        EmailField emailField = new EmailField();
        emailField.setLabel("Email address");

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

        if(targetvalue != null &&targetvalue.length == 5){
            nameField.setValue(targetvalue[0]);
            emailField.setValue(targetvalue[1]);
            ageField.setValue(targetvalue[2]);
            experienceField.setValue(Double.valueOf(targetvalue[3]));
            keywordField.setValue(targetvalue[4]);
        } else {
            Notification.show("There are errors with the retieved data");
        }

        add(profileLayout);
        
        
        binder = new BeanValidationBinder<>(User.class);
        
        
//         save = new Button("Save");
//         save.setWidth("12em");
//         save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
//         save.addClickListener(event -> {
//         	//System.out.println("length of checkbox group: "+category.getValue());
//         	User user = new User(firstNameField.getValue(),lastNameField.getValue(),
//         			emailField.getValue());
//         	user.setGender(radioGroup.getValue());
//         	binder.forField(firstNameField).bind(User::getFirstName,User::setFirstName);
//             binder.forField(lastNameField).bind(User::getLastName,User::setLastName);
//             binder.forField(emailField).bind(User::getEmailAddress,User::setEmailAddress);
//             binder.forField(radioGroup).bind(User::getGender,User::setGender);
//             binder.bindInstanceFields(this);
//             binder.readBean(user);
// //        	System.out.println("TextField on the text field: "+jobTitle.getValue());
// //            System.out.println("Company on the text field: "+jobCompany.getValue());
// //            System.out.println("Priority on the text field: "+jobPriority.getValue());
// //            if (user != null
// //                    // && binder.writeBeanIfValid(currentJob)
// //                ) {
// //                //viewLogic.saveProduct(currentJob);
// //            }
//         });
//         save.addClickShortcut(Key.KEY_S, KeyModifier.CONTROL);
        
        profileLayout.add(save);

    }
}
