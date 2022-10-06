/**
 * 
 */
package com.group21.jobTracker.backend.data;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.validation.constraints.NotBlank;

/**
 *
 */
public class User {

	/** Name of the user */
	@NotBlank
	private String fullName;
	/** Email address of the user */
	@NotBlank
	private String emailAddress;
	/** Gender of the user */
	private String gender;
	/** The job field of the user **/
	private String age;
	/** Education level of the user **/
	private String experience;
	/** Job searching keywords for the user **/
	private String keywords;
	/** List of job applications saved to the user **/
	private ArrayList<Jobs> jobs;
	
	/**
	 * Constructs the user object
	 * @param fullName fullName of the user
	 * @param gender gender of the user
	 * @param field job field of the user
	 * @param experience education level of the user
	 * @param keywords job searching keywords for the user
	 */
	public User(String fullName, String email, String gender, String age, String experience, String keywords) {
		setFullName(fullName);
		setEmailAddress(email);
		setGender(gender);
		setAge(age);
		setExperience(experience);
		setKeywords(keywords);
		jobs = new ArrayList<Jobs>();
	}
	
	/**
	 * Constructs the user object with just a name and email
	 * @param fullName
	 * @param email
	 */
	public User(String fullName,String email) {
		this.fullName = fullName;
		this.emailAddress = email;
	}

	/**
	 * Getter for the email field
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * Setter for the email field
	 * @param emailAddress the email Address to set
	*/
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * Getter for the fullName field
	 * @return the full name
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * Setter for the full name field
	 * @param fullName the full name to set
	*/
	public String setFullName(String fullName) {
		return this.fullName = fullName;
	}
	
	/**
	 * Getter
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the major
	 */
	public String getAge() {
		return age;
	}
	/**
	 * @param major the major to set
	 */
	public void setAge(String age) {
		this.age = age;
	}
	/**
	 * @return the education
	 */
	public String getExperience() {
		return experience;
	}
	/**
	 * @param education the education to set
	 */
	public void setExperience(String experience) {
		this.experience = experience;
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
		updateId();
	}
	
	/**
	 * Gets the list of jobs 
	 * @return the list of jobs
	 */
	public ArrayList<Jobs> getJobs() {
		return jobs;
	}
	
	/**
	 * Gets the 5 jobs with the closest dates
	 * @return a list of the 5 jobs with the closest dates
	 */
	public ArrayList<Jobs> getJobsByDate( String type ) {
		ArrayList<Jobs> output = new ArrayList<Jobs>();
		for (Jobs j : jobs) {
			LocalDate oldDate = null;
			if (type.equals("DueDate")) {
				oldDate = j.getDueDate();
			} else {
				oldDate = j.getDateApplied();
			}
			
			for (int i = 0; i < 5; i++) {
				
				if (i == output.size()) {
					output.add(i, j);
					break;
				}
				
				LocalDate newDate = null;
				if (type.equals("DueDate")) {
					newDate = output.get(i).getDueDate();
					if (oldDate.compareTo(newDate) < 0) {
						output.add(i, j);
						if (output.size() > 5) {
							output.remove(5);
						}
						break;
					}
				} else {
					newDate = output.get(i).getDateApplied();
					if (oldDate.compareTo(newDate) > 0) {
						output.add(i, j);
						if (output.size() > 5) {
							output.remove(5);
						}
						break;
					}
				}
				
			}
		}
		return output;
	}
	
	/**
	 * Gets the 5 jobs with the highest priority
	 * @returns a list of the 5 jobs with the highest priority
	 */
	public ArrayList<Jobs> getJobsByPriority() {
		ArrayList<Jobs> output = new ArrayList<Jobs>();
		for (Jobs j : jobs) {
			for (int i = 0; i < 5; i++) {
				
				if (i == output.size()) {
					output.add(i, j);
					break;
				}
				
				if (j.getPriority() < output.get(i).getPriority()) {
					output.add(i, j);
					if (output.size() > 5) {
						output.remove(5);
					}
					break;
				}
				
			}
		}
		return output;
	}
	
	/**
	 * Overrides the toString method to express the user as a string
	 * 
	 * @returns the user as a string
	 */
	@Override
	public String toString() {
		String[] list = {getFullName(), getEmailAddress(), getGender(), getAge(), getExperience(), getKeywords()};
		String output = "";
		for (int i = 0; i < 6; i++) {
			if (list[i] != null) {
				output += list[i] + "~";
			} else {
				output += "NULL~";
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

	/**
	 * Gets the full name after being processed
	 * @return the processed full name
	 */
	public String getProcessedFullName(){
		return this.fullName.replace(" ", "");
	}

	/**
	 * Deletes an existing job from the user
	 * @param job the job being deleted
	 */
	public void deleteExistingJob(Jobs job) {
		this.getJobs().remove(job.getId());
		updateId();
	}
	
	/**
	 * Updates the id of all of the jobs saved to a user
	 */
	public void updateId() {
		for (int i = 0; i < this.getJobs().size(); i++) {
			this.getJobs().get(i).setId(i);
		}
	}
}
