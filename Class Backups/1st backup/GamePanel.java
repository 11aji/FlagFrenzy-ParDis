import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {
    private JLabel flagLabel;
    private JTextField answerField;
    private JButton submitButton;
    private JLabel scoreLabel;
    private int score;
    private FlagDatabase flagDatabase;
    private Flag currentFlag;
    private volatile boolean running;

    public GamePanel() {
        setLayout(new BorderLayout());

        flagDatabase = new FlagDatabase();
        score = 0;

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

        startGame();
    }

    private void startGame() {
        running = true;
        new Thread(() -> {
            while (running) {
                loadNextFlag();
                try {
                    Thread.sleep(5000); // 5 seconds delay between flags
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void loadNextFlag() {
        SwingUtilities.invokeLater(() -> {
            currentFlag = flagDatabase.getRandomFlag();
            flagLabel.setIcon(new ImageIcon(currentFlag.getImagePath()));
        });
    }

    private void checkAnswer() {
        String answer = answerField.getText().trim().toLowerCase();
        if (answer.equals(currentFlag.getName().toLowerCase())) {
            score++;
        } else {
            score--;
        }
        scoreLabel.setText("Score: " + score);
        answerField.setText("");
        loadNextFlag();
    }

    public void stopGame() {
        running = false;
    }
}
