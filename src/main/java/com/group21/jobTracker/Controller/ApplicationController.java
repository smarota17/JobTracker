package com.group21.jobTracker.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.group21.jobTracker.backend.DataService;
import com.group21.jobTracker.backend.data.Jobs;

@Controller
public class ApplicationController{
	
	@Autowired
	DataService dbService;
	
	@GetMapping("/")
	public String WelcomePage() {
		return "index";
	}
	
private String jobTitle;
    
    //@NotNull(message="company must not be null")
    private String company;
    
    //@NotNull(message="dateApplied must not be null")
	private Date dateApplied;
    
	private String nextAction;
	private String status;
	private int priority;
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addJob(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		
		String jobTitle = request.getParameter("jobTitle");
		String company = request.getParameter("company");
		String dateApplied = request.getParameter("date");
		String status = request.getParameter("status");
		
		Jobs job = new Jobs();
		job.setJobTitle(jobTitle);
		job.setCompany(company);
		job.setDateApplied(new SimpleDateFormat("dd/MM/yyyy").parse(dateApplied));
		job.setStaus(status);
		
		dbService.updateJob(job);
		return "add";
	}
	
}
