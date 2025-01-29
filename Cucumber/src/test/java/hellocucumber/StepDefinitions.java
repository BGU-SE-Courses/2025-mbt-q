package hellocucumber;

import io.cucumber.java.en.*;

public class StepDefinitions {

    //    private static List<OpenMyCoursesPageActuator> allOpenSessions;
    private static OpenStudentSessionActuator openStudentSession;
    private static OpenTeacherSessionActuator openTeacherSession;
    //    private static List<OpenCartActuatorTeacher> allopenCartsA;
    private String webDriver = "webdriver.chrome.driver";
    private String path = "../Selenium/chromedriver.exe";

    public void OpenSessionStudent() {
        System.out.println("--------------- INITIALIZING MOODLE TEST - OPENING WEBPAGE ---------------");
//        if (allOpenSessions == null) {
//            allOpenSessions = new ArrayList<>();
//        }
        openStudentSession = new OpenStudentSessionActuator();
//        allopenCarts.add(opencartUser);
        openStudentSession.initSession(webDriver, path);
    }

    public void OpenSessionTeacher() {
        System.out.println("--------------- INITIALIZING MOODLE TEST - OPENING WEBPAGE ---------------");
//        if (allOpenSessions == null) {
//            allOpenSessions = new ArrayList<>();
//        }
        openTeacherSession = new OpenTeacherSessionActuator();
//        allopenCarts.add(opencartUser);
        openTeacherSession.initSession(webDriver, path);
    }

//    public void OpenSessionTeacher() {
//        System.out.println("--------------- INITIALIZING MOODLE TEST - OPENING WEBPAGE ---------------");
//        if (allopenCartsA == null) {
//            allopenCartsA = new ArrayList<>();
//        }
//        opencartTeacher = new OpenCartActuatorTeacher();
//        allopenCartsA.add(opencartManager);
//        opencartTeacher.initSessionAsTeacher(webDriver, path);
//    }

    // --------------- STUDENT STEPS ---------------

    // $$*TODO* explain what this step does$$
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

//    @And("the course {string} exists")
//    public void courseExists(String course) {
//    }

//    @And("the student is registered to the course {string}")
//    public void studentRegisteredToCourse(String course) {
//    }

    // $$*TODO* explain what this step does$$
    // This step marks a course as starred:
    // It presses the three dots of the course and then "Star this course"
    @When("the student defined the course {string} as starred")
    public void defineAsStarred(String course) {
        openStudentSession.DefineStaredCourse(course);
    }

    // $$*TODO* explain what this step does$$
    // This steps checks if the starring was successful
    // It is searching for a star next to the course's name
    @Then("the course is starred")
    public void courseIsStarred() {
        openStudentSession.CheckStaredCourse();
    }

    // --------------- TEACHER STEPS ---------------

    // $$*TODO* explain what this step does$$
    @Given("the teacher is logged in to the system with {string} and {string}")
    // This step logs in the teacher by pressing the log in page button, entering
    // teacher username and password in the page and pressing the log in button
    public void teacherIsLoggedIn(String username, String password) {
        OpenSessionTeacher();
        openTeacherSession.GoToLogin();
        openTeacherSession.EnterLoginInfo(username, password);
    }

    /*@And("{string} is defined as a teacher of course {string}")
    public void teacherIsOnMyCoursesPage(String username, String course) {
        openTeacherSession.GoToMyCoursePage();
    }*/

    // $$*TODO* explain what this step does$$
    // This step navigates to the setting page of the course
    // It goes to all courses by pressing on "My Courses" button
    // Searches for the specified course and clicks on it
    // In the course's page, clicks on the "Settings" button
    @And("the teacher is on {string} setting")
    public void goToSettings(String course) {
        openTeacherSession.GoToMyCoursesPage();
        openTeacherSession.goToSettings(course);
    }

    // $$*TODO* explain what this step does$$
    // This step hides a course from students
    // In the setting page, scrolls down to hide / show dropdown
    // Clicks and the dropdown and chooses the "Hide" option
    // Scrolls to the bottom of the page and clicks "Save"
    @When("the teacher deletes the course")
    public void deleteCourse() {
        openTeacherSession.deleteCourse();
    }

    // $$*TODO* explain what this step does$$
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