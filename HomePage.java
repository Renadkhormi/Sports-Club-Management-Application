package projectt;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame {

    public HomePage() {
        setTitle("Home Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300); // حجم الفريم
        setLocationRelativeTo(null);

        ImageIcon backgroundImage = new ImageIcon("/Users/hayalsaab/Downloads/Gym_12.23-19.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        setContentPane(backgroundLabel); // تعيين الصورة كخلفية
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

        JLabel titleLabel = new JLabel("Home Page");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(4, 3, 20, 20));
        buttons.setOpaque(false);

        JButton button1 = new JButton("Information");
        JButton button2 = new JButton("Activities");
        JButton button3 = new JButton("Membership");

        buttons.add(titleLabel);
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);

        add(buttons, BorderLayout.CENTER);

        // ActionListener لزر "Information" (اختياري)
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InformationFrame(); // Open the InformationFrame
                dispose(); // Optionally close the HomePage window
            }
        });

        // ActionListener لزر "Activities"
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GymActivities activitiesPage = new GymActivities(); // الانتقال لصفحة GymActivities
                activitiesPage.setVisible(true);
                dispose(); // إغلاق صفحة HomePage
            }
        });

        // ActionListener لزر "Membership"
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MembershipOptionsFrame membershipPage = new MembershipOptionsFrame(); // الانتقال لصفحة MembershipOptionsFrame
                membershipPage.setVisible(true);
                
                dispose(); // إغلاق صفحة HomePage
            }
        });

        setVisible(true);
    }

}