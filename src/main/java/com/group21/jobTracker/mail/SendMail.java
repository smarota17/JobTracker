package com.group21.jobTracker.mail;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.group21.jobTracker.backend.data.Jobs;
import com.group21.jobTracker.backend.data.User;
import com.group21.jobTracker.csv.Csv;
import com.group21.jobTracker.ui.MainLayout;

/**
 * This class is used to handle the "Send reminder email" functionality for JobTracker. NEVER
 * push the username/password of the email you have set up to your repo. 
 */
public class SendMail {
	
	/**
	 * This method uses the javax mail package to send an email using Outlook. In order to use
	 * this method, make sure to set up an outlook email and supply the username and password in
	 * the "fromEmail" and "password" fields. The email contains a list of the 5 most upcoming
	 * deadlines for the user.
	 * @param tEmail email to send to 
	 */
	public static void sendEmail(String tEmail) {

		final String fromEmail = "Enter your email address for the application here."; 
		final String password = "Enter your password for the email you created for this application here."; 
		final String toEmail = tEmail.trim(); 
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "outlook.office365.com"); // SMTP Host
		props.put("mail.smtp.port", "587"); // TLS Port
		props.put("mail.smtp.auth", "true"); // enable authentication
		props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS
		
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
		});

        try {
        	
        	User currentUser = null;
        	try {
				currentUser = Csv.loadUser(MainLayout.userName.replaceAll("\\s", "_"));
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException("Issue loading data for current user.");
			} catch (ParseException e) {
				throw new IllegalArgumentException("Issue loading data for current user.");
			}
        	
        	// construct string for list of jobs
        	String jobData = "";
        	ArrayList<Jobs> jobList = currentUser.getJobsByDate("DueDate");
        	for (Jobs j : jobList) {
        		jobData += "- Position '" + j.getJobTitle() + "' at " + j.getCompany() + "' is due on " + j.getDueDate() + ".\n";
        	}
        	
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(toEmail));
            message.setSubject("JobTracker Reminder: Upcoming Deadlines");
            message.setText("Here are your upcoming application deadlines: \n\n" + jobData);

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }	
        
	}
}
