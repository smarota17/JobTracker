/**
 * 
 */
package com.group21.jobTracker.backend.data;

import java.util.ArrayList;

/**
 *
 */
public class User {

	/** Name of the user */
	private String name;
	/** Gender of the user **/
	private int gender;
	/** The job field of the user **/
	private String field;
	/** Education level of the user **/
	private String education;
	/** Job searching keywords for the user **/
	private String keywords;
	/** List of job applications saved to the user **/
	private ArrayList<Jobs> jobs;
	
	/**\
	 * Constructs the user object
	 * @param name name of the user
	 * @param gender gender of the user
	 * @param field job field of the user
	 * @param education education level of the user
	 * @param keywords job searching keywords for the user
	 */
	public User(String name, String gender, String field, String education, String keywords) {
		setName(name);
		setGender(gender);
		setField(field);
		setEducation(education);
		setKeywords(keywords);
		jobs = new ArrayList<Jobs>();
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		if(gender == 1) {
			return "Male";
		} else if(gender == 2) {
			return "Female";
		} else if(gender == 0) {
			return "Nonbinary";
		}
		return null;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		if(gender.toLowerCase().equals("male")) {
			this.gender = 1;
		} else if(gender.toLowerCase().equals("female")) {
			this.gender = 2;
		} else if(gender.toLowerCase().equals("nonbinary")) {
			this.gender = 0;
		}
	}
	/**
	 * @return the major
	 */
	public String getField() {
		return field;
	}
	/**
	 * @param major the major to set
	 */
	public void setField(String field) {
		this.field = field;
	}
	/**
	 * @return the education
	 */
	public String getEducation() {
		return education;
	}
	/**
	 * @param education the education to set
	 */
	public void setEducation(String education) {
		this.education = education;
	}
	/**
	 * @return the keywords
	 */
	public String getKeywords() {
		return keywords;
	}
	/**
	 * @param keywords the keywords to set
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	/**
	 * Adds a job to the list of user jobs
	 */
	public void addJob(Jobs job) {
		jobs.add(job);
	}
	
	/**
	 * Gets the list of jobs 
	 * @return the list of jobs
	 */
	public ArrayList<Jobs> getJobs() {
		return jobs;
	}
	
	/**
	 * Overrides the toString method to express the user as a string
	 * 
	 * @returns the user as a string
	 */
	@Override
	public String toString() {
		String[] list = {getName(), getGender(), getField(), getEducation(), getKeywords()};
		String output = "";
		for (int i = 0; i < 5; i++) {
			if (list[i] != null) {
				output += list[i] + ",";
			} else {
				output += "NULL,";
			}
		}
		return output;
	}
	
	/**
	 * Converts the user into a string to be saved to a file
	 * @return the string representation of the user
	 */
	public String toSaveString() {
		String output = this.toString();
		for(Jobs j : jobs) {
			output += "\n" + j.toSaveString();
		}
		return output;
	}
	
}
