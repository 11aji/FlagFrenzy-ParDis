import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitlePanel extends JPanel {
    private MainPanel mainPanel;

    public TitlePanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        setLayout(new GridBagLayout());

        JPanel innerPanel = new JPanel(new BorderLayout());
        innerPanel.setPreferredSize(new Dimension(650, 450));
        innerPanel.setBackground(Color.WHITE);
        innerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JLabel titleLabel = new JLabel("Flag Frenzy", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 60));
        innerPanel.add(titleLabel, BorderLayout.CENTER);

        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        startButton.setPreferredSize(new Dimension(200, 50));
        startButton.setBackground(Color.decode("#2592AF"));
        startButton.setForeground(Color.WHITE);
        startButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        startButton.setFocusPainted(false);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.showStartPanel();
                mainPanel.playMainMusic();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(startButton);
        innerPanel.add(buttonPanel, BorderLayout.SOUTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(innerPanel, gbc);
    }
}
