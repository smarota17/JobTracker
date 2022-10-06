# Black Box Tests for JobTracker
## To run the tests manually:
* Kill the process listening to the 8080 port if there is any.
* In Eclipse:
    Right click on PackSchedulerGUI class in the Package Explorer.
    Select Run As > Java Application > Choose "MyApplication - com.group21.jobTracker"
* In Browser:
    Go to http://localhost:8080/

## Test Description Table
<table width="100%" border=0 align=center>
<tr>
<td align=center><b>Test ID</b></td>
<td align=center><b>Description</b></td>
<td align=center><b>Expected Results</b></td>
<td align=center><b>Actual Results</b> </td>
</tr>

<tr>
<td valign=top>
    T1: Invalid Login
</td>
<td valign=top>
    Preconditions: LoginScreen has loaded

    User Name: invalid user
    Click Login
</td>
<td valign=top>
    There will be a notificaion saying: "No saved data found".
</td>
<td valign=top>
    There is a notificaion saying: "No saved data found".
    <br><br>
    Remain on the LoginScreen page without navigating.
</td>
</tr>

<tr>
<td valign=top>
    T2: Registration
</td>
<td valign=top>
    Preconditions: LoginScreen has loaded

    Click "Don't have an account? Register now!"

    Input registration infomation, in the popped up modal.

        Info: 
            Full Name: Test Name
            Email address: test@gmail.com
            Age: 24
            Year of Experience: 1
            Job Keywords: Software Engineer

    Click "Create User"
</td>
<td valign=top>
    The modal will pop up.
    <br><br>
    After clicking the "Create User", a notification saying "Registeration success! Please go back and log in." will show up.
</td>
<td valign=top>
    The modal pops up successfully.
    <br><br>
    After clicking the "Create User", a notification saying "Registeration success! Please go back and log in." shows up.
</td>
</tr>

<tr>
<td valign=top>
    T3: Invalid Registration
</td>
<td valign=top>
    Preconditions: LoginScreen has loaded

    Click "Don't have an account? Register now!"

    Input registration infomation, in the popped up modal.

        Info: 
            Full Name: Test Name (A name we used before)
            Email address: (whatever)
            Age: (whatever)
            Year of Experience: (whatever)
            Job Keywords: (whatever)

    Click "Create User"
</td>
<td valign=top>
    The modal will pop up.
    <br><br>
    After clicking the "Create User", a notification saying "Registeration failed! User already exists." will show up.
</td>
<td valign=top>
    The modal pops up successfully.
    <br><br>
    After clicking the "Create User", a notification saying "Registeration failed! User already exists." shows up.
</td>
</tr>

<tr>
<td valign=top>
    T4: Valid Login
</td>
<td valign=top>
    Preconditions: LoginScreen has loaded

    User Name: Test Name
    Click Login
</td>
<td valign=top>
    The app will be navigated to the Job Board.
    <br><br>
    There will be a header saying: "Hello, Test Name!"
</td>
<td valign=top>
    The app is navigated to the Job Board.
    <br><br>
    There is a header saying: "Hello, Test Name!"
</td>
</tr>

<tr>
<td valign=top>
    T5: Profile auto populated
</td>
<td valign=top>
    Preconditions: Logged in with a valid username. (Here is "Test Name")

    Click "My profile" in the navigation bar.
</td>
<td valign=top>
    The app will be navigated to the Profile view.
    <br><br>
    The infomation of the user will be auto populated in the Profile view.
</td>
<td valign=top>
    The app is navigated to the Profile view.
    <br><br>
    The following is the auto populated infomation in the Profile view:
        Info: 
            Full Name: Test Name
            Email address: test@gmail.com
            Age: 24
            Year of Experience: 1
            Job Keywords: Software Engineer

</td>
</tr>

<tr>
<td valign=top>
    T6: Application Search
</td>
<td valign=top>
    Preconditions: Logged in with a valid username. (Here is "Test Name")

    Click "Application Search" in the navigation bar.
    Input keyword:"software" in the input field and click the "Search for more applications" button.
    Click the "Go To Job" button.
    Click the "Apply here" button.
    Input the relevant infomation
    Click the "Save" button.
    Go to the "My Application" view by clicking the button in the navigation bar.

</td>
<td valign=top>
    In the "Application Search View", 5 default jobs will show up.<br><br>
    After clicking the "Search for more applications" button. 5 New jobs with the search keywords will show up.
    <br><br>
    After clicking the "Go To Job" button. The job website will be opened in a new tab.
    <br><br>
    After clicking the "Apply here" button. A form with information of the corresponding job will show up.
    <br><br>
    After clicking the "Save" button and navigating to the MyApplication view, a corresponding record will show up.
    <br><br>

</td>
<td valign=top>
    In the "Application Search View", 5 default jobs shows up.<br><br>
    After clicking the "Search for more applications" button. 5 New jobs with the search keywords(here is "software") shows up.
    <br><br>
    After clicking the "Go To Job" button. The job website is opened in a new tab.
    <br><br>
    After clicking the "Apply here" button. A form with information of the corresponding job shows up.(Here is "Software Engineer - Entry Level" @ Lockheed Martin)
    <br><br>
    After clicking the "Save" button and navigating to the MyApplication view, a new record with Job Title = "Software Engineer - Entry Level" and company = "Lockheed Martin" showed up.
    <br><br>
</td>
</tr>

<tr>
<td valign=top>
    T7: My applications
</td>
<td valign=top>
    Preconditions: Logged in with a valid username. (Here is "Test Name")

    Navigate to the My application view by clicking the "My Applications" button in the navigation bar.
    Click the "+ New Application" button.
    Input the informations.
    Click the "Save" button.
    Click on one of the records in the grid.
    Click the "Delete" button.
</td>
<td valign=top>
    The app will be navigated to the "My Applications" view.
    <br><br>
    All the applications of the User will show up.
    <br><br>
    After clicking the "Save" button, a new record will be added.
    <br><br>
    After clicking the "Delete" button, the corresponding record will be removed from the grid.
</td>
<td valign=top>
    The app is navigated to the "My Applications" view.
    <br><br>
    All the applications of the User shows up.
    <br><br>
    After clicking the "Save" button, a new record is added.
    <br><br>
    After clicking the "Delete" button, the corresponding record is removed from the grid.

</td>
</tr>

<tr>
<td valign=top>
    T8: Dashboard
</td>
<td valign=top>
    Preconditions: Logged in with a valid username. (Here is "Test Name")

    Navigate to the Dashboard view by clicking the "Dashboard" button in the navigation bar.
    Click the "+ Send reminder email" button.
</td>
<td valign=top>
    The app will be navigated to the "Dashboard" view.
    <br><br>
    5 default jobs will show up in the gird.
    <br><br>
    After clicking the "+ Send reminder email" button, a reminder email including deadline information will be sent to the User's email(if the email address is valid).
</td>
<td valign=top>
    The app is navigated to the "Dashboard" view.
    <br><br>
    5 default jobs shows up in the gird.
    <br><br>
     After clicking the "+ Send reminder email" button and providing a valid email in registration, a reminder email including deadline information shows up in the mailbox of that email address.

</td>
</tr>

</table>