package com.group21.jobTracker.backend.data;

import java.io.Serializable;
import java.util.Date;
//import javax.validation.constraints.*;
import java.util.Set;


public class Jobs implements Serializable{
	
	//@NotNull(message="id must not be null")
    private int id;

	private String name;
	
    //@NotNull(message="jobTitle must not be null")
    private String jobTitle;
    
    //@NotNull(message="company must not be null")
    private String company;

	private Set<Category> category;
    
    //@NotNull(message="dateApplied must not be null")
	private Date dateApplied;
    
	private String nextAction;
	private String status;
	private int priority;
	 
    /*Is id necessary? */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}

	public Set<Category> getCategory() {
		return category;
	}
	public void setCategory(Set<Category> category) {
		this.category = category;
	}
	
	public Date getDateApplied() {
		return dateApplied;
	}
	public void setDateApplied(Date dateApplied) {
		this.dateApplied = dateApplied;
	}
	
	public String getNextAction() {
		return nextAction;
	}
	public void setNextAction(String nextAction) {
		this.nextAction = nextAction;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStaus(String status) {
		if (!status.equals("In progress") && !status.equals("Rejected") && !status.equals("Accepted")) {
			throw new IllegalArgumentException();
		}
		this.status = status;
	}
	
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		if (priority > 3 || priority < 1) {
			throw new IllegalArgumentException();
		}
		this.priority = priority;
	}
	//checking whther this is a new job or exiting one

	public boolean isNewJob() {
        return getId() == -1;
    }
}
