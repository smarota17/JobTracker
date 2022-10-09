# Usage

This document covers how to use the software and different use cases

## Use Case 1: Registering and logging into a User

1. CLick on the Register User button below the login field
2. Fill out the form with all of the listed infomration and click "Create User"
3. Close the pop-up and your account is made, you can now log in

## Use Case 2: Logging in

1. Fill in your name into the name field.
2. Click "Login".

## Use Case 3: Edit User Profile

1. After loggin in, click on the "My Profile" button.
2. Modify any fields and click the "Save" button to save the user.

## Use Case 4: See Highest Priority Job Applications

1. Log in and stay on the landing page, or click the "Dashboard" button on the left.
2. The center of the page has a list of the 5 highest priority saved jobs (the lower the number, the higher the priority.

## Use Case 5: Add Job Application

1. After logging in, click on the "My Applications" button on the left.
2. Click the "New Application" button on the top right.
3. Fill in all the applicable information about the job application.
4. Scroll down and click "Save" to save the application.

## Use Case 6: Edit Job Applications

1. After logging in, click on the "My Applications" button on the left.
2. Click on an existing job application.
3. On the right, there will be a pop-up with all of the job fields.
4. Modify the job fields to the desired values.
5. Scroll down and click either "Save" to save the changes or "Delete" to delete the application (This deletes the original application, not just the changes).

## Use Case 7: Schedule email reminder for application

**Note:** In order to use the mail functionality for this project, you must set up an Outlook email account and add the username and password to the JobTracker\src\main\java\com\group21\jobTracker\mail\SendMail.java file. You cannot use Gmail for this feature because Google set up a new restriction this year that doesn't allow third-party apps to send emails from Gmail accounts.

1. After logging in, click on the "My Applications" button on the left.
2. Add multiple job applications (Use Case 5).
3. Click on the "Dashboard" tab to return to the dashboard. 
4. Click the "Send Reminder Email" button located under the upcoming deadlines section on the right side. 
5. A reminder email will be sent to the email you specified when creating your account with a list of 5 applications with the closest deadlines. 

## Use Case 8: Search for New Job Applications

1. After logging in, click on the "Application Search" button on the left.
2. A list of 5 job postings from LinkedIn will populate based on your accounts "Keywords" field.
3. Change the search words by clicking on the "Input Search Keywords" text field and enter a new series of space seperated keywords. Click "Search for more applications" to update the list of job postings.
4. Click on the "Go To Job" button to go to the job posting on the LinkedIn website.
5. Click on the "Add Application" button to make a new job application in the system, follow the same method from Use Case 5.
