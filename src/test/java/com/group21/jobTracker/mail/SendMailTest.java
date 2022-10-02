package com.group21.jobTracker.mail;

import static org.junit.Assert.*;

import org.junit.Test;

import com.group21.jobTracker.mail.SendMail;

public class SendMailTest {

	@Test
	public void test() {
		SendMail.sendEmail("jobtracker21@outlook.com", "Gr0up#21!", "jobtracker21@outlook.com");
		
		try {
			SendMail.sendEmail("jobtracker21@outlook.com", "Gr0up#21!", "outlook.com");
			fail();
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}

}
