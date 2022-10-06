/**
 * 
 */
package com.group21.jobTracker.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Scanner;

import com.group21.jobTracker.backend.data.Jobs;
import com.group21.jobTracker.backend.data.User;

/**
 *
 */
public class Csv {
	
	/**
	 * Saves the given user as a string to the related csv file
	 * @param user the user being saved
	 */
	public static void saveUser(User user) {
		String path = FileSystems.getDefault()
                .getPath("data")
                .toAbsolutePath()
                .toString();
		File data = new File(path+"/");
		if (!data.exists()){
			data.mkdirs();
		}
		File file = new File(path+"/" + user.getProcessedFullName() + ".csv");
		file.delete();
		try {
		file.createNewFile();
		FileWriter writer = new FileWriter(file);
		writer.write(user.toSaveString());
		writer.close();
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file");
		}
	}
	
	/**
	 * Loads a specified user from a file
	 * @param username the username of the specified user
	 * @return the user as a User object
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 */
	public static User loadUser(String username) throws NumberFormatException, ParseException {
		String path = FileSystems.getDefault()
                .getPath("data")
                .toAbsolutePath()
                .toString();
		File file = new File(path+"/" + username + ".csv");
		System.out.println(path);
		if (!file.exists()) {
			throw new IllegalArgumentException("No saved data found");
		}
		try {
			Scanner scan = new Scanner(file);
			String[] list = scan.nextLine().split(",");
			for(int i = 0; i < list.length; i++) {
				if (list[i].equals("NULL")) {
					list[i] = null;
				}
			}
			User user = new User(list[0], list[1], list[2], list[3], list[4], list[5]);
			while (scan.hasNext()) {
				user.addJob(loadJobs(scan.nextLine()));
			}
			scan.close();
			return user;
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("No saved data found");
		}
		
	}
	
	/**
	 * Loads a job from a string
	 * @param line The string representation of the Job
	 * @return the job loaded from the string
	 * @throws NumberFormatException if the format of the date is incorrect
	 * @throws ParseException if the parser encounters an error
	 */
	private static Jobs loadJobs(String line) throws NumberFormatException, ParseException {
		String[] list = line.split(",");
		for(int i = 0; i < list.length; i++) {
			if (list[i].equals("NULL")) {
				list[i] = null;
			}
		}
		Jobs job = new Jobs(list[1], list[2], null, null, list[5], list[6], list[7], list[8], Double.parseDouble(list[9]) );
		if (list[3] != null) {
			job.setDateApplied(LocalDate.parse(list[3]));
		}
		if (list[4] != null) {
			job.setDueDate(LocalDate.parse(list[4]));
		}
		return job;
		
	}

}
