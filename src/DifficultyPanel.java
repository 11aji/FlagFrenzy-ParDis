import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DifficultyPanel extends JPanel {
    private MainPanel mainPanel;
    private SoundEffect buttonSound;

    public DifficultyPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;

        setLayout(new GridBagLayout()); // Use GridBagLayout to center the inner panel

        JPanel innerPanel = new JPanel();
        innerPanel.setPreferredSize(new Dimension(650, 450));
        innerPanel.setLayout(new GridLayout(4, 1)); // Adjusted to 4 rows
        innerPanel.setBackground(Color.WHITE); // Set background to white
        innerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Add black outline

        JLabel label = new JLabel("Select a difficulty", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 40));
        innerPanel.add(label);

        buttonSound = new SoundEffect("src/click.wav");

        JPanel easyPanel = createButtonPanel("Easy", e -> {
            buttonSound.play();
            mainPanel.showGamePanel("Easy");
        });
        innerPanel.add(easyPanel);

        JPanel mediumPanel = createButtonPanel("Medium", e -> {
            buttonSound.play();
            if (mainPanel.isMediumUnlocked()) {
                mainPanel.showGamePanel("Medium");
            } else {
                JOptionPane.showMessageDialog(DifficultyPanel.this, "Medium difficulty is locked. Complete Easy with 7/10 to unlock.", "Locked", JOptionPane.WARNING_MESSAGE);
            }
        });
        innerPanel.add(mediumPanel);

        JPanel hardPanel = createButtonPanel("Hard", e -> {
            buttonSound.play();
            if (mainPanel.isHardUnlocked()) {
                mainPanel.showGamePanel("Hard");
            } else {
                JOptionPane.showMessageDialog(DifficultyPanel.this, "Hard difficulty is locked. Complete Medium with 8/10 to unlock.", "Locked", JOptionPane.WARNING_MESSAGE);
            }
        });
        innerPanel.add(hardPanel);

        // Add the "Go Back" label at the top
        JLabel goBackLabel = new JLabel("< Sign Out");
        goBackLabel.setFont(new Font("Arial", Font.BOLD, 18));
        goBackLabel.setForeground(Color.white); // Set to the same blue color as the buttons
        goBackLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        goBackLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonSound.play();
                mainPanel.showLoginPanel();
            }
        });

        // Add the "How to Play" button
        JButton howToPlayButton = new JButton("How to Play");
        howToPlayButton.setFont(new Font("Arial", Font.BOLD, 18));
        howToPlayButton.setBackground(Color.decode("#2592AF"));
        howToPlayButton.setForeground(Color.WHITE);
        howToPlayButton.setFocusPainted(false);
        howToPlayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHowToPlay();
            }
        });

        GridBagConstraints backGbc = new GridBagConstraints();
        backGbc.gridx = 0;
        backGbc.gridy = 0;
        backGbc.anchor = GridBagConstraints.NORTHWEST;
        backGbc.insets = new Insets(10, 10, 10, 10); // Add some padding
        add(goBackLabel, backGbc);

        GridBagConstraints howToPlayGbc = new GridBagConstraints();
        howToPlayGbc.gridx = 1;
        howToPlayGbc.gridy = 0;
        howToPlayGbc.anchor = GridBagConstraints.NORTHEAST;
        howToPlayGbc.insets = new Insets(10, 10, 10, 10); // Add some padding
        add(howToPlayButton, howToPlayGbc);

        // Add the innerPanel to the DifficultyPanel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Span both columns
        gbc.anchor = GridBagConstraints.CENTER;
        add(innerPanel, gbc);
    }

    private JPanel createButtonPanel(String text, ActionListener actionListener) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel.setOpaque(false);

        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.setPreferredSize(new Dimension(350, 69));
        button.setBackground(Color.decode("#2592AF"));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        button.setFocusPainted(false);

        // Make button edges round
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setFocusPainted(false);

        button.addActionListener(actionListener);
        panel.add(button);
        return panel;
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

    private void showHowToPlay() {
        JFrame howToPlayFrame = new JFrame("How to Play");
        howToPlayFrame.setSize(400, 300);
        howToPlayFrame.setLayout(new BorderLayout());

        JTextArea instructionsTextArea = new JTextArea();
        instructionsTextArea.setText("How to Play:\n\n" +
                "1. Select a difficulty level to start the game.\n" +
                "2. A flag will be displayed, and you must select the correct country name from the options provided.\n" +
                "3. If you select the correct answer, your score will increase.\n" +
                "4. If you select the wrong answer, the correct answer will be highlighted.\n" +
                "5. You have 10 seconds to answer each question.\n" +
                "6. After 10 questions, your performance will be analyzed.\n" +
                "7. Unlock higher difficulty levels by scoring well on easier levels.\n" +
                "8. Have fun and learn about the flags of the world!");

        instructionsTextArea.setWrapStyleWord(true);
        instructionsTextArea.setLineWrap(true);
        instructionsTextArea.setEditable(false);

        howToPlayFrame.add(new JScrollPane(instructionsTextArea), BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> howToPlayFrame.dispose());
        howToPlayFrame.add(closeButton, BorderLayout.SOUTH);

        howToPlayFrame.setLocationRelativeTo(this);
        howToPlayFrame.setVisible(true);
    }
}
