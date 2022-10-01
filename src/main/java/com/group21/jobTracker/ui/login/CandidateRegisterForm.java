package com.group21.jobTracker.ui.login;

import org.hibernate.query.QueryParameter;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
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
public class CandidateRegisterForm extends Div {

    private final VerticalLayout content;

    private final TextField fullName;
    private final EmailField email;
    private final TextField age;
    private final NumberField experience;
    private final TextField keywords;
    private final Button save;


    public CandidateRegisterForm() {
        setClassName("job-form");
        content = new VerticalLayout();
        content.setSizeUndefined();
        content.addClassName("job-form-content");
        add(content);

        fullName = new TextField();
        fullName.setLabel("Full Name");

        email = new EmailField();
        email.setLabel("Email address");

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

        save = new Button("Create candidate");
        save.addClickListener(event-> newCandidate());

        content.add(fullName, email, age, experience, keywords, save);
    }

    public void newCandidate() {
        String name = fullName.getValue();
        QueryParameters parameter = QueryParameters.of("candidateName", name);
        getUI().get().navigate("", parameter);
    }

}
