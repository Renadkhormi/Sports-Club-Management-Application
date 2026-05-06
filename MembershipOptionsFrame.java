package projectt;

import projectt.HomePage;
import static projectt.UserDataManager.currentUserPhone;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MembershipOptionsFrame extends JFrame {

    public MembershipOptionsFrame() {
        setTitle("Membership :");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // تعيين الخلفية
        ImageIcon backgroundImage = new ImageIcon("/Users/hayalsaab/Downloads/Gym_12.23-19.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        setContentPane(backgroundLabel);
        setLayout(new BorderLayout());

        // إعداد لوحة الاختيارات
        JPanel optionsPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        optionsPanel.setOpaque(false);

        // إضافة عنوان لاختيار العضوية
        JLabel membershipLabel = new JLabel("Choose Membership :", SwingConstants.LEFT);
        membershipLabel.setFont(new Font("Arial", Font.BOLD, 16));
        membershipLabel.setForeground(Color.WHITE);
        optionsPanel.add(membershipLabel);

        // إعداد أزرار الاختيارات للعضوية
        JRadioButton regularButton = new JRadioButton("Regular");
        regularButton.setForeground(Color.WHITE);
        JRadioButton standardButton = new JRadioButton("Standard");
        standardButton.setForeground(Color.WHITE);
        JRadioButton plusButton = new JRadioButton("Plus");
        plusButton.setForeground(Color.WHITE);
        regularButton.setOpaque(false);
        standardButton.setOpaque(false);
        plusButton.setOpaque(false);

        ButtonGroup membershipTypeGroup = new ButtonGroup();
        membershipTypeGroup.add(regularButton);
        membershipTypeGroup.add(standardButton);
        membershipTypeGroup.add(plusButton);

        optionsPanel.add(regularButton);
        optionsPanel.add(standardButton);
        optionsPanel.add(plusButton);

        // إضافة عنوان لاختيار فترة العضوية
        JLabel timeLabel = new JLabel("Membership Time:", SwingConstants.LEFT);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        timeLabel.setForeground(Color.WHITE);
        optionsPanel.add(timeLabel);

        // إعداد أزرار الاختيارات للفترة
        JRadioButton threeMonthsButton = new JRadioButton("3 Months");
        threeMonthsButton.setForeground(Color.WHITE);
        JRadioButton sixMonthsButton = new JRadioButton("6 Months");
        sixMonthsButton.setForeground(Color.WHITE);
        JRadioButton twelveMonthsButton = new JRadioButton("12 Months");
        twelveMonthsButton.setForeground(Color.WHITE);
        threeMonthsButton.setOpaque(false);
        sixMonthsButton.setOpaque(false);
        twelveMonthsButton.setOpaque(false);

        ButtonGroup membershipTimeGroup = new ButtonGroup();
        membershipTimeGroup.add(threeMonthsButton);
        membershipTimeGroup.add(sixMonthsButton);
        membershipTimeGroup.add(twelveMonthsButton);

        optionsPanel.add(threeMonthsButton);
        optionsPanel.add(sixMonthsButton);
        optionsPanel.add(twelveMonthsButton);

        // إعداد شريط القوائم
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Edit");
        JMenuItem item1 = new JMenuItem("Unsubscribe");
        JMenuItem item2 = new JMenuItem("Subscription extension");
        JMenuItem item3 = new JMenuItem("Exit");

        menu.add(item1);
        menu.add(item2);
        menu.addSeparator();
        menu.add(item3);

        menuBar.add(menu);
        setJMenuBar(menuBar);

        // إضافة لوحة الاختيارات إلى الوسط
        add(optionsPanel, BorderLayout.CENTER);

        // إعداد لوحة الأزرار
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        JButton saveButton = new JButton("Save");
        buttonPanel.add(saveButton);

        // إضافة لوحة الأزرار إلى الأسفل
        add(buttonPanel, BorderLayout.SOUTH);

        // إضافة معالج الحدث للزر
        saveButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String membershipType = "";
        String membershipDuration = "";

        if (regularButton.isSelected()) {
            membershipType = "Regular";
        } else if (standardButton.isSelected()) {
            membershipType = "Standard";
        } else if (plusButton.isSelected()) {
            membershipType = "Plus";
        }

        if (threeMonthsButton.isSelected()) {
            membershipDuration = "3 Months";
        } else if (sixMonthsButton.isSelected()) {
            membershipDuration = "6 Months";
        } else if (twelveMonthsButton.isSelected()) {
            membershipDuration = "12 Months";
        }

        if (membershipType.isEmpty() || membershipDuration.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select membership type and duration.");
        } else {
            // استدعاء الدالة لتحديث العضوية في البيانات
            User userData = UserDataManager.readUserData(UserDataManager.currentUserPhone);
            String userPhone = userData.getPhone();

            boolean isUpdated = UserDataManager.updateMembership(userPhone, membershipType, membershipDuration);
            if (isUpdated) {
                JOptionPane.showMessageDialog(null, "Membership updated successfully.");
                HomePage homePage = new HomePage();
                homePage.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update membership. User not found.");
            }
        }
    }
});

        
                // إضافة ActionListener إلى العنصر الأول "Unsubscribe"
       item1.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        int confirmation = JOptionPane.showConfirmDialog(
                null, 
                "Are you sure you want to unsubscribe?", 
                "Confirmation", 
                JOptionPane.YES_NO_OPTION
        );
        if (confirmation == JOptionPane.YES_OPTION) {
            // استدعاء دالة الحذف
            boolean isDeleted = UserDataManager.deleteUserData(UserDataManager.currentUserPhone);

            if (isDeleted) {
                JOptionPane.showMessageDialog(null, "You have successfully unsubscribed.");
                new LoginFrames(); // فتح صفحة تسجيل الدخول
                dispose(); // إغلاق نافذة العضوية
            } else {
                JOptionPane.showMessageDialog(null, "Failed to unsubscribe. User not found.");
            }
        }
    }
});

       

        // إضافة ActionListener إلى العنصر الثاني "Subscription extension"
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // فتح نافذة تمديد الاشتراك
                String[] options = {"3 Months", "6 Months", "12 Months"};
                String extension = (String) JOptionPane.showInputDialog(
                        null,
                        "Choose extension period:",
                        "Subscription Extension",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        options,
                        options[0]
                );
                
            }
        });

        // إضافة ActionListener إلى العنصر الثالث "Exit"
        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    System.exit(0); // إنهاء البرنامج
                }
            }
        );

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MembershipOptionsFrame::new);
    }
}