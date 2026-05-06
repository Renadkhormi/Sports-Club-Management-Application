package projectt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrames extends JFrame {

    JTextField phoneField;

    public LoginFrames() {

        setTitle("Login");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        ImageIcon backgroundImage = new ImageIcon("/Users/hayalsaab/Downloads/Gym_12.23-19.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        setContentPane(backgroundLabel); // تعيين الصورة كخلفية
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

        // The panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(7, 1, 5, 5));
        centerPanel.setOpaque(false);

        // Login label
        JLabel titleLabel = new JLabel("Login", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        centerPanel.add(titleLabel);

        // Panel containing the phone components
        JPanel phonePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        phonePanel.setOpaque(false);
        JLabel phoneLabel = new JLabel("Phone:       ");
        phoneLabel.setForeground(Color.WHITE);
        phonePanel.add(phoneLabel);
        phoneField = new JTextField(15);
        phonePanel.add(phoneField);
        centerPanel.add(phonePanel);

        // Panel containing the password components
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        passwordPanel.setOpaque(false);
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordPanel.add(passwordLabel);
        JPasswordField passwordField = new JPasswordField(15);
        passwordPanel.add(passwordField);
        centerPanel.add(passwordPanel);

        // Error panel to place it at the center
        JPanel errorPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        errorPanel.setOpaque(false);
        JLabel errorLabel = new JLabel(" ");
        errorLabel.setForeground(Color.RED);
        errorPanel.add(errorLabel);
        centerPanel.add(errorPanel);

        // Panel containing the sign-up components
        JPanel signUpPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        signUpPanel.setOpaque(false);
        JLabel signLapel = new JLabel("Do not have an account?");
        signLapel.setForeground(Color.WHITE);
        signUpPanel.add(signLapel);

        JButton signUpButton = new JButton("Sign Up");
        signUpPanel.add(signUpButton);
        centerPanel.add(signUpPanel);

        // Panel containing the login button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setOpaque(false);
        JButton loginButton = new JButton("Login");
        bottomPanel.add(loginButton);
        centerPanel.add(bottomPanel);

        // Make the big panel at the center of the frame
        add(centerPanel, BorderLayout.CENTER);

        // Add action listener to the sign-up button
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SignUpFrame(); // Open the SignUpFrame
                dispose(); // Optionally close the LoginFrames window
            }
        });

        // Add action listener to the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String phone = phoneField.getText();
                String password = new String(passwordField.getPassword());

                if (phone.isEmpty() || password.isEmpty()) {
                    errorLabel.setText("Please enter both phone number and password.");
                } else {
                    // التحقق من صحة الهاتف وكلمة المرور
                    boolean isValid = UserDataManager.validateLogin(phone, password);
                    if (isValid) {
                        // تعيين currentUserPhone عند نجاح تسجيل الدخول
                        UserDataManager.currentUserPhone = phone;
                        System.out.println("Current user phone set to: " + phone);

                        new HomePage(); // فتح الصفحة الرئيسية
                        dispose(); // إغلاق نافذة تسجيل الدخول
                    } else {
                        errorLabel.setText("Invalid phone or password.");
                    }
                }
            }
        });

        setVisible(true);
    }

    public String getPhone() {
        return phoneField.getText();
    }
}