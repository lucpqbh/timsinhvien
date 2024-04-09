import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String firstName;
    private String lastName;
    private int studentId;

    public Student(String firstName, String lastName, int studentId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentId = studentId;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    // Override toString method to provide a meaningful representation of Student object
    @Override
    public String toString() {
        return "Student: " + getFullName() + ", Student ID: " + studentId;
    }
}

public class StudentManagemet {
    private static ArrayList<Student> studentList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Student Management System");
            System.out.println("1. Enter Student List");
            System.out.println("2. Find Students by Last Name");
            System.out.println("3. Find and Edit Students by Full Name");
            System.out.println("4. End");
            System.out.print("Enter your choice: ");
            int choice;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // Consume newline
                continue;
            }

            switch (choice) {
                case 1:
                    enterStudentList();
                    break;
                case 2:
                    findStudentByLastName();
                    break;
                case 3:
                    findAndEditStudentByFullName();
                    break;
                case 4:
                    System.out.println("Exiting program. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void enterStudentList() {
        String firstName;
        do {
            System.out.print("Enter first name: ");
            firstName = scanner.nextLine();
            if (!firstName.matches("[a-zA-Z]+")) {
                System.out.println("Invalid input. Please enter a valid first name " +
                        "(alphabetic characters only).");
            }
        } while (!firstName.matches("[a-zA-Z]+"));

        String lastName;
        do {
            System.out.print("Enter last name: ");
            lastName = scanner.nextLine();
            if (!lastName.matches("[a-zA-Z]+")) {
                System.out.println("Invalid input. Please enter a valid last name " +
                        "(alphabetic characters only).");
            }
        } while (!lastName.matches("[a-zA-Z]+"));

        int studentId;
        do {
            System.out.print("Enter student ID: ");
            if (scanner.hasNextInt()) {
                studentId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                break;
            } else {
                System.out.println("Invalid input. Please enter a valid integer for the student ID.");
                scanner.nextLine(); // Consume newline
            }
        } while (true);

        studentList.add(new Student(firstName, lastName, studentId));
        System.out.println("Student added successfully!");
    }

    private static void findStudentByLastName() {
        if (studentList.size() < 1) {
            System.out.println("There are no students in the data, please enter students first!");
        } else {
            System.out.print("Enter The Last Name To Search For: ");
            String lastNameToSearch = scanner.nextLine();

            boolean found = false;
            for (Student student : studentList) {
                if (student.getFullName().endsWith(lastNameToSearch)) {
                    System.out.println("Student Found: " + student);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("Student Not Found!");
            }
        }
    }

    private static void findAndEditStudentByFullName() {
        System.out.print("Enter full name to search: ");
        String searchFullName = scanner.nextLine();
        boolean foundByFullName = false;
        for (Student student : studentList) {
            if (student.getFullName().equalsIgnoreCase(searchFullName)) {
                foundByFullName = true;
                System.out.println("Student found: " + student);

                System.out.print("Enter new first name: ");
                String newFirstName = scanner.nextLine();
                student.setFirstName(newFirstName);

                System.out.print("Enter new last name: ");
                String newLastName = scanner.nextLine();
                student.setLastName(newLastName);

                System.out.print("Enter new student ID: ");
                int newStudentId = scanner.nextInt();
                student.setStudentId(newStudentId);

                System.out.println("Student details updated!");
                break;
            }
        }
        if (!foundByFullName) {
            System.out.println("No student found with the full name: " + searchFullName);
        }
    }
}