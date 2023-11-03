package org.example;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    @Test
    public void testCreateStudent() {
        Student student = new Student("user", "pw", "Test Student", 12345);
        assertEquals("user", student.getUsername());
        assertEquals("pw", student.getPassword());
        assertEquals("Test Student", student.getName());
        assertEquals(12345, student.getId());
    }

    @Test
    public void testUpdateStudent() {
        Student student = new Student("user", "pw", "Test Student", 12345);
        student.setId(54321);
        assertEquals("Updated Name", student.getName());
        assertEquals(54321, student.getId());
    }

    @Test
    public void deleteStudent(){
        Student student = new Student("user", "pw", "Test Student", 12345);
        Student student2 = new Student("user", "pw", "Student Two", 54321);
        Student.deleteStudent(student);
        assertEquals("[Student 54321: Student Two, username: user]", Student.allStudents);
    }
}