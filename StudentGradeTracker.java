import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class Student {
    String name;
    int rollNo;
    ArrayList<Integer> grades = new ArrayList<>();

    Student(String name, int rollNo) {
        this.name = name;
        this.rollNo = rollNo;
    }

    double getAverage() {
        int sum = 0;
        for (int g : grades) sum += g;
        return grades.size() > 0 ? (double) sum / grades.size() : 0.0;
    }

    String getLetterGrade() {
        double avg = getAverage();
        if (avg >= 90) return "A";
        else if (avg >= 75) return "B";
        else if (avg >= 60) return "C";
        else if (avg >= 40) return "D";
        else return "F";
    }
}

public class StudentGradeTracker {
    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.println("\n--- Student Grade Tracker ---");
            System.out.println("1. Add Student");
            System.out.println("2. Add Grades");
            System.out.println("3. View Report");
            System.out.println("4. Search Student");
            System.out.println("5. Export Report to File");
            System.out.println("6. Exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> addGrades();
                case 3 -> viewReport();
                case 4 -> searchStudent();
                case 5 -> exportReport();
                case 6 -> System.exit(0);
            }
        }
    }

    static void addStudent() {
        System.out.print("Enter Roll No: ");
        int roll = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        students.add(new Student(name, roll));
        System.out.println("Student added!");
    }

    static void addGrades() {
        System.out.print("Enter Roll No: ");
        int roll = sc.nextInt();
        for (Student s : students) {
            if (s.rollNo == roll) {
                System.out.print("Enter Grade: ");
                int grade = sc.nextInt();
                s.grades.add(grade);
                System.out.println("Grade added!");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    static void viewReport() {
        for (Student s : students) {
            System.out.printf("Roll: %d | Name: %s | Avg: %.2f | Grade: %s\n",
                    s.rollNo, s.name, s.getAverage(), s.getLetterGrade());
        }
    }

    static void searchStudent() {
        System.out.print("Enter Roll No: ");
        int roll = sc.nextInt();
        for (Student s : students) {
            if (s.rollNo == roll) {
                System.out.printf("Roll: %d | Name: %s | Avg: %.2f | Grade: %s\n",
                        s.rollNo, s.name, s.getAverage(), s.getLetterGrade());
                return;
            }
        }
        System.out.println("Student not found.");
    }

    static void exportReport() throws IOException {
        FileWriter fw = new FileWriter("StudentReport.txt");
        for (Student s : students) {
            fw.write(String.format("Roll: %d | Name: %s | Avg: %.2f | Grade: %s\n",
                    s.rollNo, s.name, s.getAverage(), s.getLetterGrade()));
        }
        fw.close();
        System.out.println("Report exported to StudentReport.txt");
    }
}
