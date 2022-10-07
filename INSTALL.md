# Installation

This document covers the steps necessary to download and run.

## Prerequisites

You will need the following programs and packages installed on your local machine.

Programs:

* Java Development Kit
* Git
* Maven or Eclipse

### Setup Option 1: Command Line (Requires Maven)
1. Clone the repository to your local machine
2. Navigate to /JobTracker and run "mvn" on the commandline
3. Navigate to "localhost:8080" in your browser.
3. Press Ctr+c in the terminal to close the project

### Setup Option 2: Eclipse (Requires Eclipse)
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
6. Navigate to "localhost:8080" in your browser.  

Note: In order to use the "Send Mail" functionality for this project, your team must set up an **Outlook** email account, and add the username and password to the SendMail.java class. You cannot use Gmail for this feature because Google set up a new restriction this year that doesn't allow third-party apps to send emails from Gmail accounts. 
