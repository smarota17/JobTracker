package com.group21.jobTracker.ui.emailSend;

import com.group21.jobTracker.backend.data.User;
import com.group21.jobTracker.ui.MainLayout;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "Sendemail",layout = MainLayout.class)
@PageTitle("Send Email")
public class EmailSendView extends HorizontalLayout{
	public static final String VIEW_NAME = "Send Email";
	public static final int charLimit = 5000;
	
	public EmailSendView() {
		setClassName("email-form");

		VerticalLayout sendEmailLayout = new VerticalLayout();


		TextField toEmail = new TextField();
		toEmail.setLabel("To");
		toEmail.setWidth("50%");
		sendEmailLayout.add(toEmail);

		TextField fromEmail = new TextField();
		fromEmail.setLabel("From");
		fromEmail.setWidth("50%");
		sendEmailLayout.add(fromEmail);

		EmailField emailSubject = new EmailField();
		emailSubject.setLabel("Subject");
		emailSubject.setWidth("50%");
		sendEmailLayout.add(emailSubject);

		TextArea emailBody = new TextArea();
		emailBody.setLabel("Body");
		emailBody.setMaxLength(charLimit);
		emailBody.setWidth("50%");
		sendEmailLayout.add(emailBody);

		add(sendEmailLayout);

	}

}
