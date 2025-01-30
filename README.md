# Software Quality Engineering - System Testing

This is a repository for the system-testing assignment of the Software Quality Engineering course at the [Ben-Gurion University](https://in.bgu.ac.il/), Israel.

## Assignment Description

In this assignment, we tested an open-source software called Moodle(https://sandbox.moodledemo.net/).

Moodle is a software used by universities to manage courses, for both teachers and students.
Some of the features it provides are adding teachers and students to courses, uploading class material, opening quizes and assignments, and more.

## Installation

1. We installed Moodle locally according to the instructions in "/SUT/README.md".
   - We defined an admin user with username :"admin" and password: "Sandbox24\*"
2. After installing Moodle, we ran the "start Moodle.exe" file and opened "http://localhost" to open Moodle.
3. We prepared the testing environment by adding to following data to Moodle:
   - Created teacher user with username :"teacher" and password: "teacherQ$A1234\*"
   - Created student user with username :"student" and password: "studentQ$A1234\*"
   - Created a course called "Course 1"
   - Added the teacher user with the role of "Teacher" and to student with a role of "Student" to "Course 1".
4. To ran the tests, we started the Selenium server according to the instructions in "/Selenium/README.md".

## What we tested

We tested the starring a course module that allows students (and teachers) to star a course to mark as their favorite. We chose to test the following user stories:

1.

- User story: A student defined a course called "Course 1" as starred
- Preconditions: The student is logged in to Moodle, The course "Course 1" exists in the system and the student is registered to it.
- Expected outcome: The course is starred (a star is shown next to the course name in "My Courses" page).

2.

- User story: A teacher hides from student a course called "Course 1" as starred
- Preconditions: The teacher is logged in to Moodle, The course "Course 1" exists in the system and the teacher is registered to it.
- Expected outcome: The course is hidden from students (A "hidden from students" label for the teacher in "My Courses" page and the course is not shown to student in "My Courses" page).

## How we tested

We used two different testing methods:

1. [Cucumber](https://cucumber.io/), a behavior-driven testing framework.
2. [Provengo](https://provengo.tech/), a story-based testing framework.

Each of the testing methods is elaborated in its own directory.

## Results

Update all README.md files (except for d-e, see Section 1). Specifically, replace all $$*TODO*…$$ according to the instructions inside the $$.

## Detected Bugs

We detected the following bugs:

1. Bug 1:
   1. General description: A student can star a course that was hidden by the teacher in some cases
   2. Steps to reproduce: 
      a. Student goes to "My Courses" and loads the courses
      b. Teacher hides the course
      c. Student stars the course
   3. Expected result: Course is hidden from the student and they are unable to perform actions on it
   4. Actual result: Course is still visible to the student and they can star it
