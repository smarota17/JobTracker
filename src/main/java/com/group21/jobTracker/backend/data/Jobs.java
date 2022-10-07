package com.group21.jobTracker.backend.data;

import java.io.Serializable;
import java.time.LocalDate;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Representation of a Job application
 *
 */
@SuppressWarnings("serial")
public class Jobs implements Serializable{
	
	/** Id number used for tracking the job's location in the User list **/
    private int id = -1; 
    /** Title of the job **/
    private String jobTitle;
    /** Company that is hiring **/
    private String company;
    /** The date that the user applied **/
	private LocalDate dateApplied;
	/** The date that the next action is due **/
	private LocalDate dueDate;
	/** The salary of the job **/
	private String salary;
	/** The description of the job **/
	private String jobDescription;
	/** The next action that needs to be taken **/
	private String nextAction;
	/** The status of the job **/
	private String status;
	/** The priority of the job **/
	private double priority;
	/** When the user wants to be reminded about the job **/
	private String remindMeOn;
	private String link;
	
	
	
	/**
	 * Constructs the Job without any parameters
	 */
	public Jobs() {
		this.jobTitle = "";				
	}
	
	/**
	 * Constructs the Jobs object
	 * @param jobTitle title of the job
	 * @param company name of the company
	 * @param dateApplied date of the application
	 * @param dueDate due date of job
	 * @param salary salary of job
	 * @param jobDescription description of job
	 * @param nextAction next action for job
	 * @param status status of job
	 * @param priority priority for job
	 */
	public Jobs(String jobTitle, String company, LocalDate dateApplied, LocalDate dueDate, String salary, String jobDescription, String nextAction, String status, double priority) {
		setJobTitle(jobTitle);
		setCompany(company);
		setDateApplied(dateApplied);
		setDueDate(dueDate);
		setSalary(salary);
		setJobDescription(jobDescription);
		setNextAction(nextAction);
		setStatus(status);
		setPriority(priority);
		
	}
	
	/**
	 * Helper method to verify string input
	 * @param input the string that is being verified as a date
	 */
	private void verifyInput(String input) {
		try {
			new SimpleDateFormat("MM/dd/yyyy").parse(input);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Getter for the Id field
	 * @return the id of the job
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Gets the id as a string for front end use
	 * @return the id as a string
	 */
	public String getStringId() {
		return String.valueOf(id);
	}
	
	/**
	 * Sets the id from a string
	 * @param id the new id
	 */
	public void setStringId(String id) {
		this.id = Integer.valueOf(id);
	}

	/**
	 * Sets the id
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter method for the title
	 * @return the name
	 */
	public String getJobTitle() {
		return jobTitle;
	}
	/**
	 * Setter method for the job title
	 * @param name the name to set
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	/**
	 * Getter method for the company
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}
	
	/**
	 * Setter for the company
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	
	/**
	 * Getter for the DateApplied field
	 * @return the dateApplied
	 */
	public LocalDate getDateApplied() {
		return dateApplied;
	}
	
	/**
	 * Setter for the DateApplied field
	 * @param dateApplied dateApplied to set
	 */
	public void setDateApplied(LocalDate dateApplied) {
		this.dateApplied = dateApplied;
	}
	
	/**
	 * Getter for the DueDate field
	 * @return the dueDate
	 */
	public LocalDate getDueDate() {
		return dueDate;
	}
	
	/**
	 * Setter for the DueDate field
	 * @param dueDate dueDate to set
	 */
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	
	/**
	 * Getter for the salary field
	 * @return the salary
	 */
	public String getSalary() {
		return salary;
	}
	
	/**
	 * Setter for the salary field
	 * @param salary salary to set
	 */
	public void setSalary(String salary) {
		this.salary = salary;
	}
	
	/**
	 * Getter for the Job Description
	 * @return the jobDescription
	 */
	public String getJobDescription() {
		return jobDescription;
	}
	
	/**
	 * Setter for the Job Description
	 * @param jobDescription jobDescription to set
	 */
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	
	/**
	 * Getter for the nextAction field
	 * @return the nextAction
	 */
	public String getNextAction() {
		return nextAction;
	}
	
	/**
	 * Setter for the next action field
	 * @param nextAction nextAction to set
	 */
	public void setNextAction(String nextAction) {
		this.nextAction = nextAction;
	}
	
	/**
	 * Getter for the status field
	 * @return the status
	 */
	public String getStatus() {
		return this.status;
	}
	/**
	 * Setter for the status field
	 * @param status status to set
	 */
	public void setStatus(String status) {
		if(status == null){
			this.status = null;
		}
		else if (status.toLowerCase().equals("in progress")) {
			this.status = "in progress";
		} else if (status.toLowerCase().equals("rejected")) {
			this.status = "rejected";
		} else if (status.toLowerCase().equals("accepted")) {
			this.status = "accepted";
		} else {
			this.status = status;
		}
	}
	
	/**
	 * Getter for the priority field
	 * @return the priority
	 */
	public double getPriority() {
		return priority;
	}
	
	/**
	 * Setter for the priority field
	 * @param priority priority to set
	 */
	public void setPriority(double priority) {
		this.priority = priority;
	}
	
	/**
	 * Getter for the reminder field
	 * @return the dueDate
	 */
	public String getRemindMeOn() {
		return this.remindMeOn;
	}
	
	/**
	 * Setter for the reminder field
	 * @param dueDate dueDate to set
	 */
	public void setRemindMeOn(String remindMeOn) {
		this.verifyInput(remindMeOn);
		this.remindMeOn = remindMeOn;
	}
	
	/**
	 * Determines if the job is a new job or not
	 * @return true if the job is new
	 */
	public boolean isNewJob() {
        return getId() == -1;
    }
	
	/**
	 * Overrides the toString method to convert the object into a string
	 * @return this object as a string
	 */
	@Override
	public String toString() {
	      return this.jobTitle+" "+this.company+" "+Double.toString(this.priority);
	}
	
	/**
	 * Determines if this job application is equal to another
	 * @param job2 the other job being compared to 
	 * @return true if the objects are equal
	 */
	public boolean equals(Jobs job2) {
		return this.toSaveString().equals(job2.toSaveString());
	}

	/**
	 * Expresses the job as a string that is used to save the file
	 * @return the string representation of the job
	 */
	public String toSaveString() {
		String output = "";
		String[] list = {Integer.toString(id), jobTitle, company, dateToString(dateApplied), dateToString(dueDate), salary, jobDescription, nextAction, status, Double.toString(priority)};
		for (int i = 0; i < list.length; i++) {
			
			if (list[i] != null) {
				list[i] = list[i].replace('~', '-');
				output += list[i] + "~";
			} else {
				output += "NULL~";
			}
		}
		
		return output;
		
	}
	
	/**
	 * Helper function that formats the date into a string
	 * @param date the date being formatted	
	 * @return the string representation of the date
	 */
	public static String dateToString(LocalDate date) {
		if (date == null) {
			return "NULL";
		}
		String output = "" + date.getYear() + "-";
		if(date.getMonthValue() < 10) { 
			output += "0";
		}
		output += date.getMonthValue() + "-";
		if(date.getDayOfMonth() < 10) {
			output += "0";
		}
		output += date.getDayOfMonth();
		return output;
	}

	public void setLink(String link){
		this.link = link;
	}

	public String getLink(){
		return this.link;
	}
}
