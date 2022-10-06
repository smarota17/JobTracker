[![DOI](https://zenodo.org/badge/535341071.svg)](https://zenodo.org/badge/latestdoi/535341071)
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

JobTracker is a Java Application that helps users keep track of job applications for internship and full time positions!

https://user-images.githubusercontent.com/70153150/194409299-52adb84c-05f5-48d2-a237-0f302becfe2b.mp4


## Description

JobTracker is a personal Job board to add and track job applications in all their stages. This application allows users to provide information about jobs they have applied to, easily filter through applications and assign priorities to certain applications. JobTracker can also send reminder emails about applications with upcoming deadlines. Users can also use JobTracker's "Application Search" feature which allows users to search for job postings on LinkedIn and CareerOneStop within the program! The user can limit their search using keywords and JobTracker will display a list of associated jobs. The user can navigate to the original job posting, and easily add information about the application into JobTracker's system. 

To start using JobTracker, a user must create an account. The system collects information about name, email, gender, age, experience, and keywords for the system to use when searching through LinkedIn and CareerOneStop. All of this information is stored locally in a CSV file, and is not used by JobTracker in any other way.

## Technologies
<p align="left">
  <a href="https://www.java.com/en/" target="_blank"> 
    <img src="https://github.com/smarota17/JobTracker/blob/main/resources/java_logo.png" alt="java" width="60" height="60"/>
  </a>
  <a href="https://vaadin.com/" target="_blank">
    <img src="https://upload.wikimedia.org/wikipedia/commons/e/e0/Vaadin-logo.svg" alt="react" width="150" height="60"/>
  </a>
  <a href="https://spring.io/projects/spring-boot" target="_blank"> 
    <img src="https://spring.io/images/favicon-9d25009f65637a49ac8d91eb1cf7b75e.ico" alt="spring" width="60" height="60"/>
  </a>
  <a href="https://maven.apache.org/" target="_blank"> 
    <img src="https://idroot.us/wp-content/uploads/2019/12/Apache-Maven-logo.png" alt="js" width="120" height="90"/>
  </a>
</p> 

* Java 11
* Vaadin 23.2.2
* Spring boot 2.7.3
* Apache Maven 4.0.0


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
1. Open Eclipse IDE. Install [here](https://www.eclipse.org/downloads/packages/release/oxygen/3a/eclipse-ide-java-developers) if not already installed.
2. Clone the project from GitHub Repo 
   - Cloning using EGit: 
        Click On File > Import > Git > Projects From Git > Clone Uri > Paste HTTPS Repository Url > Select J2EE Branch > Next > Next > Finish 
   - Cloning using Git Bash:  
        git clone https://github.com/smarota17/JobTracker.git 
        Click on File > Import > Existing Maven Project > Select Local Directory > Finish 
3. Right click on the Project Directory > Maven > Update Project 
4. Right Click on the Project Directory > Run As > Maven Clean \
5. Right Click on the Project Directory > Run As > Maven Build \
      If the run configuration doesn't appear, use the following steps:
      - Right Click on the Project Directory > Run As > Run Configuration > Maven Build > Main > \
      - select Base directory as "${project_loc:jobTracker}" \
      - select Goals "spring-boot:run" 
6. Navigate to localhost:8080 in your browser.  

Note: In order to use the "Send Mail" functionality for this project, your team must set up an **Outlook** email account, and add the username and password to the SendMail.java class. You cannot use Gmail for this feature because Google set up a new restriction this year that doesn't allow third-party apps to send emails from Gmail accounts. 

### Visual Representations


## Future Features

While JobTracker is ready for users, there are several enhancements that could be made to amplify user experience. Below, we have listed several scopes of future improvements to JobTracker with a brief description. 

* **Database integration:** Use a database system to persist data for each user. Currently, JobTracker saves all data in a CSV file (one for each user). 
* **Deployment server:** Use Tomcat, Heroku, AWS, or a different cloud server for deployment to improve usability and make maintenance easier when fixing issues.
* **Chart-based analysis:** Introduce a quantitative or qualitative analysis for comparing jobs based on different charts. For example, a user could create a bar chart to compare jobs they have added based on salary insights.
* **Chrome extension:** Create a Chrome extension for JobTracker so users can add applications to the system from their browser. 
* **Link to other employment sites:** Consider using other employment services such as Indeed and Glassdor to generate the list of jobs on the "Application Search" page. Currently, JobTracker is only connected to LinkedIn and CareerOneStop. 
* **Chatbot:** Implement Chatbot capability that allows users to communicate effortlessly with the bot and learn about various elements of JobTracker.
* **SMS notifications:** Implement SMS notifications to supplement the email notification feature when reminding users of upcoming deadlines. 

## Contribution

Please see the CONTRIBUTING.md for instructions on how to contribute to our repository.

## License

This project is licensed under the Apache License 2.0.
