import java.util.*;

class Classroom {
    private String name;
    private List<String> students;
    private List<String> assignments;

    public Classroom(String name) {
        this.name = name;
        this.students = new ArrayList<>();
        this.assignments = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addStudent(String studentId) {
        students.add(studentId);
    }

    public void addAssignment(String assignment) {
        assignments.add(assignment);
    }

    public List<String> getStudents() {
        return students;
    }

    public List<String> getAssignments() {
        return assignments;
    }
}

class VirtualClassroomManager {
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

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static VirtualClassroomManager manager = new VirtualClassroomManager();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nEnter a command:");
            String command = scanner.nextLine();
            String[] parts = command.split(" ", 2);

            try {
                switch (parts[0]) {
                    case "add_classroom":
                        manager.addClassroom(parts[1]);
                        break;
                    case "add_student":
                        String[] studentData = parts[1].split(" ", 2);
                        manager.addStudent(studentData[0], studentData[1]);
                        break;
                    case "schedule_assignment":
                        String[] assignmentData = parts[1].split(" ", 2);
                        manager.scheduleAssignment(assignmentData[0], assignmentData[1]);
                        break;
                    case "submit_assignment":
                        String[] submissionData = parts[1].split(" ", 3);
                        manager.submitAssignment(submissionData[0], submissionData[1], submissionData[2]);
                        break;
                    case "list_classrooms":
                        manager.listClassrooms();
                        break;
                    case "list_students":
                        manager.listStudents(parts[1]);
                        break;
                    case "exit":
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Unknown command.");
                }
            } catch (Exception e) {
                System.out.println("Error processing command: " + e.getMessage());
            }
        }
    }
}
