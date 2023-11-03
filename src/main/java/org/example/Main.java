package org.example;
public class Main {
    public static void main(String[] args) {
        UserManagement userManagement = new UserManagement();
        DataManagement dataManagement = new DataManagement(userManagement);
        userManagement.setDataManagement(dataManagement);
        Student student = new Student("Hannah", "123", "Hannah", 123);
        Course course = new Course("Java", 101);
        student.enrollIn(course);
        CLIMenu menu = new CLIMenu(userManagement, dataManagement);
        menu.displayMenu();
    }
}