package org.example;
import java.util.List;
import java.util.ArrayList;

public class UserManagement {
    DataManagement dataManagement;
    List<String> passwords = new ArrayList<>();
    List<UserManagement> allUsers = new ArrayList<>();
    List<AdminUser> adminUsers = new ArrayList<>();
    List<StudentUser> studentUsers = new ArrayList<>();
    List<TeacherUser> teacherUsers = new ArrayList<>();

    public void setDataManagement(DataManagement dataManagement) {
        this.dataManagement = dataManagement;
        dataManagement.readUserDataFromFile(UserRole.ADMIN);
        dataManagement.readUserDataFromFile(UserRole.STUDENT);
        dataManagement.readUserDataFromFile(UserRole.TEACHER);
    }


    public void registerUser(String username, String password, UserRole role) {
        try {
            switch (role) {
                case ADMIN:
                    AdminUser admin = new AdminUser(username, password);
                    adminUsers.add(admin);
                    passwords.add(password);
                    break;
                case TEACHER:
                    TeacherUser teacher = new TeacherUser(username, password);
                    teacherUsers.add(teacher);
                    passwords.add(password);
                    break;
                case STUDENT:
                    StudentUser student = new StudentUser(username, password);
                    studentUsers.add(student);
                    passwords.add(password);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean loginUser(String username, String password, UserRole role) {
        try {
            switch (role) {
                case ADMIN:
                    for (AdminUser admin : adminUsers) {
                        if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                            return true;
                        }
                    }
                    break;
                case TEACHER:
                    for (TeacherUser teacher : teacherUsers) {
                        if (teacher.getUsername().equals(username) && teacher.getPassword().equals(password)) {
                            return true;
                        }
                    }
                    break;
                case STUDENT:
                    for (StudentUser student : studentUsers) {
                        if (student.getUsername().equals(username) && student.getPassword().equals(password)) {
                            return true;
                        }
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}