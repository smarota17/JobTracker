package com.group21.jobTracker.ui.application;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Locale;

import com.group21.jobTracker.backend.data.Availability;
import com.group21.jobTracker.backend.data.Category;
import com.group21.jobTracker.backend.data.Jobs;
import com.group21.jobTracker.ui.application.ApplicationViewLogic;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToBigDecimalConverter;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.data.value.ValueChangeMode;

/**
 * A form for editing a single product.
 */
public class ApplicationForm extends Div {

    private final VerticalLayout content;

    private final TextField jobTitle;
    private final TextField jobCompany;
    private final TextField jobPriority;
    private final CheckboxGroup<Category> category;
    private Button save;
    private Button discard;
    private Button cancel;
    private final Button delete;

    private final ApplicationViewLogic viewLogic;
    // private final Binder<Jobs> binder;
    private Jobs currentJob;


    public ApplicationForm(ApplicationViewLogic sampleCrudLogic) {
        setClassName("job-form");
        System.out.println("inside application form: ");
        content = new VerticalLayout();
        content.setSizeUndefined();
        content.addClassName("job-form-content");
        add(content);

        viewLogic = sampleCrudLogic;

        jobTitle = new TextField("Job Title");
        jobTitle.setWidth("100%");
        jobTitle.setRequired(true);
        jobTitle.setValueChangeMode(ValueChangeMode.EAGER);
//        jobTitle.addValueChangeListener(event -> {
//        	String val = event.getValue();      
//        	System.out.println(event.getValue());
//        });
        
        content.add(jobTitle);
        
        jobCompany = new TextField("Company");
        jobCompany.setWidth("100%");
        jobCompany.setRequired(true);
        jobCompany.setValueChangeMode(ValueChangeMode.EAGER);
//        jobCompany.addValueChangeListener(event -> {
//        	String val = event.getValue(); 
//        	System.out.println(event.getValue());
//        });
        
        content.add(jobCompany);
        

        jobPriority = new TextField("Priority");
        jobPriority.setWidth("100%");
        jobPriority.setRequired(true);
        jobPriority.setValueChangeMode(ValueChangeMode.EAGER);
//        jobPriority.addValueChangeListener(event -> {
//        	String val = event.getValue();         
//        	System.out.println(event.getValue());
//        });
        
        content.add(jobPriority);
        


        category = new CheckboxGroup<>();
        category.setLabel("Categories");
        category.setId("category");
        category.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        content.add(category);

        // binder = new BeanValidationBinder<>(Jobs.class);
        // binder.forField(price).withConverter(new PriceConverter())
        //         .bind("price");
        // binder.forField(stockCount).withConverter(new StockCountConverter())
        //         .bind("stockCount");
        // binder.bindInstanceFields(this);

        // // enable/disable save button while editing
        // binder.addStatusChangeListener(event -> {
        //     final boolean isValid = !event.hasValidationErrors();
        //     final boolean hasChanges = binder.hasChanges();
        //     save.setEnabled(hasChanges && isValid);
        //     discard.setEnabled(hasChanges);
        // });
        
        save = new Button("Save");
        save.setWidth("100%");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.addClickListener(event -> {
        	//System.out.println("length of checkbox group: "+category.getValue());
        	currentJob = new Jobs();
        	currentJob.setJobTitle(jobTitle.getValue());
        	currentJob.setCompany(jobCompany.getValue());
        	currentJob.setPriority(jobPriority.getValue());
        	currentJob.setJobType(category.getValue());
//        	System.out.println("TextField on the text field: "+jobTitle.getValue());
//            System.out.println("Company on the text field: "+jobCompany.getValue());
//            System.out.println("Priority on the text field: "+jobPriority.getValue());
            if (currentJob != null
                    // && binder.writeBeanIfValid(currentJob)
                ) {
                viewLogic.saveProduct(currentJob);
            }
        });
        save.addClickShortcut(Key.KEY_S, KeyModifier.CONTROL);

        discard = new Button("Discard changes");
        discard.setWidth("100%");
        discard.addClickListener(
                event -> viewLogic.editJob(currentJob));

        cancel = new Button("Cancel");
        cancel.setWidth("100%");
        cancel.addClickListener(event -> viewLogic.cancelProduct());
        cancel.addClickShortcut(Key.ESCAPE);
        getElement()
                .addEventListener("keydown", event -> viewLogic.cancelProduct())
                .setFilter("event.key == 'Escape'");

        delete = new Button("Delete");
        delete.setWidth("100%");
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR,
                ButtonVariant.LUMO_PRIMARY);
        delete.addClickListener(event -> {
            if (currentJob != null) {
                viewLogic.deleteJob(currentJob);
            }
        });

        content.add(save, discard, delete, cancel);
    }

    public void setCategories(Collection<Category> categories) {
        category.setItems(categories);
    }

    public void editJob(Jobs job) {
        if (job == null) {
            job = new Jobs();
        }
        delete.setVisible(!job.isNewJob());
        currentJob = job;
        // binder.readBean(job);
    }
}
