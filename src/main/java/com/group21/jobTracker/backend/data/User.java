/**
 * 
 */
package com.group21.jobTracker.backend.data;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.validation.constraints.NotBlank;

/**
 * Class that represents the user in JobTracker.
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
	 * @param email of the user
	 * @param gender of the user 
	 * @param age of the user
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
	 * @param fullName of the user
	 * @param email of the user
	 */
	public User(String fullName, String email) {
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
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	/**
	 * Getter for gender
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	
	/**
	 * Sets the gender of the user
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	/**
	 * Gets the age of the user
	 * @return the age
	 */
	public String getAge() {
		return age;
	}
	
	/**
	 * Set the age of the user
	 * @param age to set
	 */
	public void setAge(String age) {
		this.age = age;
	}
	
	/**
	 * Get experience of the user
	 * @return the experience of the user
	 */
	public String getExperience() {
		return experience;
	}
	
	/**
	 * Set the experience of the user.
	 * @param experience to set
	 */
	public void setExperience(String experience) {
		this.experience = experience;
	}
	
	/**
	 * Get keywords from the user
	 * @return the keywords
	 */
	public String getKeywords() {
		return keywords;
	}
	
	/**
	 * Set the keywords 
	 * @param keywords the keywords to set
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	/**
	 * Adds a job to the list of user jobs
	 * @param job add the given job object to a list of the users jobs
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
	 * @param type either DueDate or priority 
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
	 * @return a list of the 5 jobs with the highest priority
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
	 * @return the user as a string
	 */
	@Override
	public String toString() {
		String[] list = {getProcessedFullName(), getEmailAddress(), getGender(), getAge(), getExperience(), getKeywords()};
		String output = "";
		for (int i = 0; i < 6; i++) {
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
		return this.fullName.replace(" ", "_");
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
