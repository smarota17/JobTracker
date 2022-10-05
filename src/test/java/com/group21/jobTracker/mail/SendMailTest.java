package com.group21.jobTracker.mail;

import static org.junit.Assert.*;

import org.junit.Test;

import com.group21.jobTracker.mail.SendMail;
import com.group21.jobTracker.ui.MainLayout;

public class SendMailTest {

	@Test
	public void test() {
		MainLayout.userName = "TestName";
		SendMail.sendEmail("smarota1861@gmail.com");
		
//		try {
//			SendMail.sendEmail("outlook.com");
//			fail();
//		} catch (RuntimeException e) {
//			System.out.println(e.getMessage());
//		}
	}

}
