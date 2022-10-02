package com.group21.jobTracker.ui.login;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hibernate.query.QueryParameter;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
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
        List<String[]> newCandidateAttributes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("./data/candidate_data.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                newCandidateAttributes.add(values);
                if(values[0].equals(name)){
                    Notification.show("Account already exists!");
                    return;
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            Notification.show("Error reading account data");
        }
        
        newCandidateAttributes.add(new String[]{
            name,
            email.getValue(),
            age.getValue(),
            experience.getValue().toString(),
            keywords.getValue()
        });

        this.wirteData(newCandidateAttributes);
        Notification.show("Registeration success! Please go back and log in.");
    }

    private void wirteData(List<String[]> newCandidateAttributes){
        File csvOutputFile = new File("./data/candidate_data.csv");
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            newCandidateAttributes.stream()
            .map(this::convertToCSV)
            .forEach(pw::println);
        } catch (Exception e) {
            Notification.show(e.getMessage());
        }
    }

    public String convertToCSV(String[] data) {
        return Stream.of(data)
          .map(this::escapeSpecialCharacters)
          .collect(Collectors.joining(","));
    }

    public String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }

}
