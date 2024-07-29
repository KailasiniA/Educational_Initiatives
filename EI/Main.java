import java.util.Scanner;

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
