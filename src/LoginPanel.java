import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;
    private UserManager userManager;
    private MainPanel mainPanel;
    private SoundEffect buttonSound;

    public LoginPanel(MainPanel mainPanel, UserManager userManager) {
        this.mainPanel = mainPanel;
        this.userManager = userManager;
        this.buttonSound = new SoundEffect("src/click.wav");

        setLayout(new BorderLayout());

        JLabel imageLabel = new JLabel(new ImageIcon("images\\123.png"));
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setPreferredSize(new Dimension(350, 436));
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
        formPanel.setPreferredSize(new Dimension(350, 436));
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
        usernameField = new RoundedTextField(15);
        usernameField.setText("Username");
        usernameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonSound.play();
                loginUser();
            }
        });
        usernameFieldFocus(usernameField);
        styleTextField(usernameField);
        formPanel.add(usernameField, gbc);

        gbc.gridy++;

        gbc.gridx = 1;
        passwordField = new RoundedPasswordField(15);
        passwordFieldFocus(passwordField);
        passwordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonSound.play();
                loginUser();
            }
        });
        styleTextField(passwordField);
        formPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;

        loginButton = createRoundedButton("LOG IN");
        formPanel.add(loginButton, gbc);

        gbc.gridy++;

        backButton = createRoundedButton("BACK");
        formPanel.add(backButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonSound.play();
                loginUser();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonSound.play();
                mainPanel.showStartPanel();
            }
        });

        JPanel combinedPanel = new JPanel(new BorderLayout());
        combinedPanel.add(imagePanel, BorderLayout.WEST);
        combinedPanel.add(formPanel, BorderLayout.EAST);
        combinedPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

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
        gbcMain.anchor = GridBagConstraints.CENTER;

        gradientPanel.add(combinedPanel, gbcMain);

        add(gradientPanel, BorderLayout.CENTER);
    }

    private void styleTextField(JTextField textField) {
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setForeground(Color.WHITE);
        textField.setBackground(Color.decode("#2592AF"));
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#2592AF"), 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        textField.setPreferredSize(new Dimension(200, 30));
    }

    private void usernameFieldFocus(JTextField field) {
        usernameField.setText("Username");
        usernameField.setHorizontalAlignment(JTextField.CENTER);
        usernameField.setForeground(Color.WHITE);

        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals("Username")) {
                    field.setText("");
                    field.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().equals("")) {
                    field.setText("Username");
                    field.setForeground(Color.WHITE);
                }
            }
        });
    }

    private void passwordFieldFocus(JPasswordField field) {
        passwordField.setText("Password");
        passwordField.setHorizontalAlignment(JTextField.CENTER);
        passwordField.setForeground(Color.WHITE);

        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(field.getPassword()).equals("Password")) {
                    field.setText("");
                    field.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(field.getPassword()).equals("")) {
                    field.setText("Password");
                    field.setForeground(Color.WHITE);
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

                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

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
        button.setOpaque(false);
        button.setBorder(new RoundedBorder(15));
        return button;
    }

    private void loginUser() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        User user = userManager.loginUser(username, password);

        if (username.isEmpty() || username.equals("Username")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid username!", "Invalid Input", JOptionPane.WARNING_MESSAGE);
            return;
        }

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

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        public boolean isBorderOpaque() { return false; }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }

    class RoundedTextField extends JTextField {
        public RoundedTextField(int columns) {
            super(columns);
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);

            super.paintComponent(g2);
            g2.dispose();
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getForeground());
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
            g2.dispose();
        }

        @Override
        public void setBorder(Border border) {
            // No border
        }
    }

    class RoundedPasswordField extends JPasswordField {
        public RoundedPasswordField(int columns) {
            super(columns);
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);

            super.paintComponent(g2);
            g2.dispose();
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getForeground());
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
            g2.dispose();
        }

        @Override
        public void setBorder(Border border) {
            // No border
        }
    }
}
