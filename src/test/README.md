# Importing and Running Job Tracker Through Eclipse IDE

Step 0: Open Eclipse IDE. [Install if not already installed]

Step 1: Clone the project from GitHub Repo \
        Cloning using EGit

        Click On File > Import > Git > Projects From Git > Clone Uri > Paste The Repository Url as: https://github.com/smarota17/JobTracker.git > Select J2EE Branch > Next > Next > Finish 

        Using Git Bash 
        git clone https://github.com/smarota17/JobTracker.git 
        Click on File > Import > Existing Maven Project > Select Local Directory > Finish 

Step 2: Right Click on the Project Directory > Maven > Update Project 

Step 3: Right Click on the Project Directory > Run As > Maven Clean \
        Right Click on the Project Directory > Run As > Maven Build [Might show Run Configuration if not then go down] \
        Right Click on the Project Directory > Run As > Run Configuration > Maven Build > Main > \
        select Base directory as "${project_loc:jobTracker}" \
        select Goals "spring-boot:run" 

Step 4: Check Running the site at http://127.0.0.1:8080 \ 

Step 5: As you don't have any account on the site, click for sign up \
        The registration will need Full name, email id, gender, age and experience in the job field. \
        You can also set Job keywords during the registration. \
        Your user acount will be created. 

Step 6: After completing the the registration, You can use user credentials to login into the site.

        

