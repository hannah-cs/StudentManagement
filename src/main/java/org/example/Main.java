package org.example;
public class Main {
    public static void main(String[] args) {
        UserManagement userManagement = new UserManagement();
        userManagement.registerUser("hannah", "hello", UserRole.STUDENT);
        userManagement.registerUser("paddy", "startsteps", UserRole.ADMIN);
        CLIMenu menu = new CLIMenu(userManagement);
        menu.displayMenu();
    }
}