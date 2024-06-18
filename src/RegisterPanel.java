import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class RegisterPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JButton backButton;
    private UserManager userManager;
    private MainPanel mainPanel;

    public RegisterPanel(MainPanel mainPanel, UserManager userManager) {
        this.mainPanel = mainPanel;
        this.userManager = userManager;

        setLayout(new BorderLayout());

        // Create the left panel for the image
        JLabel imageLabel = new JLabel(new ImageIcon("E:\\Flag Frenzy Proj\\Flag Frenzy\\images\\cat.jpg")); // Using the provided image
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        // Create the right panel for the form
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
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);  // Add a black line border
        formPanel.setBorder(border);  // Set the border
        formPanel.setPreferredSize(new Dimension(400, 360)); // Adjust size as needed
        formPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        // Add the Register label
        JLabel registerLabel = new JLabel("Register");
        registerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        formPanel.add(registerLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;

        // Add Username field
        gbc.gridx = 1;

        usernameField = new RoundedTextField(15);
        usernameField.setText("Username");
        usernameFieldFocus(usernameField);
        styleTextField(usernameField);
        formPanel.add(usernameField, gbc);

        gbc.gridy++;

        gbc.gridx = 1;

        passwordField = new RoundedPasswordField(15);
        passwordFieldFocus(passwordField);
        styleTextField(passwordField);
        formPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;

        // Add the Sign Up button
        registerButton = createRoundedButton("SIGN UP");
        formPanel.add(registerButton, gbc);

        gbc.gridy++;

        // Add the Back button beside the Sign Up button
        backButton = createRoundedButton("BACK");
        formPanel.add(backButton, gbc);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.showStartPanel();
            }
        });

        // Create a custom panel to paint the gradient background
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

        GridBagConstraints gbcMain = new GridBagConstraints();
        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        gbcMain.insets = new Insets(10, 10, 10, 10);
        gbcMain.anchor = GridBagConstraints.WEST;

        gradientPanel.add(imagePanel, gbcMain);

        gbcMain.gridx = 1;
        gbcMain.anchor = GridBagConstraints.EAST;
        gradientPanel.add(formPanel, gbcMain);

        // Set the gradient panel as the content pane
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
        field.setHorizontalAlignment(JPasswordField.CENTER);
        field.setForeground(Color.WHITE);

        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (new String(field.getPassword()).equals("Password")) {
                    field.setText("");
                    field.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (new String(field.getPassword()).equals("")) {
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

                // Paint the rounded background
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

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

    private void registerUser() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (username.isEmpty() || username.equals("Username")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid username!", "Invalid Input", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (password.isEmpty() || password.equals("Password")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid password!", "Invalid Input", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (userManager.registerUser(username, password)) {
            JOptionPane.showMessageDialog(this, "User registered successfully. Please login.", "Registration Successful", JOptionPane.INFORMATION_MESSAGE);
            mainPanel.showStartPanel();
        } else {
            JOptionPane.showMessageDialog(this, "Username already exists. Please choose another.", "Registration Failed", JOptionPane.ERROR_MESSAGE);
        }
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

    class RoundedTextField extends JTextField {
        public RoundedTextField(int columns) {
            super(columns);
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Background
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);

            // Text
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

            // Background
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);

            // Text
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

