import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: Number of subjects
        System.out.print("Enter the total number of subjects: ");
        int numSubjects = scanner.nextInt();

        int totalMarks = 0;

        // Input: Marks for each subject
        for (int i = 1; i <= numSubjects; i++) {
            System.out.print("Enter marks obtained in subject " + i + " (out of 100): ");
            int marks = scanner.nextInt();
            
            // Validate input
            while (marks < 0 || marks > 100) {
                System.out.print("Invalid marks! Please enter between 0 and 100: ");
                marks = scanner.nextInt();
            }
            totalMarks += marks;
        }

        // Calculate Average Percentage
        double averagePercentage = (double) totalMarks / numSubjects;

        // Grade Calculation
        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else if (averagePercentage >= 50) {
            grade = 'E';
        } else {
            grade = 'F';
        }

        // Display Results
        System.out.println("\n--- RESULTS ---");
        System.out.println("Total Marks Obtained: " + totalMarks + " / " + (numSubjects * 100));
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
        System.out.println("Assigned Grade: " + grade);

        scanner.close();
    }
}