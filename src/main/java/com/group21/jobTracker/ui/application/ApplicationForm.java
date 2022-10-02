package com.group21.jobTracker.ui.application;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
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

    private final TextField jobId;
    private final TextField jobTitle;
    private final TextField jobCompany;
    private final DatePicker jobDateApplied;
    private final DatePicker jobDueDate;
    private final TextField jobSalary;
    private final TextField jobDescription;
    private final TextField jobNextAction;
    private final TextField jobStatus;
    private final TextField jobPriority;
    
    private Button save;
    private Button cancel;
    private final Button delete;

    private final ApplicationViewLogic viewLogic;
    private final Binder<Jobs> binder;
    private Jobs currentJob;


    public ApplicationForm(ApplicationViewLogic sampleCrudLogic) {
        setClassName("job-form");
        System.out.println("inside application form: ");
        content = new VerticalLayout();
        content.setSizeUndefined();
        content.addClassName("job-form-content");
        add(content);

        viewLogic = sampleCrudLogic;

        jobId = new TextField("Job Title");
        jobId.setWidth("100%");
        jobId.setRequired(true);
        jobId.setValueChangeMode(ValueChangeMode.EAGER);
                
        jobTitle = new TextField("Job Title");
        jobTitle.setWidth("100%");
        jobTitle.setRequired(true);
        jobTitle.setValueChangeMode(ValueChangeMode.EAGER);
        
        content.add(jobTitle);
        
        jobCompany = new TextField("Company");
        jobCompany.setWidth("100%");
        jobCompany.setRequired(true);
        jobCompany.setValueChangeMode(ValueChangeMode.EAGER);
        
        content.add(jobCompany);
        
        jobDateApplied = new DatePicker("Date Applied");
        jobDateApplied.setWidth("100%");
        jobDateApplied.setRequired(true);
        
        content.add(jobDateApplied);
        
        jobDueDate = new DatePicker("Due Date");
        jobDueDate.setWidth("100%");
        jobDueDate.setRequired(true);
        
        content.add(jobDueDate);
        
        jobSalary = new TextField("Salary");
        jobSalary.setWidth("100%");
        Div dollarPrefix = new Div();
        dollarPrefix.setText("$");
        jobSalary.setPrefixComponent(dollarPrefix);
        jobSalary.setValueChangeMode(ValueChangeMode.EAGER);
        
        content.add(jobSalary);
        
        jobDescription = new TextField("Description");
        jobDescription.setWidth("100%");
        jobDescription.setRequired(true);
        jobDescription.setValueChangeMode(ValueChangeMode.EAGER);
        
        content.add(jobDescription);
        jobNextAction = new TextField("Next Actions");
        jobNextAction.setWidth("100%");
        jobNextAction.setRequired(true);
        jobNextAction.setValueChangeMode(ValueChangeMode.EAGER);
       
        content.add(jobNextAction);
        
        jobStatus = new TextField("Status");
        jobStatus.setWidth("100%");
        jobStatus.setRequired(true);
        jobStatus.setValueChangeMode(ValueChangeMode.EAGER);
        
        content.add(jobStatus);
        
        jobPriority = new TextField("Priority");
        jobPriority.setWidth("100%");
        jobPriority.setRequired(true);
        jobPriority.setValueChangeMode(ValueChangeMode.EAGER);
        
        content.add(jobPriority);
        

        binder = new BeanValidationBinder<>(Jobs.class);
        binder.forField(jobId).bind(Jobs::getStringId,Jobs::setStringId);
        binder.forField(jobTitle).bind(Jobs::getJobTitle,Jobs::setJobTitle);
        binder.forField(jobCompany).bind(Jobs::getCompany,Jobs::setCompany);
        binder.forField(jobDateApplied).bind(Jobs::getDateApplied,Jobs::setDateApplied);
        binder.forField(jobDueDate).bind(Jobs::getDueDate,Jobs::setDueDate);
        binder.forField(jobSalary).bind(Jobs::getSalary,Jobs::setSalary);
        binder.forField(jobDescription).bind(Jobs::getJobDescription,Jobs::setJobDescription);
        binder.forField(jobNextAction).bind(Jobs::getNextAction,Jobs::setNextAction);
        binder.forField(jobStatus).bind(Jobs::getStatus,Jobs::setStatus);
        binder.forField(jobPriority).bind(Jobs::getPriority,Jobs::setPriority);
        binder.bindInstanceFields(this);
        binder.readBean(currentJob);

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
        	currentJob.setId(Integer.parseInt(jobId.getValue()));
        	currentJob.setJobTitle(jobTitle.getValue());
        	currentJob.setCompany(jobCompany.getValue());
        	currentJob.setDateApplied(jobDateApplied.getValue());
        	currentJob.setDueDate(jobDueDate.getValue());
        	currentJob.setSalary(jobSalary.getValue());
        	currentJob.setJobDescription(jobDescription.getValue());
        	currentJob.setNextAction(jobNextAction.getValue());
        	currentJob.setStatus(jobStatus.getValue());
        	currentJob.setPriority(jobPriority.getValue());

            if (currentJob != null && binder.writeBeanIfValid(currentJob)
                ) {
                viewLogic.saveProduct(currentJob);
            }
        });
        save.addClickShortcut(Key.KEY_S, KeyModifier.CONTROL);

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
            if (currentJob != null && binder.writeBeanIfValid(currentJob)) {
                viewLogic.deleteJob(currentJob);
            }
        });

        content.add(save, delete, cancel);
    }


    public void editJob(Jobs job) {
        if (job == null) {
            job = new Jobs();
        }
        delete.setVisible(!job.isNewJob());
        currentJob = job;
        // binder.readBean(job);
    }
    
    public void setJob(Jobs job) {
        this.currentJob = job;
        binder.setBean(currentJob);

//        // Show delete button for only customers already in the database
//        delete.setVisible(customer.isPersisted());
//        setVisible(true);
//        firstName.selectAll();
    }
}
