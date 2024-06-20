import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPanel extends JPanel {
    private MainPanel mainPanel;
    private JButton loginButton;
    private JButton registerButton;
    private JButton exitButton;

    public StartPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;

        setLayout(new BorderLayout());

        JPanel gradientPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();

                // Create the gradient paint
                Color startColor = Color.decode("#7AD2EA");
                Color endColor = Color.decode("#0F597E");
                GradientPaint gp = new GradientPaint(0, 0, startColor, 0, height, endColor);

                // Paint the background with the gradient
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, width, height);
            }
        };
        gradientPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;

        JLabel titleLabel = new JLabel("Welcome to Flag Frenzy!", SwingConstants.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Calculate text bounds
                FontMetrics fm = g2d.getFontMetrics();
                Rectangle textBounds = fm.getStringBounds(getText(), g2d).getBounds();
                int x = (getWidth() - textBounds.width) / 2;
                int y = (getHeight() - textBounds.height) / 2 + fm.getAscent();

                // Draw the outline
                g2d.setFont(getFont());
                g2d.setColor(Color.BLACK);
                g2d.drawString(getText(), x + 1, y + 1);
                g2d.drawString(getText(), x - 1, y - 1);
                g2d.drawString(getText(), x + 1, y - 1);
                g2d.drawString(getText(), x - 1, y + 1);

                // Draw the text
                g2d.setColor(getForeground());
                g2d.drawString(getText(), x, y);
            }
        };
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridy = 0;
        gradientPanel.add(titleLabel, gbc);

        gbc.gridy++;

        loginButton = createRoundedButton("Log In");
        gradientPanel.add(loginButton, gbc);

        gbc.gridy++;

        registerButton = createRoundedButton("Sign Up");
        gradientPanel.add(registerButton, gbc);

        gbc.gridy++;

        exitButton = createRoundedButton("Exit");
        gradientPanel.add(exitButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.showLoginPanel();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.showRegisterPanel();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(mainPanel, "Are you sure you want to exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        // Set the gradient panel as the content pane
        add(gradientPanel, BorderLayout.CENTER);
    }

    private JButton createRoundedButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

                super.paintComponent(g2);

                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getForeground());
                g2.drawRoundRect(0, 0, getWidth(), getHeight() - 1, 10, 10);
            }

            @Override
            public void setContentAreaFilled(boolean b) {
                // Do nothing
            }
        };
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setBackground(Color.decode("#2592AF"));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(175, 45));
        button.setOpaque(false);
        button.setBorder(new RoundedBorder(15));
        return button;
    }

    class RoundedBorder implements Border {
        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        public boolean isBorderOpaque() {
            return false;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
}
