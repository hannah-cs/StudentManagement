package org.example;
import java.util.List;
import java.util.ArrayList;

public class UserManagement {
    DataManagement dataManagement;
    List<String> passwords = new ArrayList<>();
    List<UserManagement> allUsers = new ArrayList<>();
    List<AdminUser> admins = new ArrayList<>();
    List<StudentUser> students = new ArrayList<>();
    List<TeacherUser> teachers = new ArrayList<>();

    public void setDataManagement(DataManagement dataManagement) {
        this.dataManagement = dataManagement;
        dataManagement.readUserDataFromFile(UserRole.ADMIN);
        dataManagement.readUserDataFromFile(UserRole.STUDENT);
        dataManagement.readUserDataFromFile(UserRole.TEACHER);
    }


    public void registerUser(String username, String password, UserRole role) {
        switch (role) {
            case ADMIN:
                AdminUser admin = new AdminUser(username, password);
                admins.add(admin);
                passwords.add(password);
                break;
            case TEACHER:
                TeacherUser teacher = new TeacherUser(username, password);
                teachers.add(teacher);
                passwords.add(password);
                break;
            case STUDENT:
                StudentUser student = new StudentUser(username, password);
                students.add(student);
                passwords.add(password);
                break;
        }
    }

    public boolean loginUser(String username, String password, UserRole role){
        switch (role) {
            case ADMIN:
                for (AdminUser admin : admins){
                    if (admin.getUsername().equals(username) && admin.getPassword().equals(password)){
                        return true;
                    }
                }
                break;
            case TEACHER:
                for (TeacherUser teacher : teachers){
                    if (teacher.getUsername().equals(username) && teacher.getPassword().equals(password)){
                        return true;
                    }
                }
                break;
            case STUDENT:
                for (StudentUser student : students){
                    if (student.getUsername().equals(username) && student.getPassword().equals(password)){
                        return true;
                    }
                }
                break;
        }
        return false;
    }
}