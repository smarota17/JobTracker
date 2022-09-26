package com.group21.jobTracker.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Jobs> addJob(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		
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
		//HttpHeaders httpHeaders = new HttpHeaders();
		//httpHeaders.add("new job", "/job/" + String.valueOf(job.getId()));
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/updateJob", method = RequestMethod.POST)
	public ResponseEntity<Jobs> updateJobs(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		
		
		String id = request.getParameter("id");
		String next_action = request.getParameter("next action");
		String status = request.getParameter("status");
		Jobs jobToUpdate = dbService.getJobsbyId(Integer.parseInt(id));
		
		jobToUpdate.setNextAction(next_action);
		jobToUpdate.setStaus(status);
		dbService.updateJob(jobToUpdate);
		return new ResponseEntity<>(dbService.getJobsbyId(Integer.parseInt(id)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<Jobs> deleteJobs(HttpServletRequest request, HttpServletResponse response) throws ParseException {
			
		String id = request.getParameter("id");
		dbService.deleteJob(Integer.parseInt(id));
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
