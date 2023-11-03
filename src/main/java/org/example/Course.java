package org.example;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Course {
    String name;
    int id;
    List<Student> enrolledStudents = new ArrayList<>();
    Map<Student, String> grades = new HashMap<>();

    public Course(String name, int id) {
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
}
