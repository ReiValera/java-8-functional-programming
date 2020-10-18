package utils;

import domain.Student;
import domain.Subject;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StudentUtils {

    //TODO complete all the following methods. Try to use Stream API for all of them

    private StudentUtils(){
    }

    /**
     * EASY
     * @param students a List of all the Students
     * @param gender the Gender of the Student
     * @return a List of students based on a gender
     */
    public static List<Student> getStudentsWithGender(List<Student> students, String gender) {

        return students.stream()
                .filter(student -> gender.equalsIgnoreCase(student.getDemographics().getGender()))
                .collect(Collectors.toList());
    }

    /**
     * EASY
     * @param students a List of all the Students
     * @param degreeTitle the Degree Title
     * @param degreeType the Degree Type
     * @return a List of students with degreeTitle and degreeType
     */
    public static List<Student> getStudentWithDegreeTitleAndType(List<Student> students, String degreeTitle, String degreeType) {
        return students.stream()
                .filter(student -> degreeTitle.equalsIgnoreCase(student.getDegree().getCourseTitle()))
                .filter(student -> degreeType.equalsIgnoreCase(student.getDegree().getType()))
                .collect(Collectors.toList());
    }

    /**
     * HARD
     * To calculate if the Student has passed. The average grade should be more than 40
     *
     * For each Student add their grades and divide the total by 8.
     * Filter all the Students that meet the condition totalGrade / 8 > 40
     *
     * HINT: You can use mapToInt().sum() Feel free to Google for documentation
     *
     * @param students a List of all the Students
     * @return a List of all passing students
     */
    public static List<Student> getAllPassingStudents(List<Student> students) {

        return students.stream()
                .filter(student -> student.getSubjects()
                        .stream()
                        .mapToInt(Subject::getGrade)
                        .sum() / 8 > 40)
                .collect(Collectors.toList());
    }

    /**
     * HARD
     *
     * First try and get the highestGrade out of all Students
     * Then stream through all Students and calculate their averageGrades
     * Return the first Student whose averageGrade matches the highestGrade
     *
     * @param students a List of all the Students
     * @return the student with the Highest grade
     */
    public static Student getStudentWithHighestGrade(List<Student> students) {

        Optional<Integer> highestGrade = students.stream()
                .map(student -> student.getSubjects()
                        .stream()
                        .mapToInt(Subject::getGrade)
                        .sum() / 8)
                .max(Integer::compareTo);

        Predicate<Student> getStudentGrade = (Student student) -> student.getSubjects()
                .stream()
                .mapToInt(Subject::getGrade)
                .sum() / 8 == highestGrade.get();

        return students.stream()
                .filter(getStudentGrade)
                .findFirst()
                .orElse(null);
    }

    /**
     * EASY
     *
     * Combining the methods above implement the following method
     *
     * @param students a List of all the Students
     * @param degreeTitle the Degree Title
     * @param degreeType the Degree Type
     * @param gender the Gender of the Student
     * @return the best student for a Degree based on gender
     */
    public static Student bestStudentForDegreeBasedOnGender(List<Student> students, String degreeTitle, String degreeType, String gender) {

        List<Student> studentsBasedOnDegree = getStudentWithDegreeTitleAndType(students, degreeTitle, degreeType);
        List<Student> studentsBasedOnGenderForDegree = getStudentsWithGender(studentsBasedOnDegree, gender);

        return getStudentWithHighestGrade(studentsBasedOnGenderForDegree);
    }
}
