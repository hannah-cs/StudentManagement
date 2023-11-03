package org.example;
public class Main {
    public static void main(String[] args) {
        UserManagement userManagement = new UserManagement();
        DataManagement dataManagement = new DataManagement(userManagement);
        CLIMenu menu = new CLIMenu(userManagement, dataManagement);
        menu.displayMenu();
    }
}