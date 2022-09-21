package com.group21.jobTracker.Api;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.group21.jobTracker.backend.data.Jobs;

public class ApiCalls {

	
	public static Jobs linkedInSelectJob(String url) throws IOException {
		if (!url.contains("linkedin.com/jobs/view/")) {
			throw new IllegalArgumentException("TEST");
		}
		Document doc = Jsoup.connect(url).get();
		String title = doc.select("h1.top-card-layout__title").text();
		String company = doc.select("a.topcard__org-name-link").text();
		Jobs job = null; //Initialize with constructor
		return job;
	}
	
	public static ArrayList<Jobs> linkedInJobSearch(String keywords) throws IOException {
		String url = "https://www.linkedin.com/jobs/search?" + "keywords=" + keywords.replaceAll(" ", "%20");
		Document doc = Jsoup.connect(url).get();
		System.out.println(doc.title());
		Elements links = doc.select("a.base-card__full-link");
		ArrayList<Jobs> results = new ArrayList<Jobs>();
		for (Element link : links) {
			System.out.println(link.attr("href"));
			results.add(linkedInSelectJob(link.attr("href")));
		}
		return results;
	}
}

