package com.group21.jobTracker.backend.data;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

/*
import javax.validation.constraints.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
*/

public class Jobs implements Serializable{
	
	/*
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	*/
	
    private int id = -1; 
	
    private String jobTitle;
    private String company;
	private LocalDate dateApplied;
	private LocalDate dueDate;
	private String salary;
	private String jobDescription;
	private String nextAction;
	private String status;
	private String priority;
	private Set<Category> jobType;
	private String remindMeOn;
	
	/**\
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
	 * @param remindMeOn date for reminder to remind
	 */
	
	public Jobs() {
		this.jobTitle = "";				
	}
	
	public Jobs(String jobTitle, String company, LocalDate dateApplied, LocalDate dueDate, String salary, String jobDescription, String nextAction, String status, String priority) {
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
	
	private void verifyInput(String input) {
		try {
			new SimpleDateFormat("MM/dd/yyyy").parse(input);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<Category>  getJobType() {
		return jobType;
	}

	public void setJobType(Set<Category>  jobType) {
		this.jobType = jobType;
	}

	/**
	 * @return the name
	 */
	public String getJobTitle() {
		return jobTitle;
	}
	/**
	 * @param name the name to set
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}
	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	
	/**
	 * @return the dateApplied
	 */
	public LocalDate getDateApplied() {
		return dateApplied;
	}
	/**
	 * @param dateApplied dateApplied to set
	 */
	public void setDateApplied(LocalDate dateApplied) {
		this.dateApplied = dateApplied;
	}
	
	/**
	 * @return the dueDate
	 */
	public LocalDate getDueDate() {
		return dueDate;
	}
	/**
	 * @param dueDate dueDate to set
	 */
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	
	/**
	 * @return the salary
	 */
	public String getSalary() {
		return salary;
	}
	/**
	 * @param salary salary to set
	 */
	public void setSalary(String salary) {
		this.salary = salary;
	}
	
	/**
	 * @return the jobDescription
	 */
	public String getJobDescription() {
		return jobDescription;
	}
	/**
	 * @param jobDescription jobDescription to set
	 */
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	
	/**
	 * @return the nextAction
	 */
	public String getNextAction() {
		return nextAction;
	}
	/**
	 * @param nextAction nextAction to set
	 */
	public void setNextAction(String nextAction) {
		this.nextAction = nextAction;
	}
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return this.status;
	}
	/**
	 * @param status status to set
	 */
	public void setStatus(String status) {
		if (status.toLowerCase().equals("in progress")) {
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
	 * @return the priority
	 */
	public String getPriority() {
		return priority;
	}
	
	/**
	 * @param priority priority to set
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	/**
	 * @return the dueDate
	 */
	public String getRemindMeOn() {
		return this.remindMeOn;
	}
	/**
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
	 */
	@Override
	public String toString() {
	      return this.jobTitle+" "+this.company+" "+this.priority;
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
		String[] list = {Integer.toString(id), jobTitle, company, dateToString(dateApplied), dateToString(dueDate), salary, jobDescription, nextAction, status, priority};
		for (int i = 0; i < list.length; i++) {
			if (list[i] != null) {
				output += list[i] + ",";
			} else {
				output += "NULL,";
			}
		}
		if (jobType == null || jobType.isEmpty()) {
			output += "NULL,";
		} else {
			//Add Category
		}
		
		return output;
		
	}
	
	/**
	 * Helper function that formats the date into a string
	 * @param date the date being formatted	
	 * @return the string representation of the date
	 */
	@SuppressWarnings("deprecation")
	private String dateToString(LocalDate date) {
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
}
