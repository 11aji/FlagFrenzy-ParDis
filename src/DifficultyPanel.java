import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DifficultyPanel extends JPanel {
    private MainPanel mainPanel;

    public DifficultyPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;

        setLayout(new GridLayout(4, 1));

        JLabel label = new JLabel("Select Difficulty", SwingConstants.CENTER);
        label.setFont(new Font("Serif", Font.BOLD, 24));
        add(label);

        JButton easyButton = new JButton("Easy");
        JButton mediumButton = new JButton("Medium");
        JButton hardButton = new JButton("Hard");

        add(easyButton);
        add(mediumButton);
        add(hardButton);

        easyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.showGamePanel("Easy");
            }
        });

        mediumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mainPanel.isMediumUnlocked()) {
                    mainPanel.showGamePanel("Medium");
                } else {
                    JOptionPane.showMessageDialog(DifficultyPanel.this, "Medium difficulty is locked. Complete Easy with 7/10 to unlock.", "Locked", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        hardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mainPanel.isHardUnlocked()) {
                    mainPanel.showGamePanel("Hard");
                } else {
                    JOptionPane.showMessageDialog(DifficultyPanel.this, "Hard difficulty is locked. Complete Medium with 8/10 to unlock.", "Locked", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }
}
