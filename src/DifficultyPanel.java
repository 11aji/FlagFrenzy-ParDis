import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DifficultyPanel extends JPanel {
    private MainPanel mainPanel;

    public DifficultyPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;

        setLayout(new GridBagLayout()); // Use GridBagLayout to center the inner panel

        JPanel innerPanel = new JPanel();
        innerPanel.setPreferredSize(new Dimension(650, 450));
        innerPanel.setLayout(new GridLayout(4, 1));
        innerPanel.setBackground(Color.WHITE); // Set background to white
        innerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Add black outline

        JLabel label = new JLabel("Select Difficulty", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 40));
        innerPanel.add(label);

        JPanel easyPanel = createButtonPanel("Easy", e -> mainPanel.showGamePanel("Easy"));
        innerPanel.add(easyPanel);

        JPanel mediumPanel = createButtonPanel("Medium", e -> {
            if (mainPanel.isMediumUnlocked()) {
                mainPanel.showGamePanel("Medium");
            } else {
                JOptionPane.showMessageDialog(DifficultyPanel.this, "Medium difficulty is locked. Complete Easy with 7/10 to unlock.", "Locked", JOptionPane.WARNING_MESSAGE);
            }
        });
        innerPanel.add(mediumPanel);

        JPanel hardPanel = createButtonPanel("Hard", e -> {
            if (mainPanel.isHardUnlocked()) {
                mainPanel.showGamePanel("Hard");
            } else {
                JOptionPane.showMessageDialog(DifficultyPanel.this, "Hard difficulty is locked. Complete Medium with 8/10 to unlock.", "Locked", JOptionPane.WARNING_MESSAGE);
            }
        });
        innerPanel.add(hardPanel);

        // Add the innerPanel to the DifficultyPanel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
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
}