package org.example;
import java.util.List;
import java.util.ArrayList;

public class Course {
    String name;
    int id;
    List<Student> enrolledStudents = new ArrayList<>();

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
    }
    public void unenroll(Student student){
        this.enrolledStudents.remove(student);
    }

    @Override
    public String toString(){
        return id+": "+name;
    }
}
