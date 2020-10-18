package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Student;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StudentUtilsTest {

    private List<Student> students;
    private ObjectMapper mapper = new ObjectMapper();
    private static final String JSON_PATH = "src/test/resources/students.json";


    @Before
    public void setUp() throws IOException {
       // TODO using ObjectMapper read the file students.json and Deserialize it to a List of Student Object. Assign the result to the variable students
        students = mapper.readValue(new File(JSON_PATH), new TypeReference<List<Student>>() {});
    }

    @Test
    public void getAllPassingStudents() {
        List<Student> passingStudents = StudentUtils.getAllPassingStudents(students);

        // assertions
        assertEquals(passingStudents.size(), 11);
    }

    @Test
    public void getStudentsWithGender() {
        List<Student> maleStudents = StudentUtils.getStudentsWithGender(students, "male");

        maleStudents.sort(Comparator.comparing((s -> s.getName().getFirstName())));
        // assertions
        assertEquals(maleStudents.size(), 5);
        assertEquals(maleStudents.get(0).getName().getFirstName(), "Jack");
        assertEquals(maleStudents.get(0).getDegree().getCourseTitle(), "Computer Science");
        assertEquals(maleStudents.get(1).getName().getFirstName(), "James");
        assertEquals(maleStudents.get(1).getDegree().getCourseTitle(), "Computer Science");
        assertEquals(maleStudents.get(2).getName().getFirstName(), "Jason");
        assertEquals(maleStudents.get(2).getDegree().getCourseTitle(), "Mechanical Engineering");
        assertEquals(maleStudents.get(3).getName().getFirstName(), "John");
        assertEquals(maleStudents.get(3).getDegree().getCourseTitle(), "Computer Science");
        assertEquals(maleStudents.get(4).getName().getFirstName(), "Martin");
        assertEquals(maleStudents.get(4).getDegree().getCourseTitle(), "Computer Science");
    }

    @Test
    public void getStudentWithDegreeTitleAndType() {
        List<Student> lawMasterStudents = StudentUtils.getStudentWithDegreeTitleAndType(students, "Law", "Masters");

        // assertions
        assertEquals(lawMasterStudents.size(), 1);
        assertEquals(lawMasterStudents.get(0).getName().getFirstName(), "Alice");
        assertEquals(lawMasterStudents.get(0).getName().getLastName(), "Robinson");
        assertEquals(lawMasterStudents.get(0).getDegree().getCourseTitle(), "Law");
        assertEquals(lawMasterStudents.get(0).getDegree().getType(), "Masters");
    }

    @Test
    public void getStudentWithHighestGrade() {
        Student bestStudent = StudentUtils.getStudentWithHighestGrade(students);

        // assertions
        assertEquals(bestStudent.getName().getFirstName(), "Helen");
        assertEquals(bestStudent.getName().getLastName(), "Smith");
        assertEquals(bestStudent.getDegree().getCourseTitle(), "Physics");
        assertEquals(bestStudent.getDegree().getType(), "Bachelors");
        assertEquals(bestStudent.getDemographics().getGender(), "Female");
    }

    @Test
    public void bestStudentForDegreeBasedOnGender() {
        Student student = StudentUtils.bestStudentForDegreeBasedOnGender(students, "Economics", "Masters", "female");

        // assertions
        assertEquals(student.getDegree().getCourseTitle(), "Economics");
        assertEquals(student.getDegree().getType(), "Masters");
        assertEquals(student.getDemographics().getGender(), "Female");
        assertEquals(student.getName().getFirstName(), "Jennifer");
        assertEquals(student.getName().getLastName(), "Green");
    }
}