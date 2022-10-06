[![License](https://img.shields.io/github/license/smarota17/JobTracker?style=plastic)](https://img.shields.io/github/license/smarota17/JobTracker?style=plastic)
[![java](https://img.shields.io/badge/Made%20with-Java-brightgreen?style=plastic)](https://img.shields.io/badge/Made%20with-Java-brightgreen?style=plastic)
[![size](https://img.shields.io/badge/repo%20size-2.14%20MB-brightgreen?style=plastic)](https://img.shields.io/badge/repo%20size-2.14%20MB-brightgreen?style=plastic)
[![lang](https://img.shields.io/badge/languages-4-blue?style=plastic)](https://img.shields.io/badge/languages-4-brightgreen?style=plastic)
[![contrib](https://img.shields.io/badge/contributors-5-blue?style=plastic)](https://img.shields.io/badge/contributors-5-brightgreen?style=plastic)
[![issue op](https://img.shields.io/badge/issues-14%20open-yellow?style=plastic)](https://img.shields.io/badge/issues-14%20open-brightgreen?style=plastic)
[![issue cl](https://img.shields.io/badge/issues-19%20closed-yellow?style=plastic)](https://img.shields.io/badge/issues-19%20closed-brightgreen?style=plastic)
[![pull](https://img.shields.io/badge/pull%20requests-19%20closed-yellow?style=plastic)](https://img.shields.io/badge/pull%20requests-19%20closed-brightgreen?style=plastic)

# JobTracker
<p align="center"><img width="1000" src="https://github.com/smarota17/JobTracker/blob/job-controller/resources/the_job_tracker.jpg"></p>

Job Tracker is a Java Application that keeps track records of Users’ job applications.

## Description


Job Tracker is a personal Job board to add and track job applications in all their stages. This web application includes adding any new job posting on LinkedIn and CareerOneStop by using API calls and putting company name, Job title, Job Type, and other detailed information into the System. By using the general Job Board, one user can get to know the most recent job posts according to the user preferences. 

When one user Setup their account, the system collects information about name, email age, etc. as personal information including the preference for job postings through the list of keywords. This web application has an email feature with which one user can get an email notification about his/her applied jobs.

## Features
* Send reminder emails for upcoming dealines.
* APIs from supported platforms:  Google careers, Indeed and Linkedin.
* Dashboard for notifying upcoming deadlines
* Filtering applications on searching page
* Personal application page lists current applications, and has past applications/priority/date as filters
* Personal application page enables users to create/edit/delete an application and compare salaries among applications.

## Installation

You will need the following programs and packages installed on your local machine.

Programs:

* Java Development Kit
* Git

Installation can be accomplished using Eclipse IDE.

### Setup
1. Open Eclipse IDE. Install here[https://www.eclipse.org/downloads/packages/release/oxygen/3a/eclipse-ide-java-developers] if not already installed.
2. Clone the project from GitHub Repo \
   - Cloning using EGit: 
        Click On File > Import > Git > Projects From Git > Clone Uri > Paste The Repository Url as: https://github.com/smarota17/JobTracker.git > Select J2EE Branch > Next > Next > Finish 
   - Cloning using Git Bash:  
        git clone https://github.com/smarota17/JobTracker.git 
        Click on File > Import > Existing Maven Project > Select Local Directory > Finish 
3. Right click on the Project Directory > Maven > Update Project 
4. Right Click on the Project Directory > Run As > Maven Clean \
   Right Click on the Project Directory > Run As > Maven Build \
      If the run configuration doesn't appear, use the following steps:
      - Right Click on the Project Directory > Run As > Run Configuration > Maven Build > Main > \
      - select Base directory as "${project_loc:jobTracker}" \
      - select Goals "spring-boot:run" 
5. Navigate to localhost:8080 in your browser.  

Note: In order to use the "Send Mail" functionality for this project, your team must set up an _Outlook_ account, and add the username and password to the SendMail.java class. You cannot use Gmail for this feature because Google set up a new restriction this year that doesn't allow third-party apps to send emails from Gmail accounts. 

### Visual Representations

## Technologies we used
<p align="left">
  <a href="https://www.java.com/en/" target="_blank"> 
    <img src="https://github.com/smarota17/JobTracker/blob/main/resources/java_logo.png" alt="java" width="40" height="40"/>
  </a>
  <a href="https://vaadin.com/" target="_blank">
    <img src="https://upload.wikimedia.org/wikipedia/commons/e/e0/Vaadin-logo.svg" alt="react" width="40" height="40"/>
  </a>
  <a href="https://spring.io/projects/spring-boot" target="_blank"> 
    <img src="https://spring.io/images/favicon-9d25009f65637a49ac8d91eb1cf7b75e.ico" alt="spring" width="40" height="40"/>
  </a>
  <a href="https://maven.apache.org/" target="_blank"> 
    <img src="https://idroot.us/wp-content/uploads/2019/12/Apache-Maven-logo.png" alt="js" width="40" height="40"/>
  </a>
</p> 

* Java 11
* Vaadin 23.2.2
* Spring boot 2.7.3
* Apache Maven 4.0.0

## Future Features

This application covers the functionalities like searching for new jobs based on the keywords provided by the User. It also maintains a User Dashboard, Application Dashboard, and a process of saving jobs for Users. This App could also be used for tracking the applied jobs in different companies with a feature of email notification. 

However, there is significant scope for Job Tracker to improve in the future. Here some of the scopes are defined with a brief description. 
* Database integration \
Currently, the Job Tracker application is not using any Database System. Instead of a database, all the information is stored in CSV files where each user will have a separate CSV file to keep track of all the Jobs applied or saved for future consideration.
* Deployment Server \
In the future, Job Tracker can use Tomcat, Heroku, AWS, or any cloud server for deployment to make maintenance easier in terms of using and fixing issues.
* Chart-based analysis \
Another future scope is to introduce a quantitative or qualitative analysis for comparing jobs based on different charts. For example, One User can create a bar chart to compare jobs added by the user based on salary insights.
* Chrome extension \
Creating a Chrome extension for Job Tracker would be another scope of development for consideration.
* Link to other Job sites \
Currently, the application is only connected to LinkedIn, but we can also consider Indeed, CareerOneStop, Glassdoor, etc. to know a variety of Jobs within a single platform.
* Chatbot \
Job Tracker could have Chatbot capability allowing users to effortlessly communicate with the bot and learn about various elements of our online application like improving resumes for based on Job specifications etc. 




## Contribution

Please see the CONTRIBUTING.md for instructions on how to contribute to our repository.

## License

This project is licensed under the Apache License 2.0.
