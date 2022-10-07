package com.group21.jobTracker.ui.application;


import com.group21.jobTracker.backend.data.Jobs;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;

/**
 * An Application for performing create-read-update-delete operations on Jobs.
 *
 * See also {@link ApplicationViewLogic} for fetching the data, the actual CRUD
 * operations and controlling the view based on events from outside.
 */
@SuppressWarnings("serial")
public class ApplicationForm extends Div {

	/** private final parameter representing the content on the form*/
    private final VerticalLayout content;

    /** private final TextField parameter representing jobId*/
    public final TextField jobId;
    /** private final TextField parameter representing jobTitle*/
    public final TextField jobTitle;
    /** private final TextField parameter representing jobCompany*/
    public final TextField jobCompany;
    /** private final TextField parameter representing jobDateApplied*/
    public final DatePicker jobDateApplied;
    /** private final TextField parameter representing jobDueDate*/
    public final DatePicker jobDueDate;
    /** private final TextField parameter representing jobSalary*/
    public final TextField jobSalary;
    /** private final TextField parameter representing jobDescription*/
    public final TextField jobDescription;
    /** private final TextField parameter representing jobNextAction*/
    public final TextField jobNextAction;
    /** private final TextField parameter representing jobStatus*/
    public final TextField jobStatus;
    /** private final TextField parameter representing jobPriority*/
    public final NumberField jobPriority;
    
    /** private final Button parameter representing saving job button*/
    private Button save;
    /** private final Button parameter representing cancel job button*/
    private Button cancel;
    /** private final Button parameter representing delete job button*/
    private final Button delete;

    /** private final ApplicationViewLogic instance to handle the CRUD operation for Application form*/
    private final ApplicationViewLogic viewLogic;
    /** private final Binder instance to Bind information entered into the Application form*/
    private final Binder<Jobs> binder;
    /** private Jobs variable represents the job object to add, delete, update*/
    private Jobs currentJob;


    /**
     * ApplicationForm for constructor is using to define the job form by defining  labels of each textfields and 
     * values. Binder then binding them into the form to work with the values we are getting from the form textfields.
     * There are action listeners for save, update, delete buttons which is using the binding information to do crud operations
     * Evry change happening on the form is adding intot he content variable to show the changes n the form.
     * 
     * @param sampleCrudLogic to initialize the view instance for CRUD operations
     *
     */
    public ApplicationForm(ApplicationViewLogic sampleCrudLogic) {
        setClassName("job-form");
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
        
        jobPriority = new NumberField("Priority");
        jobPriority.setWidth("100%");
        jobPriority.setValueChangeMode(ValueChangeMode.EAGER);
        
        content.add(jobPriority);
        

        binder = new BeanValidationBinder<>(Jobs.class);
        binder.forField(jobId).bind(Jobs::getStringId, Jobs::setStringId);
        binder.forField(jobTitle).bind(Jobs::getJobTitle, Jobs::setJobTitle);
        binder.forField(jobCompany).bind(Jobs::getCompany, Jobs::setCompany);
        binder.forField(jobDateApplied).bind(Jobs::getDateApplied, Jobs::setDateApplied);
        binder.forField(jobDueDate).bind(Jobs::getDueDate, Jobs::setDueDate);
        binder.forField(jobSalary).bind(Jobs::getSalary, Jobs::setSalary);
        binder.forField(jobDescription).bind(Jobs::getJobDescription, Jobs::setJobDescription);
        binder.forField(jobNextAction).bind(Jobs::getNextAction, Jobs::setNextAction);
        binder.forField(jobStatus).bind(Jobs::getStatus, Jobs::setStatus);
        binder.forField(jobPriority).bind(Jobs::getPriority, Jobs::setPriority);
        binder.bindInstanceFields(this);
        binder.readBean(currentJob);

        
        save = new Button("Save");
        save.setWidth("100%");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.addClickListener(event -> {
        	currentJob = new Jobs();
            if(jobId.getValue().equals("")){
                currentJob.setId(0);
            } else {
                currentJob.setId(Integer.parseInt(jobId.getValue()));
            }
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
        cancel.addClickListener(event -> viewLogic.cancelJob());
        cancel.addClickShortcut(Key.ESCAPE);
        getElement()
                .addEventListener("keydown", event -> viewLogic.cancelJob())
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


    /**
     * This function use a job object as parameter and utilize the view object to call 
     * the edit method for the job instance. After it will pop up the information from the 
     * desired job of grids to the application form.
     * @param job
     * 
     **/
    public void editJob(Jobs job) {
        if (job == null) {
            job = new Jobs();
        }
        delete.setVisible(!job.isNewJob());
        currentJob = job;
        // binder.readBean(job);
    }
    
    /**
     * This function use a job object as parameter to set it as current job and bind it to the application form.
     * @param job
     * 
     **/
    public void setJob(Jobs job) {
        this.currentJob = job;
        binder.setBean(currentJob);
    }
}
