import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GamePanel extends JPanel {
    private JLabel flagLabel;
    private JTextField answerField;
    private JButton submitButton;
    private JLabel scoreLabel;
    private int score;
    private int questionsAnswered;
    private String difficulty;
    private FlagDatabase flagDatabase;
    private List<Flag> currentFlags;
    private Flag currentFlag;
    private volatile boolean running;
    private MainPanel mainPanel;

    public GamePanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;  // Store the reference to the MainPanel
        setLayout(new BorderLayout());

        flagDatabase = new FlagDatabase();
        score = 0;
        questionsAnswered = 0;

        flagLabel = new JLabel("", SwingConstants.CENTER);
        add(flagLabel, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        answerField = new JTextField(20);
        submitButton = new JButton("Submit");

        inputPanel.add(answerField);
        inputPanel.add(submitButton);

        add(inputPanel, BorderLayout.SOUTH);

        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
        add(scoreLabel, BorderLayout.NORTH);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
            }
        });
    }

    public void startGame(String difficulty) {
        this.difficulty = difficulty;
        score = 0;
        questionsAnswered = 0;
        currentFlags = flagDatabase.getFlags(difficulty);
        loadNextFlag();
        running = true;
    }

    private void loadNextFlag() {
        if (questionsAnswered < 10) {
            SwingUtilities.invokeLater(() -> {
                int index = (int) (Math.random() * currentFlags.size());
                currentFlag = currentFlags.get(index);
                flagLabel.setIcon(new ImageIcon(currentFlag.getImagePath()));
            });
        } else {
            showAnalysis();
            running = false;
        }
    }

    private void checkAnswer() {
        String answer = answerField.getText().trim().toLowerCase();
        if (answer.equals(currentFlag.getName().toLowerCase())) {
            score++;
        }
        questionsAnswered++;
        scoreLabel.setText("Score: " + score);
        answerField.setText("");
        loadNextFlag();
    }

    private void showAnalysis() {
        String analysis = PerformanceAnalyzer.getDetailedFeedback(score);
        if (difficulty.equals("Easy") && score >= 7) {
            mainPanel.unlockMedium();
        }
        if (difficulty.equals("Medium") && score >= 8) {
            mainPanel.unlockHard();
        }

        SwingUtilities.invokeLater(() -> {
            JDialog analysisDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Performance Analysis", true);
            analysisDialog.setSize(400, 300);
            analysisDialog.setLayout(new BorderLayout());

            JTextArea analysisTextArea = new JTextArea(analysis);
            analysisTextArea.setWrapStyleWord(true);
            analysisTextArea.setLineWrap(true);
            analysisTextArea.setEditable(false);

            JScrollPane scrollPane = new JScrollPane(analysisTextArea);
            analysisDialog.add(scrollPane, BorderLayout.CENTER);

            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(e -> analysisDialog.dispose());
            analysisDialog.add(closeButton, BorderLayout.SOUTH);

            analysisDialog.setVisible(true);
        });
    }
}
