import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class QuizApplication extends JFrame {

    // 1. Quiz Questions and Options Data Structure
    static class Question {
        String questionText;
        String[] options;
        int correctOptionIndex;

        public Question(String questionText, String[] options, int correctOptionIndex) {
            final String text = questionText;
            this.questionText = text;
            this.options = options;
            this.correctOptionIndex = correctOptionIndex;
        }
    }

    private ArrayList<Question> quizData;
    private int currentQuestionIndex = 0;
    private int score = 0;
    
    // Timer Variables
    private Timer timer;
    private int timeLeft = 10; // 10 seconds per question

    // GUI Components
    private JLabel timerLabel;
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup optionsGroup;
    private JButton submitButton;
    private JPanel quizPanel;

    public QuizApplication() {
        // Initialize Questions
        quizData = new ArrayList<>();
        quizData.add(new Question(
                "Which programming language is mainly used for web development behavior?",
                new String[]{"Python", "JavaScript", "C++", "HTML"}, 1));
        quizData.add(new Question(
                "What does CSS stand for?",
                new String[]{"Creative Style Sheets", "Computer Style Sheets", "Cascading Style Sheets", "Colorful Style Sheets"}, 2));
        quizData.add(new Question(
                "Which HTML element is used to link a JavaScript file?",
                new String[]{"<script>", "<javascript>", "<js>", "<link>"}, 0));

        // Setup Window JFrame
        setTitle("Quiz Application with Timer");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main Layout Container
        quizPanel = new JPanel();
        quizPanel.setLayout(new BoxLayout(quizPanel, BoxLayout.Y_AXIS));
        quizPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        quizPanel.setBackground(new Color(240, 249, 255));

        // 2. Timer Setup
        timerLabel = new JLabel("Time Left: 10s");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        timerLabel.setForeground(Color.RED);
        timerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        quizPanel.add(timerLabel);
        quizPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // 3. Question Display Setup
        questionLabel = new JLabel("Question text goes here");
        questionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        quizPanel.add(questionLabel);
        quizPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Setup 4 Options Elements
        optionButtons = new JRadioButton[4];
        optionsGroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            optionButtons[i].setFont(new Font("Arial", Font.PLAIN, 13));
            optionButtons[i].setBackground(new Color(240, 249, 255));
            optionButtons[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            optionsGroup.add(optionButtons[i]);
            quizPanel.add(optionButtons[i]);
            quizPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        // 4. Answer Submission Button
        submitButton = new JButton("Submit Answer");
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setBackground(new Color(3, 105, 161));
        submitButton.setForeground(Color.WHITE);
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmission(false);
            }
        });
        quizPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        quizPanel.add(submitButton);

        add(quizPanel);

        // Background Swing Timer Logic
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                timerLabel.setText("Time Left: " + timeLeft + "s");
                if (timeLeft <= 0) {
                    timer.stop();
                    handleSubmission(true); // Forced timeout submission
                }
            }
        });

        // Start Quiz Game
        displayQuestion();
    }

    // Load next question into view elements
    private void displayQuestion() {
        if (currentQuestionIndex < quizData.size()) {
            Question q = quizData.get(currentQuestionIndex);
            questionLabel.setText((currentQuestionIndex + 1) + ". " + q.questionText);
            
            optionsGroup.clearSelection();
            for (int i = 0; i < 4; i++) {
                optionButtons[i].setText(q.options[i]);
                optionButtons[i].setEnabled(true);
            }

            timeLeft = 10;
            timerLabel.setText("Time Left: 10s");
            timer.start();
        } else {
            showResults();
        }
    }

    // Process answer checking and scoring
    private void handleSubmission(boolean isTimeout) {
        timer.stop();
        Question q = quizData.get(currentQuestionIndex);
        int selectedIndex = -1;

        for (int i = 0; i < 4; i++) {
            if (optionButtons[i].isSelected()) {
                selectedIndex = i;
                break;
            }
        }

        if (!isTimeout && selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Please select an answer choice first!");
            timer.start();
            return;
        }

        // 5. Score Calculation
        if (selectedIndex == q.correctOptionIndex) {
            score++;
        }

        currentQuestionIndex++;
        displayQuestion();
    }

    // 6. Final Summary View Screen
    private void showResults() {
        quizPanel.removeAll();
        quizPanel.setLayout(new GridLayout(0, 1, 10, 10));

        JLabel resultTitle = new JLabel("Quiz Completed!", SwingConstants.CENTER);
        resultTitle.setFont(new Font("Arial", Font.BOLD, 20));
        quizPanel.add(resultTitle);

        JLabel finalScoreLabel = new JLabel("Your Score: " + score + " / " + quizData.size(), SwingConstants.CENTER);
        finalScoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        quizPanel.add(finalScoreLabel);

        // Simple Restart UI Action Button
        JButton restartButton = new JButton("Restart Quiz");
        restartButton.addActionListener(e -> {
            currentQuestionIndex = 0;
            score = 0;
            quizPanel.removeAll();
            dispose();
            new QuizApplication().setVisible(true);
        });
        quizPanel.add(restartButton);

        quizPanel.revalidate();
        quizPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new QuizApplication().setVisible(true);
        });
    }
}