package com.group21.jobTracker.backend.data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.validation.constraints.*;
import javax.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class Jobs implements Serializable{
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int id; 
	
    private String jobTitle;
    private String company;
	private Date dateApplied;
	private Date dueDate;
	private int salary;
	private String jobDescription;
	private String nextAction;
	private String status;
	private int priority;
	private Set<Category> jobType;
	
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
	 */
	
	public Jobs() {
		this.jobTitle = "";				
	}
	
	public Jobs(String jobTitle, String company, Date dateApplied, Date dueDate, int salary, String jobDescription, String nextAction, String status, int priority) {
		setJobTitle(jobTitle);
		setCompany(company);
		setDateApplied(dateApplied);
		setDueDate(dueDate);
		setSalary(salary);
		setJobDescription(jobDescription);
		setNextAction(nextAction);
		setStaus(status);
		setPriority(priority);
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
	public Date getDateApplied() {
		return dateApplied;
	}
	/**
	 * @param dateApplied dateApplied to set
	 */
	public void setDateApplied(Date dateApplied) {
		this.dateApplied = dateApplied;
	}
	
	/**
	 * @return the dueDate
	 */
	public Date getDueDate() {
		return dueDate;
	}
	/**
	 * @param dueDate dueDate to set
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	/**
	 * @return the salary
	 */
	public int getSalary() {
		return salary;
	}
	/**
	 * @param salary salary to set
	 */
	public void setSalary(int salary) {
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
	public void setStaus(String status) {
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
	public int getPriority() {
		return priority;
	}
	/**
	 * @param priority priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	public boolean isNewJob() {
        return getId() == -1;
    }
}
