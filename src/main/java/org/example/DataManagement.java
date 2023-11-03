package org.example;
import java.io.*;

public class DataManagement {
    public String studentData = "students.csv";
    public String teacherData = "teachers.csv";
    public String adminData = "admins.csv";

    public DataManagement() {
    }

    public void writeToFile(String filename, String text) throws IOException {
        FileWriter fw = new FileWriter(filename, true);
        BufferedWriter writer = new BufferedWriter(fw);
        try {
            writer.write(text);
            System.out.println("Successfully written to " + filename);
            writer.close();
        } catch (Exception e){
            e.getStackTrace();
        }
    }
}