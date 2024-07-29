import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VirtualClassroomManager {
    private Map<String, Classroom> classrooms;
    private Map<String, List<String>> studentAssignments;  // studentId -> list of assignments

    public VirtualClassroomManager() {
        classrooms = new HashMap<>();
        studentAssignments = new HashMap<>();
    }

    public void addClassroom(String name) {
        if (!classrooms.containsKey(name)) {
            classrooms.put(name, new Classroom(name));
            System.out.println("Classroom " + name + " has been created.");
        } else {
            System.out.println("Classroom " + name + " already exists.");
        }
    }

    public void addStudent(String studentId, String className) {
        Classroom classroom = classrooms.get(className);
        if (classroom != null) {
            classroom.addStudent(studentId);
            studentAssignments.putIfAbsent(studentId, new ArrayList<>());
            System.out.println("Student " + studentId + " has been enrolled in " + className + ".");
        } else {
            System.out.println("Classroom " + className + " does not exist.");
        }
    }

    public void scheduleAssignment(String className, String assignment) {
        Classroom classroom = classrooms.get(className);
        if (classroom != null) {
            classroom.addAssignment(assignment);
            System.out.println("Assignment for " + className + " has been scheduled.");
        } else {
            System.out.println("Classroom " + className + " does not exist.");
        }
    }

    public void submitAssignment(String studentId, String className, String assignment) {
        Classroom classroom = classrooms.get(className);
        if (classroom != null && classroom.getStudents().contains(studentId)) {
            studentAssignments.get(studentId).add(assignment);
            System.out.println("Assignment submitted by Student " + studentId + " in " + className + ".");
        } else {
            System.out.println("Either classroom " + className + " does not exist or student " + studentId + " is not enrolled.");
        }
    }

    public void listClassrooms() {
        if (classrooms.isEmpty()) {
            System.out.println("No classrooms available.");
        } else {
            System.out.println("Classrooms:");
            for (String name : classrooms.keySet()) {
                System.out.println("- " + name);
            }
        }
    }

    public void listStudents(String className) {
        Classroom classroom = classrooms.get(className);
        if (classroom != null) {
            if (classroom.getStudents().isEmpty()) {
                System.out.println("No students enrolled in " + className + ".");
            } else {
                System.out.println("Students in " + className + ":");
                for (String student : classroom.getStudents()) {
                    System.out.println("- " + student);
                }
            }
        } else {
            System.out.println("Classroom " + className + " does not exist.");
        }
    }
}
