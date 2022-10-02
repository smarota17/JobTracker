package com.group21.jobTracker.ui.profile;

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

        TextField firstNameField = new TextField();
        firstNameField.setLabel("First Name");
        profileLayout.add(firstNameField);
        
        TextField lastNameField = new TextField();
        lastNameField.setLabel("First Name");
        profileLayout.add(lastNameField);

        EmailField emailField = new EmailField();
        emailField.setLabel("Email address");
        profileLayout.add(emailField);
        
        RadioButtonGroup<String> radioGroup = new RadioButtonGroup<>();
        radioGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        radioGroup.setLabel("Gender");
        radioGroup.setItems("Male", "Female", "Nonbinary");
        add(radioGroup);
        profileLayout.add(radioGroup);

        TextField ageField = new TextField();
        ageField.setLabel("Age");
        profileLayout.add(ageField);
        

        NumberField experienceField = new NumberField();
        experienceField.setLabel("Year of Experience");
        experienceField.setValue(0.0);
        Div experienceSuffix = new Div();
        experienceSuffix.setText("years");
        experienceField.setWidth("12em");
        experienceField.setSuffixComponent(experienceSuffix);
        profileLayout.add(experienceField);

        TextField keywordField = new TextField();
        keywordField.setLabel("Job Keywords");
        profileLayout.add(keywordField);
        
        add(profileLayout);
        
        
        binder = new BeanValidationBinder<>(User.class);
        
        
        save = new Button("Save");
        save.setWidth("12em");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.addClickListener(event -> {
        	//System.out.println("length of checkbox group: "+category.getValue());
        	User user = new User(firstNameField.getValue(),lastNameField.getValue(),
        			emailField.getValue());
        	user.setGender(radioGroup.getValue());
        	binder.forField(firstNameField).bind(User::getFirstName,User::setFirstName);
            binder.forField(lastNameField).bind(User::getLastName,User::setLastName);
            binder.forField(emailField).bind(User::getEmailAddress,User::setEmailAddress);
            binder.forField(radioGroup).bind(User::getGender,User::setGender);
            binder.bindInstanceFields(this);
            binder.readBean(user);
//        	System.out.println("TextField on the text field: "+jobTitle.getValue());
//            System.out.println("Company on the text field: "+jobCompany.getValue());
//            System.out.println("Priority on the text field: "+jobPriority.getValue());
//            if (user != null
//                    // && binder.writeBeanIfValid(currentJob)
//                ) {
//                //viewLogic.saveProduct(currentJob);
//            }
        });
        save.addClickShortcut(Key.KEY_S, KeyModifier.CONTROL);
        
        profileLayout.add(save);

    }
}
