package org.example;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;


public class MenuFunctions {

    public List<Students> student;

    public MenuFunctions(){student = new ArrayList<>();}

    public void addStudent(Students students){
        student.add(students);
    }
    public void removeStudent(String studentID){
        student.removeIf(s -> s.getStudentID().equals(studentID));
    }//Remover by ID
    public Optional<Students> searchStudent(String studentID) {//Search Lambda function by ID
        return student.stream()
                .filter(student -> student.getStudentID().equals(studentID))
                .findFirst();
    }
    public void genericReport(){
        for (Students s : student) {//Prints all objects in student List
            System.out.println("|-------------|----------------|");
            System.out.println("|Name         | " + s.getName());
            System.out.println("|-------------|----------------|");
            System.out.println("|ID           | " + s.getStudentID());
            System.out.println("|-------------|----------------|");
            System.out.println("|email        | " + s.getEmail());
            System.out.println("|-------------|----------------|");
            System.out.println("|Date Of Birth| " + s.getDateOfBirth());
            System.out.println("|-------------|----------------|");
        }
    }
    public void writeFile(String filePath) throws IOException {//Taking a user path for write a file function
        List<String> lines = new ArrayList<>();//creating new arry list and then add all values from student List
        for (Students s : student) {
            lines.add("Name: " + s.getName());
            lines.add("ID: " + s.getStudentID());
            lines.add("Email: " + s.getEmail());
            lines.add("Date Of Birth: " + s.getDateOfBirth());
            lines.add(""); // Add an empty line between each student
        }
        Files.write(Path.of(filePath), lines, StandardOpenOption.CREATE);// Creating a file according to user file path
    }

    public void readFromFile(String filePath) throws IOException {//Taking a user path for read a file function
        List<String> lines = Files.readAllLines(Path.of(filePath));// Reading a file according to user file path
        for (String line : lines) {
            System.out.println(line);
        }
    }
}
