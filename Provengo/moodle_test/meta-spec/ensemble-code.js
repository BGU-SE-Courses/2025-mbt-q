// @provengo summon ctrl

/**
 * List of events "of interest" that we want test suites to cover.
 */
/**
 * Goals for Domain-Specific criterion - We want to test the edge case where the course is hidden by the teacher
 * after the student loads their courses, but before the student stars the course, so that we can check how the case
 * is handled by the system.
 */
const GOALS = [
    Ctrl.markEvent("MyCoursesHideStar")
];


/**
 * Goals for Two-Way criterion - We want to test the combination in different order of each two actions in the system.
 */

// let studentEvents = ['EndLoginStudent', 'WentMyCourses', 'EndStarCourse']
// let teacherEvents = ['EndLoginTeacher', 'TeacherWentToMyCourses', 'TeacherWentToCourse', 'EndHideCourse']

// const GOALS = [];
// studentEvents.forEach(event1 => {
//   teacherEvents.forEach(event2 => {
//     GOALS.push(Ctrl.markEvent(`${event1}${event2}`));
//     if(!((event1 === 'WentMyCourses' || event1 === 'EndLoginStudent') && (event2 === 'TeacherWentToCourse' || event2 === 'EndHideCourse'))){
//         GOALS.push(Ctrl.markEvent(`${event2}${event1}`));
//     }
//   })
// })

/**
 * Ranks test suites by how many events from the GOALS array were met.
 * The more goals are met, the higher the score.
 * 
 * It make no difference if a goal was met more then once.
 *
 * @param {Event[][]} ensemble The test suite to be ranked.
 * @returns Number of events from GOALS that have been met.
 */
function rankByMetGoals(ensemble) {
    const unreachedGoals = [];
    for (let idx = 0; idx < GOALS.length; idx++) {
        unreachedGoals.push(GOALS[idx]);
    }

    for (let testIdx = 0; testIdx < ensemble.length; testIdx++) {
        let test = ensemble[testIdx];
        for (let eventIdx = 0; eventIdx < test.length; eventIdx++) {
            let event = test[eventIdx];
            for (let ugIdx = unreachedGoals.length - 1; ugIdx >= 0; ugIdx--) {
                let unreachedGoal = unreachedGoals[ugIdx];
                if (unreachedGoal.contains(event)) {
                    unreachedGoals.splice(ugIdx, 1);
                }
            }
        }
    }

    return GOALS.length - unreachedGoals.length;
}

/**
 * Ranks a single test by how many events from the GOALS array were met.
 * The more goals are met, the higher the score.
 * 
 * It make no difference if a goal was met more then once.
 *
 * @param {Event[]} test The test suite to be ranked.
 * @returns Number of events from GOALS that have been met.
 */

function rankSingleTestByMetGoals(test) {
    const unreachedGoals = [];
    for (let idx = 0; idx < GOALS.length; idx++) {
        unreachedGoals.push(GOALS[idx]);
    }
    for (let eventIdx = 0; eventIdx < test.length; eventIdx++) {
        let event = test[eventIdx];
        for (let ugIdx = unreachedGoals.length - 1; ugIdx >= 0; ugIdx--) {
            let unreachedGoal = unreachedGoals[ugIdx];
            if (unreachedGoal.contains(event)) {
                unreachedGoals.splice(ugIdx, 1);
            }
        }
    }

    return GOALS.length - unreachedGoals.length;
}


/**
 * Ranks potential test suites based on the percentage of goals they cover.
 * Goal events are defined in the GOALS array above. An ensemble with rank
 * 100 covers all the goal events.
 *
 * Multiple ranking functions are supported - to change ranking function,
 * use the `ensemble.ranking-function` configuration key, or the 
 * --ranking-function <functionName> command-line parameter.
 *
 * @param {Event[][]} ensemble the test suite/ensemble to be ranked
 * @returns the percentage of goals covered by `ensemble`.
 */
// for Two-Way criterion
// function rankingFunction(ensemble) {

//     // How many goals did `ensemble` hit?
//     const metGoalsCount = rankByMetGoals(ensemble);
//     // What percentage of the goals did `ensemble` cover?
//     const metGoalsPercent = metGoalsCount / GOALS.length;

//     return metGoalsPercent * 100; // convert to human-readable percentage
// }


/**
 * Ranks potential test suites based on the percentage of goals each test covers.
 * Goal events are defined in the GOALS array above. An ensemble with rank
 * 100 covers all the goal events in each test.
 *
 * @param {Event[][]} ensemble the test suite/ensemble to be ranked
 * @returns the percentage of goals covered by `ensemble`.
 */
//For Domain-Specific criterion
function rankingFunction(ensemble) {
    let sum = 0;
    let amount = 0;
    for(let testIdx = 0; testIdx < ensemble.length; testIdx++){
        let test = ensemble[testIdx]
        let metGoalsCount = rankSingleTestByMetGoals(test);
        let metGoalsPercent = metGoalsCount / GOALS.length;
        sum += metGoalsPercent;
        amount++;
    }
    const percentageAverage = sum / amount; // calculate percentage as the average of the percentage for each test
    return percentageAverage * 100; // convert to human-readable percentage
}

