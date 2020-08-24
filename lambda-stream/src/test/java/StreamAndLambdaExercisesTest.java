import domain.Name;
import domain.Student;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class StreamAndLambdaExercisesTest {

    private List<Student> students = new ArrayList<>();

    @Before
    public void init() {
        students = Arrays.asList(
                new Student(18, new Name("Ken", "Adams")),
                new Student(22, new Name("Jack", "Son")),
                new Student(20, new Name("James", "King")));
    }

    @Test
    public void studentOver20Test() {

        List<Student> matchedStudents = new ArrayList<>();

        /**
         * TODO: Loop through the students List and store in matchedStudents the Students that are over 20
         * First try using a For each loop
         * Second try using Stream API
         **/

        assertEquals(matchedStudents.size(), 1);
        assertEquals(matchedStudents.get(0).getName().getFirstName(), "Jack");
    }

    @Test
    public void sortStudentsByNameTest() {
        List<Student> sortedStudents = new ArrayList<>();
        /**
         * TODO: Sort (using Stream and a chain of stream operations) the students List based on the fistName of the Student.
         * Collect the sorted List into sortedStudents
         **/

        assertEquals(sortedStudents.get(0).getName().getFirstName(), "Jack");
        assertEquals(sortedStudents.get(1).getName().getFirstName(), "James");
        assertEquals(sortedStudents.get(2).getName().getFirstName(), "Ken");
    }

    @Test
    public void sortStudentsByNameWithJTest() {
        List<Student> sortedStudentsStartingWithJ = new ArrayList<>();
        /**
         * TODO: Sort (using Stream and a chain of stream operations) the students whose name starts with the letter "J" based on the fistName of the Student.
         * Collect the sorted List into sortedStudentsStartingWithJ
         **/

        assertEquals(sortedStudentsStartingWithJ.size(), 2);
        assertEquals(sortedStudentsStartingWithJ.get(0).getName().getFirstName(), "Jack");
        assertEquals(sortedStudentsStartingWithJ.get(1).getName().getFirstName(), "James");
    }

    @Test
    public void studentByName() {
        /**
         * TODO: Write some assertions for firstStudentWithJ based on what you think the Object holds
         **/

        Optional<Student> firstStudentWithJ = students.stream()
                .sorted(Comparator.comparing(student -> student.getName().getFirstName()))
                .filter(student -> student.getName().getFirstName().startsWith("J"))
                .findFirst();

    }

}
