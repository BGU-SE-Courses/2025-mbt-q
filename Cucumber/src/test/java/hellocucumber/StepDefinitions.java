package hellocucumber;

import io.cucumber.java.en.*;

public class StepDefinitions {

    private static OpenStudentSessionActuator openStudentSession;
    private static OpenTeacherSessionActuator openTeacherSession;
    private String webDriver = "webdriver.chrome.driver";
    private String path = "../Selenium/chromedriver.exe";

    public void OpenSessionStudent() {
        System.out.println("--------------- INITIALIZING MOODLE TEST - OPENING WEBPAGE ---------------");
        openStudentSession = new OpenStudentSessionActuator();
        openStudentSession.initSession(webDriver, path);
    }

    public void OpenSessionTeacher() {
        System.out.println("--------------- INITIALIZING MOODLE TEST - OPENING WEBPAGE ---------------");
        openTeacherSession = new OpenTeacherSessionActuator();
        openTeacherSession.initSession(webDriver, path);
    }

    // --------------- STUDENT STEPS ---------------

    // This step logs in the student by pressing the log in page button, entering
    // student username and password in the page and pressing the log in button
    @Given("the student is logged in to the system with {string} and {string}")
    public void studentIsLoggedIn(String username, String password) {
        OpenSessionStudent();
        openStudentSession.GoToLogin();
        openStudentSession.EnterLoginInfo(username, password);
    }

    // This step navigates the student to the "My Courses" page by pressing the "My Courses" button
    @And("the student is on My Courses page")
    public void studentIsOnMyCoursesPage() {
        openStudentSession.GoToMyCoursesPage();
    }

    // This step marks a course as starred:
    // It presses the three dots of the course and then "Star this course"
    @When("the student defined the course {string} as starred")
    public void defineAsStarred(String course) {
        openStudentSession.DefineStaredCourse(course);
    }

    // This steps checks if the starring was successful
    // It is searching for a star next to the course's name
    @Then("the course is starred")
    public void courseIsStarred() {
        openStudentSession.CheckStaredCourse();
    }

    // --------------- TEACHER STEPS ---------------

    // This step logs in the teacher by pressing the log in page button, entering
    // teacher username and password in the page and pressing the log in button
    @Given("the teacher is logged in to the system with {string} and {string}")
    public void teacherIsLoggedIn(String username, String password) {
        OpenSessionTeacher();
        openTeacherSession.GoToLogin();
        openTeacherSession.EnterLoginInfo(username, password);
    }

    // This step navigates to the setting page of the course
    // It goes to all courses by pressing on "My Courses" button
    // Searches for the specified course and clicks on it
    // In the course's page, clicks on the "Settings" button
    @And("the teacher is on {string} setting")
    public void goToSettings(String course) {
        openTeacherSession.GoToMyCoursesPage();
        openTeacherSession.goToSettings(course);
    }

    // This step hides a course from students
    // In the setting page, scrolls down to hide / show dropdown
    // Clicks and the dropdown and chooses the "Hide" option
    // Scrolls to the bottom of the page and clicks "Save"
    @When("the teacher deletes the course")
    public void deleteCourse() {
        openTeacherSession.deleteCourse();
    }

    // The step checks if a course is hidden from student
    // It navigates the all courses by clicking "My Courses" button
    // Searches for the specified course
    // Checks if "Hidden from students" label is showing next to the course
    @Then("the course {string} is hidden from students")
    public void courseIsHidden(String course) {
        openTeacherSession.GoToMyCoursesPage();
        openTeacherSession.CheckHiddenCourse(course);
    }
}