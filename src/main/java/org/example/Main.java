package org.example;
public class Main {
    public static void main(String[] args) {
        UserManagement userManagement = new UserManagement();
        DataManagement dataManagement = new DataManagement(userManagement);
        userManagement.setDataManagement(dataManagement);
        Student student = new Student("Hannah", "123", "Hannah", 123);
        Student student2 = new Student("Amanda", "321", "Amanda", 111);
        Course course = new Course("Java", 101);
        Course course2 = new Course("Advanced java", 102);
        student.enrollIn(course);
        CLIMenu menu = new CLIMenu(userManagement, dataManagement);
        menu.displayMenu();
    }
}