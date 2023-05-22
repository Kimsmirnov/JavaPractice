package org.example;

import java.util.Date;


public class Students {
    String name;
    String studentID;
    String email;
    Date dateOfBirth;

    public Students(String name, String studentID, String email, Date dateOfBirth) {
        this.name = name;
        this.studentID = studentID;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }
    public String getStudentID() {
        return studentID;
    }
    public String getEmail() {
        return email;
    }
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
}
