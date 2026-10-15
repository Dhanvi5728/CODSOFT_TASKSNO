import java.util.Random;
import java.util.Scanner;

public class Numbergame { // Changed from NumberGame to Numbergame
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int totalRoundsWon = 0;
        int totalScore = 0;
        boolean playAgain = true;
        
        System.out.println("=== Welcome to the Number Game! ===");
        
        while (playAgain) {
            int targetNumber = random.nextInt(100) + 1;
            int maxAttempts = 7; 
            int attemptsTaken = 0;
            boolean guessedCorrectly = false;
            
            System.out.println("\nI have generated a number between 1 and 100.");
            System.out.println("You have " + maxAttempts + " attempts to guess it.");
            
            while (attemptsTaken < maxAttempts) {
                System.out.print("Enter your guess (Attempt " + (attemptsTaken + 1) + "/" + maxAttempts + "): ");
                int userGuess = scanner.nextInt();
                attemptsTaken++;
                
                if (userGuess == targetNumber) {
                    System.out.println("Congratulations! You guessed the correct number in " + attemptsTaken + " attempts.");
                    guessedCorrectly = true;
                    totalRoundsWon++;
                    totalScore += (maxAttempts - attemptsTaken + 1) * 10; 
                    break;
                } else if (userGuess > targetNumber) {
                    System.out.println("Too high! Try a lower number.");
                } else {
                    System.out.println("Too low! Try a higher number.");
                }
            }
            
            if (!guessedCorrectly) {
                System.out.println("Out of attempts! The correct number was: " + targetNumber);
            }
            
            System.out.print("\nDo you want to play another round? (yes/no): ");
            String response = scanner.next().trim().toLowerCase();
            if (!response.equals("yes") && !response.equals("y")) {
                playAgain = false;
            }
        }
        
        System.out.println("\n=== Game Over ===");
        System.out.println("Rounds Won: " + totalRoundsWon);
        System.out.println("Total Score: " + totalScore);
        System.out.println("Thank you for playing!");
        
        scanner.close();
    }
}