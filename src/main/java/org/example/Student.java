package org.example;
import java.io.IOException;
import java.util.*;

import static java.lang.Integer.parseInt;
import java.util.stream.Collectors;

public class Student extends StudentUser {
    private UserManagement userManagement;
    private DataManagement dataManagement = new DataManagement(userManagement);
    static List<Student> allStudents = new ArrayList<>();
    String name;
    int id;
    StudentUser userDetails;
    List<Course> enrolledIn = new ArrayList<>();
    Map<Course, String> grades = new HashMap<>();

    public Student(String username, String password, String name, int id) {
        super(username, password);
        this.name = name;
        this.id = id;
        allStudents.add(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Course> getEnrolledIn() {
        return enrolledIn;
    }

    public void enrollIn(Course course) {
        this.enrolledIn.add(course);
        course.enrolledStudents.add(this);
    }

    public void unenrollFrom(Course course){
        this.enrolledIn.remove(course);
        course.enrolledStudents.remove(this);
    }

    @Override
    public String toString(){
        return "Student "+id+": "+name+", username: "+getUsername();
    }

    public void updateGrade(Course course, String grade){
        grades.put(course, grade);
        try {
            String text = "\n"+course.id + "," + this.id + "," + grade;
            dataManagement.writeToFile("grades.csv", text);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public Map<Course, String> getGrades(){
        return this.grades;
    }

    public static List<Student> search(String searchTerm) {
        List<Student> searchResults = new ArrayList<>();
        for (Student student : allStudents) {
            if (student.name.contains(searchTerm) || student.id == parseInt(searchTerm) || student.getUsername().contains(searchTerm)) {
                searchResults.add(student);
            }
        }
        return searchResults;
    }
    public static List<Student> sortStudentsByName() {
        return allStudents.stream()
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.toList());
    }
    public static List<Student> sortStudentsById() {
        return allStudents.stream()
                .sorted(Comparator.comparing(Student::getId))
                .collect(Collectors.toList());
    }

    public static void deleteStudent(Student student){
        allStudents.remove(student);
    }
}