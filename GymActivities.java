package projectt;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;

public class GymActivities extends JFrame {

    public GymActivities() {
        setTitle("Gym Activities");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setOpaque(false);

        ImageIcon backgroundImage = new ImageIcon("/Users/hayalsaab/Downloads/Gym_12.23-19.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        setContentPane(backgroundLabel); 
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new GridLayout(0, 1, 5, 5));
        checkBoxPanel.setOpaque(false);

        String[] activities = {
            "Yoga", "CrossFit", "Zumba", "Personal Training", "Pilates", 
            "Strength Training", "Cardio Kickboxing",
            "Boxing", "Stretching", "Meditation", 
            "Running Club"
        };

        JCheckBox[] checkBoxes = new JCheckBox[activities.length];
       
        for (int i = 0; i < activities.length; i++) {
            checkBoxes[i] = new JCheckBox(activities[i]);
            checkBoxes[i].setFont(new Font("Arial", Font.PLAIN, 18));
            checkBoxPanel.add(checkBoxes[i]);
        }
        

        JScrollPane scrollPane = new JScrollPane(checkBoxPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setOpaque(false);

        JButton bookButton = new JButton("Book Now");
        bookButton.setEnabled(false);
        mainPanel.add(bookButton, BorderLayout.SOUTH);

        for (JCheckBox checkBox : checkBoxes) {
            checkBox.addActionListener(e -> {
                boolean atLeastOneSelected = false;
                for (JCheckBox cb : checkBoxes) {
                    if (cb.isSelected()) {
                        atLeastOneSelected = true;
                        break;
                    }
                }
                bookButton.setEnabled(atLeastOneSelected);
            });
        }

        // Book button action
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder selectedActivities = new StringBuilder("You have booked:\n");
                for (JCheckBox checkBox : checkBoxes) {
                    if (checkBox.isSelected()) {
                        selectedActivities.append("- ").append(checkBox.getText()).append("\n");
                    }
                }
                JOptionPane.showMessageDialog(GymActivities.this, selectedActivities.toString());

                // الانتقال إلى صفحة HomePage
                HomePage homePage = new HomePage();
                
                
                dispose(); // إغلاق صفحة GymActivities
            }
        });

        // Adding main panel to the frame
        add(mainPanel);
        setVisible(true);
    }

}