package org.example;
import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class CLIMenu {
    private UserManagement userManagement;

    public CLIMenu(UserManagement userManagement) {
        this.userManagement = userManagement;
    }

    public void displayMenu() {
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
                switch (choice3) {
                    case "1":
                        userManagement.registerUser(username, password, UserRole.ADMIN);
                        System.out.println("Successfully created new admin: " + username);
                        break;
                    case "2":
                        userManagement.registerUser(username, password, UserRole.TEACHER);
                        System.out.println("Successfully created new teacher: " + username);
                        break;
                    case "3":
                        userManagement.registerUser(username, password, UserRole.STUDENT);
                        System.out.println("Successfully created new student: " + username);
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
                        if (userManagement.loginUser(username, password, UserRole.ADMIN)) {
                            System.out.println("Successfully logged in.");
                        } else {
                            System.out.println("Invalid credentials");
                        }
                        break;
                    case "2":
                        if (userManagement.loginUser(username, password, UserRole.TEACHER)) {
                            System.out.println("Successfully logged in.");
                        } else {
                            System.out.println("Invalid credentials");
                        }
                        break;
                    case "3":
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
        }
    }
}