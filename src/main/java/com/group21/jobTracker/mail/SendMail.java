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

public class SendMail {

	public static void sendEmail(String tEmail) {

		final String fromEmail = "Enter your email address for the application here."; 
		final String password = "Enter your password for the email you created for this application here."; 
		final String toEmail = tEmail.trim(); 
		
		System.out.println("TLSEmail Start");
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
				currentUser = Csv.loadUser(MainLayout.userName);
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException("Issue loading data for current user.");
			} catch (ParseException e) {
				throw new IllegalArgumentException("Issue loading data for current user.");
			}
        	
        	System.out.println(currentUser.getFullName());
        	
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

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }	
        
	}
}
