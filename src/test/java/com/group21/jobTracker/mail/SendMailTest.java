package com.group21.jobTracker.mail;

import static org.junit.Assert.*;

import org.junit.Test;

import com.group21.jobTracker.mail.SendMail;
import com.group21.jobTracker.ui.MainLayout;

public class SendMailTest {

	@Test
	public void test() {
		// attempting to run sendEmail function on invalid address (will fail)
		try {
			SendMail.sendEmail("outlook.com");
			fail();
		} catch (RuntimeException e) {
			//Intentionally left blank
		}
	}

}
