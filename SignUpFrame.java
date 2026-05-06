package projectt;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpFrame extends JFrame {

    // Constructor
    public SignUpFrame() {
        setTitle("Information Frame");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageIcon backgroundImage = new ImageIcon("/Users/hayalsaab/Downloads/Gym_12.23-19.jpg ");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        setContentPane(backgroundLabel); // تعيين الصورة كخلفية
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(9, 1, 7, 7)); 
        centerPanel.setOpaque(false);

        JLabel titleLabel = new JLabel("Information", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        centerPanel.add(titleLabel);

        // إنشاء الحقول النصية باستخدام دالة مساعدة
        JTextField firstNameField = createLabeledField(centerPanel, "First Name:");
        JTextField lastNameField = createLabeledField(centerPanel, "Last Name:");
        JTextField phoneField = createLabeledField(centerPanel, "Phone:       ");
        JTextField emailField = createLabeledField(centerPanel, "Email:        ");
        JTextField passwodrField = createLabeledField(centerPanel, "Password:");
        JTextField ageField = createLabeledField(centerPanel, "Age:         ");

        // إضافة خيارات الجنس
        JPanel genderPanel = new JPanel();
        genderPanel.setOpaque(false);
        JLabel genderLabel = new JLabel("Gender:");
        genderPanel.add(genderLabel);
        genderLabel.setForeground(Color.WHITE);
        JRadioButton maleButton = new JRadioButton("Male");
        maleButton.setForeground(Color.WHITE);
        JRadioButton femaleButton = new JRadioButton("Female");
        femaleButton.setForeground(Color.WHITE);
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        centerPanel.add(genderPanel);
        maleButton.setOpaque(false);
        femaleButton.setOpaque(false);

        // إضافة زر الحفظ
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); 
        bottomPanel.setOpaque(false);
        JButton saveButton = new JButton("Save");
        bottomPanel.add(saveButton);
        centerPanel.add(bottomPanel);

        // إضافة اللوحة إلى الإطار
        add(centerPanel, BorderLayout.CENTER);
        setVisible(true);


        // إضافة الحدث عند الضغط على زر الحفظ
        saveButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();
        String age = ageField.getText();
        String password = passwodrField.getText();
        String gender = maleButton.isSelected() ? "Male" : "Female";

        if (firstName.isEmpty() || lastName.isEmpty() || phone.isEmpty() || email.isEmpty() || age.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter all of the required information!");
        } else if (password.length() < 5) {
            JOptionPane.showMessageDialog(null, "Password should be at least 5 characters!");
        } else {
            User saveUserData = new User(firstName, lastName, phone, email, password, age, gender);
            // حفظ البيانات
            UserDataManager.saveUserData(saveUserData);

            JOptionPane.showMessageDialog(null, "User registered successfully!");

            HomePage homePage = new HomePage();
            homePage.setVisible(true);
            dispose();
        }
    }
});


    }

    // دالة مساعدة لإنشاء الحقول النصية
    private JTextField createLabeledField(JPanel panel, String label) {
        JPanel subPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        subPanel.setOpaque(false);
        JLabel jLabel = new JLabel(label);
        jLabel.setForeground(Color.WHITE);
        subPanel.add(jLabel);
        JTextField textField = new JTextField(15);
        subPanel.add(textField);
        panel.add(subPanel);
        return textField;
    }
}
