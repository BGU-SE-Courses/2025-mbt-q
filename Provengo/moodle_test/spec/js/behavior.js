// @provengo summon selenium
const studentSession = new SeleniumSession("student");
const teacherSession = new SeleniumSession("teacher");

//Pre-Testing instructions:
//Create student user with username "student" and password "studentQ$A1234"
//Create teacher user with username "teacher" and password "teacherQ$A1234"
//Create course and give "teacher" Teacher permissions and "student" Student permissions in the course
//Log in as the teacher and click "got it" on all info boxes



/**
 * This story opens a new browser window, logins as a student, goes to "my courses", and stars Course 1.
 */
bthread('Student Star', function () {
  studentSession.start(URL)
  login(studentSession, {username: 'student', password: 'studentQ$A1234'})
  //sync({request: Ctrl.markEvent('EndLoginStudent')}) //for two way
  goToMyCourses(studentSession)
  sync({request: Ctrl.markEvent('WentMyCourses')}) //for synchronization
  starCourse(studentSession)
  //sync({request: Ctrl.markEvent('EndStarCourse')}) //for two way and domain specific
  sync({ request: bp.Event(`StudentEnd`, {}) })
})

/**
 * This story opens a new browser window, logins as a teacher, goes to "my courses", enters Course 1, goes to settings and hides the course from view.
 */
bthread('Teacher Hide', function () {
  teacherSession.start(URL)
  login(teacherSession, {username: 'teacher', password: 'teacherQ$A1234'})
  //sync({request: Ctrl.markEvent('EndLoginTeacher')}) //for two way
  goToMyCourses(teacherSession)
  //sync({request: Ctrl.markEvent('TeacherWentToMyCourses')}) //for two way
  goToCourse(teacherSession)
  sync({request: Ctrl.markEvent('TeacherWentToCourse')}) //for synchronization
  hideCourse(teacherSession)
  //sync({request: Ctrl.markEvent('EndHideCourse')}) //for two way and domain specific
  sync({ request: bp.Event(`TeacherEnd`, {}) })
})

/**
 * This bthread undoes the actions performed in the test, allowing to run multiple tests one after the other
 */
bthread('AfterEach', function () {
  waitForAll(
    bp.Event(`TeacherEnd`, {}),
    bp.Event(`StudentEnd`, {})
  )
  sync({ request: bp.Event(`AfterEachStart`, {}) })
  showCourse(teacherSession)
  unstarCourse(studentSession)
})

/**
 * This bthread prevents the student from loading their courses after the teacher hides the course
 */
bthread('Correction', function () {
  sync({
    waitFor: Ctrl.markEvent('WentMyCourses'),
    block: Ctrl.markEvent('TeacherWentToCourse')
  })
})


/**
 * This bthread is used for the domain-specific criterion, to mark tests where the student stars the course after it was hidden
 */
/*
bthread('Mark WentMyCourses-HideCourse-StarCourse', function () {
  sync({ waitFor: Ctrl.markEvent('WentMyCourses')})
  sync({ waitFor: Ctrl.markEvent('EndHideCourse')})
  sync({ waitFor: Ctrl.markEvent('EndStarCourse')})

  sync({ request: Ctrl.markEvent('MyCoursesHideStar')})
})
*/

// creation of event pairs for two-way criterion
/*
let studentEvents = ['EndLoginStudent', 'WentMyCourses', 'EndStarCourse']
let teacherEvents = ['EndLoginTeacher', 'TeacherWentToMyCourses', 'TeacherWentToCourse', 'EndHideCourse']

let pairs = [];
studentEvents.forEach(event1 => {
  teacherEvents.forEach(event2 => {
    pairs.push([event1,event2]);
    if(!((event1 === 'WentMyCourses' || event1 === 'EndLoginStudent') && (event2 === 'TeacherWentToCourse' || event2 === 'EndHideCourse'))){
      pairs.push([event2,event1]);
    }
  })
})
*/

/**
 * These bthreads are used for the two-way criterion, to mark each possible pairing of two events
 */
/*
pairs.forEach(([event1, event2]) => {
  bthread(`Mark Two-Way ${event1} ${event2}`, function() {
    sync({ waitFor: Ctrl.markEvent(event1)})
    sync({ waitFor: Ctrl.markEvent(event2)})

    sync({ request: Ctrl.markEvent(`${event1}${event2}`)})
  })
})
*/