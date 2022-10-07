package com.group21.jobTracker.Api;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.group21.jobTracker.backend.data.Jobs;
/**
 * Class that represents the Api calls to LinkedIn and OneCareerStop for the "Application Search"
 * feature of JobTracker. 
 */
public class ApiCalls {
	/** Constant for the number of jobs pulled by the Search functions */
	private static int NUM_JOBS = 5;

	/**
	 * Gets a job object from a link to linkedin
	 * @param url the link to the linkedin job posting
	 * @return a job object of the job posting
	 * @throws IOException if the linkedin url cannot be reached
	 */
	public static Jobs linkedInSelectJob(String url) throws IOException {
		if (!url.contains("linkedin.com/jobs/view/")) {
			throw new IllegalArgumentException("Invalid URL");
		}
		Document doc = Jsoup.connect(url).get();
		String title = doc.select("h1.top-card-layout__title").text();
		String company = doc.select("a.topcard__org-name-link").text();
		Jobs job = new Jobs();
		job.setJobTitle(title);
		job.setCompany(company);
		job.setJobDescription(url);
		job.setLink(url);
		return job;
	}
	
	/**
	 * Searches for a list of jobs in linkedin
	 * @param keywords keywords used for the search
	 * @return a list of available jobs
	 * @throws IOException if the linked in website cannot be reached
	 */
	public static ArrayList<Jobs> linkedInJobSearch(String keywords) throws IOException {
		try {
		String url;
		if(keywords == null){
			url = "https://www.linkedin.com/jobs/search";
		} else {
			url = "https://www.linkedin.com/jobs/search?" + "keywords=" + keywords.replaceAll(" ", "%20");
		}
		Document doc = Jsoup.connect(url).get();
		Elements links = doc.select("a.base-card__full-link");
		ArrayList<Jobs> results = new ArrayList<Jobs>();
		for (Element link : links) {
			if (results.size() == NUM_JOBS) {
				break;
			}
			results.add(linkedInSelectJob(link.attr("href")));
			Thread.sleep( 200 ); // IMPORTANT
			//Prevents error from Jsoup retrieving too many HTTP requests in succession
		}
		return results;
		} catch (Exception e) {
			throw new IllegalArgumentException("Error retrieving Job Information");
		}
	}

	/**
	 * Gets a job object from a link to CareerOneStop
	 * @param url the link to the CareerOneStop job posting
	 * @return a job object of the job posting
	 * @throws IOException if the CareerOneStop url cannot be reached
	 */
	public static Jobs careerOneStopSelectJob(String url) throws IOException {
		if (!url.contains("careeronestop.org/Toolkit/Jobs/")) {
			throw new IllegalArgumentException("Invalid URL");
		}
		Document doc = Jsoup.connect(url).get();
		String title = doc.select("#ctl17_lblJobTitle").text();
		String company = doc.select("#ctl17_lblCompany").text();
		String desc = doc.select("#ctl17_lblDesc p").text();
		Jobs job = new Jobs();
		job.setJobTitle(title);
		job.setCompany(company);
		job.setJobDescription(desc);
		return job;
	}
	
	/**
	 * Searches for a list of jobs in CareerOneStop
	 * @param keywords keywords used for the search
	 * @return a list of available jobs
	 * @throws IOException if the CareerOneStop website cannot be reached
	 */
	public static ArrayList<Jobs> careerOneStopJobSearch(String keywords) throws IOException {
		String url = "https://www.careeronestop.org/Toolkit/Jobs/find-jobs-results.aspx?" + "keyword=" + keywords.replaceAll(" ", "%20");
		Document doc = Jsoup.connect(url).get();
		Elements links = doc.select(".cos-table-responsive tbody tr a");
		ArrayList<Jobs> results = new ArrayList<Jobs>();
		for (Element link : links) {
			if (results.size() == NUM_JOBS) {
				break;
			}
			results.add(careerOneStopSelectJob("https://www.careeronestop.org" + link.attr("href")));
		}
		return results;
	}
	
}

