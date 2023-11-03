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
                System.out.println("You must be an admin to access this option.");
                System.out.println("Admin username:");
                String username = scanner.nextLine();
                System.out.println("Admin password:");
                String password = scanner.nextLine();
                if (!userManagement.loginUser(username, password, UserRole.ADMIN)) {
                    System.out.println("Access denied.");
                } else {
                    System.out.println("What would you like to do?");
                    System.out.println("1. View all students");
                    System.out.println("2. Register new student");
                    System.out.println("3. Update existing student");
                    System.out.println("4. Delete student");
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
                        System.out.println("All registered students:");
                        System.out.println(userManagement.students);
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
                        for (Student student : userManagement.students) {
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
                        Student studentToDelete = null;
                        for (Student student : userManagement.students) {
                            if (student.getUsername().equals(deleteStudent)) {
                                studentToDelete = student;
                                break;
                            }
                        }
                        if (studentToDelete != null) {
                            System.out.println("Are you sure you want to delete " + studentToDelete.getUsername() + "? (y/n)");
                            String confirmDelete = scanner.nextLine();
                            if (confirmDelete.equals("y")) {
                                userManagement.students.remove(studentToDelete);
                                System.out.println("Successfully deleted " + studentToDelete.getUsername());
                            } else {
                                System.out.println(studentToDelete.getUsername() + " not deleted.");
                            }
                        } else {
                            System.out.println("Could not find student '" + deleteStudent + "'");
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
                        System.out.println(userManagement.teachers);
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
                        for (Teacher teacher : userManagement.teachers) {
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
                        Teacher teacherToDelete = null;

                        for (Teacher teacher : userManagement.teachers) {
                            if (teacher.getUsername().equals(deleteTeacher)) {
                                teacherToDelete = teacher;
                                break;
                            }
                        }
                        if (teacherToDelete != null) {
                            System.out.println("Are you sure you want to delete " + teacherToDelete.getUsername() + "? (y/n)");
                            String confirmDelete = scanner.nextLine();
                            if (confirmDelete.equals("y")) {
                                userManagement.teachers.remove(teacherToDelete);
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