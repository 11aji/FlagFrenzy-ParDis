import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JButton loginButton;
    private JButton backButton;
    private UserManager userManager;
    private MainPanel mainPanel;

    public LoginPanel(MainPanel mainPanel, UserManager userManager) {
        this.mainPanel = mainPanel;
        this.userManager = userManager;

        setLayout(new BorderLayout());

        JLabel imageLabel = new JLabel(new ImageIcon("E:\\Flag Frenzy Proj\\Flag Frenzy\\images\\cat.jpg"));
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        JPanel formPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(getBackground());
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            }
        };
        formPanel.setBackground(Color.WHITE);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        formPanel.setBorder(border);
        formPanel.setPreferredSize(new Dimension(400, 360));
        formPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel loginLabel = new JLabel("Login");
        loginLabel.setFont(new Font("Arial", Font.BOLD, 24));
        formPanel.add(loginLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;

        gbc.gridx = 1;
        usernameField = new JTextField(15);
        usernameField.setText("Username");
        usernameFieldFocus(usernameField);
        styleTextField(usernameField);
        formPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;

        loginButton = createRoundedButton("Login");
        formPanel.add(loginButton, gbc);

        gbc.gridy++;

        backButton = createRoundedButton("Back");
        formPanel.add(backButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.showStartPanel();
            }
        });
        JPanel gradientPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();

                Color startColor = Color.decode("#7AD2EA");
                Color endColor = Color.decode("#0F587E");
                GradientPaint gp = new GradientPaint(0, 0, startColor, 0, height, endColor);

                g2d.setPaint(gp);
                g2d.fillRect(0, 0, width, height);
            }
        };
        gradientPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbcMain = new GridBagConstraints();
        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        gbcMain.insets = new Insets(10, 10, 10, 10);
        gbcMain.anchor = GridBagConstraints.WEST;

        gradientPanel.add(imagePanel, gbcMain);

        gbcMain.gridx = 1;
        gbcMain.anchor = GridBagConstraints.EAST;
        gradientPanel.add(formPanel, gbcMain);

        add(gradientPanel, BorderLayout.CENTER);
    }

    private void styleTextField(JTextField textField) {
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        textField.setPreferredSize(new Dimension(200, 30));
    }

    private void usernameFieldFocus(JTextField field) {
        usernameField.setText("Username");
        usernameField.setHorizontalAlignment(JTextField.CENTER);
        usernameField.setForeground(Color.decode("#0F597E"));

        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals("Username")) {
                    field.setText("");
                    field.setForeground(Color.decode("#0F597E"));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().equals("")) {
                    field.setText("Username");
                    field.setForeground(Color.decode("#0F5973"));
                }
            }
        });
    }

    private JButton createRoundedButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Paint the rounded background
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);


                // Paint the text
                super.paintComponent(g2);

                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getForeground());
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
                g2.dispose();
            }

            @Override
            public void setContentAreaFilled(boolean b) {
                // Do nothing here
            }
        };
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(Color.decode("#2592AF"));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(120, 30));
        button.setOpaque(false); // Make button non-opaque
        button.setBorder(new RoundedBorder(15));
        return button;
    }

    private void loginUser() {
        String username = usernameField.getText().trim();
        User user = userManager.loginUser(username);
        if (user != null) {
            mainPanel.setCurrentUser(user);
            mainPanel.showDifficultyPanel();
        } else {
            JOptionPane.showMessageDialog(this, "User not found. Please register.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    class RoundedBorder implements Border {
        private int radius;

        RoundedBorder(int radius) { this.radius = radius; }

        public Insets getBorderInsets(Component c ) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        public boolean isBorderOpaque() { return false; }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
}