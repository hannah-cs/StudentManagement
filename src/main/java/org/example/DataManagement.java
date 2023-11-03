package org.example;
import java.io.*;

public class DataManagement {
    public String studentData = "students.csv";
    public String teacherData = "teachers.csv";
    public String adminData = "admins.csv";
    UserManagement userManagement;

    public DataManagement(UserManagement userManagement) {
        this.userManagement = userManagement;
    }

    public void writeToFile(String filename, String text) throws IOException {
        FileWriter fw = new FileWriter(filename, true);
        BufferedWriter writer = new BufferedWriter(fw);
        try {
            writer.write(text);
            System.out.println("Successfully written to " + filename);
            writer.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void readUserDataFromFile(UserRole role) {
        String line;
        BufferedReader reader = null;
        try {
            if (role.equals(UserRole.ADMIN)) {
                reader = new BufferedReader(new FileReader(adminData));
                while ((line = reader.readLine()) != null) {
                    String[] userData = line.split(",");
                    if (userData.length >= 2) {
                        String username = userData[0].trim();
                        String password = userData[1].trim();
                        userManagement.admins.add(new AdminUser(username, password));
                    }
                }
            } else if (role.equals(UserRole.TEACHER)) {
                reader = new BufferedReader(new FileReader(teacherData));
                while ((line = reader.readLine()) != null) {
                    String[] userData = line.split(",");
                    if (userData.length >= 2) {
                        String username = userData[0].trim();
                        String password = userData[1].trim();
                        userManagement.teachers.add(new TeacherUser(username, password));
                    }
                }
            } else if (role.equals(UserRole.STUDENT)) {
                reader = new BufferedReader(new FileReader(studentData));
                while ((line = reader.readLine()) != null) {
                    String[] userData = line.split(",");
                    if (userData.length >= 2) {
                        String username = userData[0].trim();
                        String password = userData[1].trim();
                        userManagement.students.add(new StudentUser(username, password));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}