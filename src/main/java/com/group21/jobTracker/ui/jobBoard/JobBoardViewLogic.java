package com.group21.jobTracker.ui.jobBoard;

import java.io.Serializable;
import com.group21.jobTracker.mail.SendMail;
import com.group21.jobTracker.ui.MainLayout;

/**
 * This class provides an interface for the logical operations for the JobBoarchView (Dashboard). 
 * Specifically, it defines the functionality for when a user clicks the "send email" button on 
 * the dashboard. 
 */
public class JobBoardViewLogic implements Serializable {
	
	/** Represents the UI Object */
    private final JobBoardView view;

    /** Constructor for JobBoardViewLogic object.
     * @param simpleCrudView the JobBoardView object that the logic will be applied to.
     */
    public JobBoardViewLogic(JobBoardView simpleCrudView) {
        view = simpleCrudView;
    }

    /**
     * Method to send a reminder email for upcoming deadlines. 
     */
	public void sendEmail() {
		SendMail.sendEmail(MainLayout.email);
	}
}
