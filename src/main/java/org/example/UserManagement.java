package org.example;
import java.util.List;
import java.util.ArrayList;

public class UserManagement {
    List<String> passwords = new ArrayList<>();
    List<UserManagement> allUsers = new ArrayList<>();
    List<Admin> admins = new ArrayList<>();
    List<Student> students = new ArrayList<>();
    List<Teacher> teachers = new ArrayList<>();


    public void registerUser(String username, String password, UserRole role) {
        switch (role) {
            case ADMIN:
                Admin admin = new Admin(username, password);
                admins.add(admin);
                passwords.add(password);
                break;
            case TEACHER:
                Teacher teacher = new Teacher(username, password);
                teachers.add(teacher);
                passwords.add(password);
                break;
            case STUDENT:
                Student student = new Student(username, password);
                students.add(student);
                passwords.add(password);
                break;
        }
    }

    public boolean loginUser(String username, String password, UserRole role){
        switch (role) {
            case ADMIN:
                for (Admin admin : admins){
                    if (admin.getUsername().equals(username) && admin.getPassword().equals(password)){
                        return true;
                    }
                }
                break;
            case TEACHER:
                for (Teacher teacher : teachers){
                    if (teacher.getUsername().equals(username) && teacher.getPassword().equals(password)){
                        return true;
                    }
                }
                break;
            case STUDENT:
                for (Student student : students){
                    if (student.getUsername().equals(username) && student.getPassword().equals(password)){
                        return true;
                    }
                }
                break;
        }
        return false;
    }
}