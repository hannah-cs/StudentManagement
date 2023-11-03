package org.example;
import java.util.Scanner;

public class CLIMenu {
    private UserManagement userManagement;
    private DataManagement dataManagement = new DataManagement(userManagement);


    public CLIMenu(UserManagement userManagement, DataManagement dataManagement) {
        this.userManagement = userManagement;
        this.dataManagement = dataManagement;
    }
    public void displayMenu() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Menu options:");
            System.out.println("1. User management");
            System.out.println("2. Student management");
            System.out.println("3. Teacher management");
            String choice = scanner.nextLine();
            String nextStep = "";
            switch (choice) {
                case "1":
                    nextStep = "userManager";
                    break;
                case "2":
                    nextStep = "studentManager";
                    break;
                case "3":
                    nextStep = "teacherManager";
                    break;
                default:
                    System.out.println("Not a valid option");
            }
            if (nextStep.equals("userManager")) {
                System.out.println("What would you like to do?");
                System.out.println("1. Register new user");
                System.out.println("2. Log in");
                String choice2 = scanner.nextLine();
                if (choice2.contains("1")) {
                    System.out.println("Register new user. Create a username:");
                    String username = scanner.nextLine();
                    System.out.println("Create a password:");
                    String password = scanner.nextLine();
                    System.out.println("Choose user role:");
                    System.out.println("1 - admin");
                    System.out.println("2 - teacher");
                    System.out.println("3 - student");
                    String choice3 = scanner.nextLine();
                    String userData = "\n"+username + "," + password;
                    switch (choice3) {
                        case "1":
                            userManagement.registerUser(username, password, UserRole.ADMIN);
                            System.out.println("Successfully created new admin: " + username);
                            dataManagement.writeToFile(dataManagement.adminData, userData);
                            break;
                        case "2":
                            userManagement.registerUser(username, password, UserRole.TEACHER);
                            System.out.println("Successfully created new teacher: " + username);
                            dataManagement.writeToFile(dataManagement.teacherData, userData);
                            break;
                        case "3":
                            userManagement.registerUser(username, password, UserRole.STUDENT);
                            System.out.println("Successfully created new student: " + username);
                            dataManagement.writeToFile(dataManagement.studentData, userData);
                            break;
                        default:
                            System.out.println("Not a valid option");
                    }
                } else if (choice2.contains("2")) {
                    System.out.println("Log in. Enter username:");
                    String username = scanner.nextLine();
                    System.out.println("Enter password");
                    String password = scanner.nextLine();
                    System.out.println("What is your user role?");
                    System.out.println("1. Admin");
                    System.out.println("2. Teacher");
                    System.out.println("3. Student");
                    String choice4 = scanner.nextLine();
                    switch (choice4) {
                        case "1":
                            dataManagement.readUserDataFromFile(UserRole.ADMIN);
                            if (userManagement.loginUser(username, password, UserRole.ADMIN)) {
                                System.out.println("Successfully logged in.");
                            } else {
                                System.out.println("Invalid credentials");
                            }
                            break;
                        case "2":
                            dataManagement.readUserDataFromFile(UserRole.TEACHER);
                            if (userManagement.loginUser(username, password, UserRole.TEACHER)) {
                                System.out.println("Successfully logged in.");
                            } else {
                                System.out.println("Invalid credentials");
                            }
                            break;
                        case "3":
                            dataManagement.readUserDataFromFile(UserRole.STUDENT);
                            if (userManagement.loginUser(username, password, UserRole.STUDENT)) {
                                System.out.println("Successfully logged in.");
                            } else {
                                System.out.println("Invalid credentials");
                            }
                            break;
                        default:
                            System.out.println("Not a valid user role.");
                    }
                }
            } else if (nextStep.equals("studentManager")) {
                System.out.println("Warning: you must be an admin or teacher to access this option.");
                System.out.println("Username:");
                String username = scanner.nextLine();
                System.out.println("Password:");
                String password = scanner.nextLine();
                boolean hasScope = false;
                if (userManagement.loginUser(username, password, UserRole.ADMIN) || userManagement.loginUser(username, password, UserRole.TEACHER)){
                    hasScope = true;
                }
                if (!hasScope) {
                    System.out.println("Access denied.");
                } else {
                    System.out.println("What would you like to do?");
                    System.out.println("1. View all students");
                    System.out.println("2. Search students");
                    System.out.println("3. Register new student");
                    System.out.println("4. Update existing student");
                    System.out.println("5. Delete student");
                    System.out.println("6. Set grade for student");
                    String choice5 = scanner.nextLine();
                    String nextStep1 = "";
                    switch (choice5) {
                        case "1":
                            nextStep1 = "view";
                            break;
                        case "2":
                            nextStep1 = "search";
                            break;
                        case "3":
                            nextStep1 = "register";
                            break;
                        case "4":
                            nextStep1 = "update";
                            break;
                        case "5":
                            nextStep1 = "delete";
                            break;
                        case "6":
                            nextStep1 = "grade";
                            break;
                        default:
                            System.out.println("Not a valid option");
                    }
                    if (nextStep1.contains("view")) {
                        System.out.println("All registered students:");
                        System.out.println(userManagement.studentUsers);
                    } else if (nextStep1.contains("search")) {
                        System.out.println("Enter search term:");
                        String searchTerm = scanner.nextLine();
                        if (Student.search(searchTerm) != null){
                            System.out.println("Search results:");
                            System.out.println(Student.search(searchTerm));
                        } else {
                            System.out.println("No results found.");
                        }
                    } else if (nextStep1.contains("register")) {
                        System.out.println("Register new student. Create a username:");
                        String usernameStudent = scanner.nextLine();
                        System.out.println("Create a password:");
                        String passwordStudent = scanner.nextLine();
                        userManagement.registerUser(usernameStudent, passwordStudent, UserRole.STUDENT);
                        System.out.println("Successfully registered " + usernameStudent);
                    } else if (nextStep1.contains("update")) {
                        System.out.println("Please enter the username of the student you'd like to update");
                        String usernameStudent = scanner.nextLine();
                        boolean studentFound = false;
                        for (StudentUser student : userManagement.studentUsers) {
                            if (student.getUsername().equals(usernameStudent)) {
                                studentFound = true;
                                System.out.println(student.getUsername() + " found. Enter new username");
                                String newUsername = scanner.nextLine();
                                student.setUsername(newUsername);
                                System.out.println("Successfully updated username to " + student.getUsername());
                            }
                        }
                        if (!studentFound) {
                            System.out.println("Could not find student '" + usernameStudent + "'");
                        }
                    } else if (nextStep1.contains("delete")) {
                        System.out.println("Enter username of student you want to delete:");
                        String deleteStudent = scanner.nextLine();
                        StudentUser studentToDelete = null;
                        for (StudentUser student : userManagement.studentUsers) {
                            if (student.getUsername().equals(deleteStudent)) {
                                studentToDelete = student;
                                break;
                            }
                        }
                        if (studentToDelete != null) {
                            System.out.println("Are you sure you want to delete " + studentToDelete.getUsername() + "? (y/n)");
                            String confirmDelete = scanner.nextLine();
                            if (confirmDelete.equals("y")) {
                                userManagement.studentUsers.remove(studentToDelete);
                                System.out.println("Successfully deleted " + studentToDelete.getUsername());
                            } else {
                                System.out.println(studentToDelete.getUsername() + " not deleted.");
                            }
                        } else {
                            System.out.println("Could not find student '" + deleteStudent + "'");
                        }
                    } else if (nextStep1.contains("grade")){
                        System.out.println("Enter the ID of the student you want to grade");
                        int idToGrade = scanner.nextInt();
                        Student studentToGrade = null;
                        for (Student student : Student.allStudents) {
                            if (student.id == idToGrade) {
                                studentToGrade = student;
                            }
                        }
                        if (studentToGrade != null) {
                            System.out.println(studentToGrade.getName()+" is currently enrolled in the following courses: "+studentToGrade.getEnrolledIn());
                            System.out.println("Enter the id of the course you'd like to add a grade for.");
                            int courseId = scanner.nextInt();
                            scanner.nextLine();
                            Course courseToGrade = null;
                            for (Course course : studentToGrade.enrolledIn) {
                                if (course.id == courseId) {
                                    courseToGrade = course;
                                }
                            }
                            if (courseToGrade != null){
                                System.out.println("Enter "+studentToGrade.getName()+"'s grade for "+courseToGrade.getName());
                                String grade = scanner.nextLine();
                                courseToGrade.grade(studentToGrade, grade);
                                System.out.println("Updated "+studentToGrade.getName()+"'s grade for "+courseToGrade.getName()+" to "+grade+".");
                            } else {
                                System.out.println("Course not found.");
                            }
                        } else {
                            System.out.println("Student not found.");
                        }
                    }
                }
            } else if (nextStep.equals("teacherManager")) {
                System.out.println("You must be an admin to access this option.");
                System.out.println("Admin username:");
                String username = scanner.nextLine();
                System.out.println("Admin password:");
                String password = scanner.nextLine();
                if (!userManagement.loginUser(username, password, UserRole.ADMIN)) {
                    System.out.println("Access denied.");
                } else {
                    System.out.println("What would you like to do?");
                    System.out.println("1. View all teachers");
                    System.out.println("2. Register new teacher");
                    System.out.println("3. Update existing teacher");
                    System.out.println("4. Delete teacher");
                    String choice5 = scanner.nextLine();
                    String nextStep1 = "";
                    switch (choice5) {
                        case "1":
                            nextStep1 = "view";
                            break;
                        case "2":
                            nextStep1 = "register";
                            break;
                        case "3":
                            nextStep1 = "update";
                            break;
                        case "4":
                            nextStep1 = "delete";
                            break;
                        default:
                            System.out.println("Not a valid option");
                    }
                    if (nextStep1.contains("view")) {
                        System.out.println("All registered teachers:");
                        System.out.println(userManagement.teacherUsers);
                    } else if (nextStep1.contains("register")) {
                        System.out.println("Register new teacher. Create a username:");
                        String usernameTeacher = scanner.nextLine();
                        System.out.println("Create a password:");
                        String passwordTeacher = scanner.nextLine();
                        userManagement.registerUser(usernameTeacher, passwordTeacher, UserRole.STUDENT);
                        System.out.println("Successfully registered " + usernameTeacher);
                    } else if (nextStep1.contains("update")) {
                        System.out.println("Please enter the username of the teacher you'd like to update");
                        String usernameTeacher = scanner.nextLine();
                        boolean teacherFound = false;
                        for (TeacherUser teacher : userManagement.teacherUsers) {
                            if (teacher.getUsername().equals(usernameTeacher)) {
                                teacherFound = true;
                                System.out.println(teacher.getUsername() + " found. Enter new username");
                                String newUsername = scanner.nextLine();
                                teacher.setUsername(newUsername);
                                System.out.println("Successfully updated username to " + teacher.getUsername());
                            }
                        }
                        if (!teacherFound) {
                            System.out.println("Could not find teacher '" + usernameTeacher + "'");
                        }
                    } else if (nextStep1.contains("delete")) {
                        System.out.println("Enter username of teacher you want to delete:");
                        String deleteTeacher = scanner.nextLine();
                        TeacherUser teacherToDelete = null;

                        for (TeacherUser teacher : userManagement.teacherUsers) {
                            if (teacher.getUsername().equals(deleteTeacher)) {
                                teacherToDelete = teacher;
                                break;
                            }
                        }
                        if (teacherToDelete != null) {
                            System.out.println("Are you sure you want to delete " + teacherToDelete.getUsername() + "? (y/n)");
                            String confirmDelete = scanner.nextLine();
                            if (confirmDelete.equals("y")) {
                                userManagement.teacherUsers.remove(teacherToDelete);
                                System.out.println("Successfully deleted " + teacherToDelete.getUsername());
                            } else {
                                System.out.println(teacherToDelete.getUsername() + " not deleted.");
                            }
                        } else {
                            System.out.println("Could not find student '" + deleteTeacher + "'");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}