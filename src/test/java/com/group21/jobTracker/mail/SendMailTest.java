package com.group21.jobTracker.mail;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Method that tests the send mail functionality. Can only be tested locally because you should NEVER
 * push the username/password of the email you have set up.
 */
public class SendMailTest {

	/**
	 * Tests failing case for sendMail functionality (invalid address)
	 */
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
