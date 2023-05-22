package org.example;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.*;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class Main {


    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);//Logger for Logs
    private static boolean isValidName(String name) { // Function for Name checking
        Pattern pattern = Pattern.compile("^[a-zA-Z]+$");// Name can include only characters
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static void main(String[] args) throws IOException {

        MenuFunctions menuFunctions = new MenuFunctions();
        Scanner scanner = new Scanner(System.in);// Scanner needs for input user data

        while (true) {
            System.out.print("\n");
            System.out.print("\n");
            System.out.println("|--|----------------|");
            System.out.println("|1.| Add student    |");
            System.out.println("|--|----------------|");
            System.out.println("|2.| Remove student |");
            System.out.println("|--|----------------|");
            System.out.println("|3.| Search student |");
            System.out.println("|--|----------------|");
            System.out.println("|4.| Generate report|");
            System.out.println("|--|----------------|");
            System.out.println("|5.| Write File     |");
            System.out.println("|--|----------------|");
            System.out.println("|6.| Read File      |");
            System.out.println("|--|----------------|");
            System.out.println("|7.| Exit           |");
            System.out.println("|--|----------------|");
            System.out.print("\n");
            System.out.print("Enter Number of action: ");
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 1 -> {
                    String name;
                    try {
                        System.out.print("Enter student name: ");
                        name = scanner.nextLine();
                        if (!isValidName(name)) {// Checking for name validation
                            logger.info("Invalid name. Student not added.");
                        } else {
                            System.out.print("Enter student ID: ");
                            String studentID = scanner.nextLine();
                            System.out.print("Enter student Email: ");
                            String email = scanner.nextLine();
                            System.out.print("Enter student Date Of Birth: ");
                            String dobString = scanner.nextLine();
                            try {
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");// Date format for date object
                                Date dateOfBirth = dateFormat.parse(dobString);
                                scanner.nextLine();
                                menuFunctions.addStudent(new Students(name, studentID, email, dateOfBirth));//Adding function
                                logger.info("Student added.");
                            } catch (Exception ex) {
                                logger.info("Invalid date format. Student not added.");
                            }
                        }
                    } catch (NumberFormatException e) {
                        logger.info("Invalid name. Student not added.");
                    }
                }
                case 2 -> {
                    try {
                        System.out.print("Enter studentID which you would like to remove: ");
                        String removeID = scanner.nextLine();
                        menuFunctions.removeStudent(removeID);// Remover function
                        logger.info("Student removed.");
                    } catch (Exception ex) {
                        logger.info("Invalid StudentID or Student not exist.");
                    }
                }
                case 3 -> {
                    System.out.print("Enter student ID to search: ");
                    String searchID = scanner.nextLine();
                    Optional<Students> foundStudent = menuFunctions.searchStudent(searchID);// Lamda searching function
                    if (foundStudent.isPresent()) {
                        System.out.print("\n");
                        logger.info("Student found:");
                        Students student = foundStudent.get();
                        System.out.println("Name: " + student.getName());
                        System.out.println("ID: " + student.getStudentID());
                        System.out.println("Email: " + student.getEmail());
                        System.out.println("Date Of Birth: " + student.getDateOfBirth());
                    } else {
                        logger.info("Student not found.");
                    }
                }
                case 4 -> menuFunctions.genericReport();//Displaying all existing students in array List
                case 5 -> {
                    System.out.println("Enter path for a file(example-> D:\\SomeDirect\\NameOfFile.txt: ");
                    String filepath = scanner.nextLine();
                    menuFunctions.writeFile(filepath);//Write a file function
                    logger.info("File created.");
                }
                case 6 -> {
                    System.out.println("Enter path for a file(example-> D:\\SomeDirect\\NameOfFile.txt: ");
                    String filePath = scanner.nextLine();
                    menuFunctions.readFromFile(filePath);//read From File function
                    logger.info("File opened.");
                }
                case 7 -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid input. Please try again.");
            }
        }
    }
}