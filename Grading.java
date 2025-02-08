import java.util.ArrayList;
import java.util.Scanner;
public class GradingSystem {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<GradeReport> reports = FileHandler.loadReports("GradeReports.ser");

        // Main menu loop
        while (true) {
            System.out.println("Menu:");
            System.out.println();
            System.out.println("[1] Add Student Report");
            System.out.println("[2] View All Reports");
            System.out.println("[3] Delete Student Report");
            System.out.println("[4] Save and Exit");
            System.out.println();


            private static int convertGradeToPoints(String grade) {
                switch (grade.toUpperCase()) {
                    case "5": return 10;
                    case "4": return 9;
                    case "3": return 8;
                    case "2": return 7;
                    case "1": return 6;
                    case "Hyväksytty": return 5;
                    case "Hylätty": return 0;
                    default: throw new IllegalArgumentException("Invalid grade. Please enter a valid letter grade (5,4,3,2,1, Hyväksytty, Hylätty).");
                }
            }

            private static void addStudentReport(Scanner scanner, ArrayList<GradeReport> reports) {
                // Display the note for the user
                System.out.println("----------");
                System.out.println("Note: Enter the student's name and ID, then add subjects and their respective grades.");
                System.out.println("Note: While Entering the Grades be sure to select numbers among {5,4,3,2,1, Hyväksytty, Hylätty} where 5 is the highest and Hylätty is the least");
                System.out.println("----------");

                System.out.print("Enter Student Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Student ID: ");
                String studentId = scanner.nextLine();

                // Check for duplicate student entry
                for (GradeReport report : reports) {
                    if (report.getStudent().getName().equalsIgnoreCase(name) || report.getStudent().getStudentId().equalsIgnoreCase(studentId)) {
                        System.out.println("Student with this name or ID already exists.");
                        return;
                    }
                }

                Student student = new Student(name, studentId);
                GradeReport report = new GradeReport(student);

                // Ask user how many subjects they want to add (between 1 and 7)
                int numSubjects = 0;
                while (numSubjects < 1 || numSubjects > 15) {
                    System.out.print("How many subjects do you want to add? (1 to 15): ");
                    numSubjects = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    if (numSubjects < 1 || numSubjects > 7) {
                        System.out.println("Invalid number. Please enter a number between 1 and 15.");
                    }
                }

                // Collect grades for the specified number of subjects
                for (int i = 1; i <= numSubjects; i++) {
                    System.out.print("Enter Subject " + i + " Name: ");
                    String subjectName = scanner.nextLine();
                    System.out.print("Enter Grade for " + subjectName + ":");
                    String grade = scanner.nextLine().toUpperCase();

                    try {
                        int points = convertGradeToPoints(grade); // Convert grade to points
                        report.addGrade(subjectName, points); // Add grade (as points) for the subject
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid grade: " + e.getMessage());
                    }
                }

                reports.add(report); // Add the report to the list
                System.out.println("Report added successfully!");
            }

            private static void deleteStudentReport(Scanner scanner, ArrayList<GradeReport> reports) {
                System.out.print("Enter Student ID to delete: ");
                String studentId = scanner.nextLine();

                for (GradeReport report : reports) {
                    if (report.getStudent().getStudentId().equals(studentId)) {
                        reports.remove(report);
                        System.out.println("Student report deleted successfully.");
                        return;
                    }
                }

                System.out.println("Student with this ID not found.");
            }
        }