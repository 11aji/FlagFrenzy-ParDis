import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GamePanel extends JPanel {
    private JLabel flagLabel;
    private JButton[] choiceButtons;
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
        this.mainPanel = mainPanel;
        setLayout(new BorderLayout());

        flagDatabase = new FlagDatabase();
        score = 0;
        questionsAnswered = 0;

        // Main panel with gradient background
        JPanel mainInnerPanel = new JPanel(new GridBagLayout());
        mainInnerPanel.setOpaque(false);

        // White panel with black outline
        JPanel innerPanel = new JPanel();
        innerPanel.setPreferredSize(new Dimension(650, 450));
        innerPanel.setLayout(new BorderLayout());
        innerPanel.setBackground(Color.WHITE);
        innerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        flagLabel = new JLabel("", SwingConstants.CENTER);
        flagLabel.setPreferredSize(new Dimension(300, 200));
        flagLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        innerPanel.add(flagLabel, BorderLayout.CENTER);

        // Choice buttons
        JPanel choicePanel = new JPanel(new GridLayout(2, 2, 10, 10));
        choicePanel.setOpaque(false);
        choiceButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            choiceButtons[i] = new JButton();
            choiceButtons[i].setFont(new Font("Arial", Font.BOLD, 24));
            choiceButtons[i].setBackground(Color.decode("#2592AF"));
            choiceButtons[i].setForeground(Color.WHITE);
            choiceButtons[i].setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.BLACK, 2),
                    BorderFactory.createEmptyBorder(5, 15, 5, 15)));
            choiceButtons[i].setFocusPainted(false);
            choiceButtons[i].setContentAreaFilled(false);
            choiceButtons[i].setOpaque(true);
            choiceButtons[i].addActionListener(new ChoiceButtonListener());
            choicePanel.add(choiceButtons[i]);
        }

        choicePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(choicePanel, BorderLayout.CENTER);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));

        innerPanel.add(bottomPanel, BorderLayout.SOUTH);

        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
        innerPanel.add(scoreLabel, BorderLayout.NORTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        mainInnerPanel.add(innerPanel, gbc);

        add(mainInnerPanel, BorderLayout.CENTER);
    }

    public void startGame(String difficulty) {
        this.difficulty = difficulty;
        score = 0;
        questionsAnswered = 0;
        scoreLabel.setText("Score: 0");
        currentFlags = flagDatabase.getFlags(difficulty);
        loadNextFlag();
        running = true;
    }

    private void loadNextFlag() {
        if (questionsAnswered < 10) {
            SwingUtilities.invokeLater(() -> {
                int index = (int) (Math.random() * currentFlags.size());
                currentFlag = currentFlags.get(index);
                flagLabel.setIcon(new ImageIcon(new ImageIcon(currentFlag.getImagePath()).getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH)));
                setChoiceButtons();
            });
        } else {
            showAnalysis();
            running = false;
        }
    }

    private void setChoiceButtons() {
        int correctIndex = (int) (Math.random() * 4);
        choiceButtons[correctIndex].setText(currentFlag.getName());

        for (int i = 0; i < 4; i++) {
            if (i != correctIndex) {
                int randomIndex;
                do {
                    randomIndex = (int) (Math.random() * currentFlags.size());
                } while (currentFlags.get(randomIndex).equals(currentFlag));
                choiceButtons[i].setText(currentFlags.get(randomIndex).getName());
            }
        }
    }

    private void checkAnswer(String selectedAnswer) {
        if (selectedAnswer.equals(currentFlag.getName())) {
            score++;
        }
        questionsAnswered++;
        scoreLabel.setText("Score: " + score);
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
            closeButton.addActionListener(e -> {
                analysisDialog.dispose();
                mainPanel.showDifficultyPanel();
            });
            analysisDialog.add(closeButton, BorderLayout.SOUTH);

            analysisDialog.setVisible(true);
        });
    }

    private class ChoiceButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton sourceButton = (JButton) e.getSource();
            String selectedAnswer = sourceButton.getText();
            new CheckAnswerTask(selectedAnswer).execute();
        }
    }

    private class CheckAnswerTask extends SwingWorker<Void, Void> {
        private String selectedAnswer;

        public CheckAnswerTask(String selectedAnswer) {
            this.selectedAnswer = selectedAnswer;
        }

        @Override
        protected Void doInBackground() throws Exception {
            Thread.sleep(100);
            return null;
        }

        @Override
        protected void done() {
            checkAnswer(selectedAnswer);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();
        Color color1 = Color.decode("#7AD2EA");
        Color color2 = Color.decode("#0F597E");
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);
    }
}