package org.example;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Student extends StudentUser {
    String name;
    int id;
    StudentUser userDetails;
    List<Course> enrolledIn = new ArrayList<>();
    Map<Course, String> grades = new HashMap<>();

    public Student(String username, String password, String name, int id) {
        super(username, password);
        this.name = name;
        this.id = id;
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
    }

    public void unenrollFrom(Course course){
        this.enrolledIn.remove(course);
    }

    @Override
    public String toString(){
        return "Student "+id+": "+name+", username: "+getUsername();
    }

    public void updateGrade(Course course, String grade){
        grades.put(course, grade);
    }

    public Map<Course, String> getGrades(){
        return this.grades;
    }
}
