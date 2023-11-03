package org.example;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class Course {
    String name;
    int id;
    static List<Course> allCourses = new ArrayList<>();
    List<Student> enrolledStudents = new ArrayList<>();
    Map<Student, String> grades = new HashMap<>();

    public Course(String name, int id) {
        this.name = name;
        this.id = id;
        allCourses.add(this);
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

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void enroll(Student student) {
        this.enrolledStudents.add(student);
        student.enrolledIn.add(this);
    }
    public void unenroll(Student student){
        this.enrolledStudents.remove(student);
        student.enrolledIn.remove(this);
    }

    @Override
    public String toString(){
        return id+": "+name;
    }
    public void grade(Student student, String grade){
        grades.put(student, grade);
        student.updateGrade(this, grade);
    }

    public Map<Student, String> getAllGrades(){
        return this.grades;
    }

    public static List<Course> searchCourses(String searchTerm){
        List<Course> searchResults = new ArrayList<>();
        for (Course course : allCourses) {
            if (course.name.contains(searchTerm) || course.id == parseInt(searchTerm)) {
                searchResults.add(course);
            }
        }
        return searchResults;
    }
}
