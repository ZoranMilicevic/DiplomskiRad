package rs.ac.bg.etf.diplomskiRadZoranMilicevic.GUI;

import rs.ac.bg.etf.diplomskiRadZoranMilicevic.Tables.StudentDataTable;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CreateRandomTable {
    private JButton backButton;
    private JButton generateARandomTableButton;
    private JTextField tableName;
    private JTextField numberOfEntries;
    private JPanel TopPanel;

    public CreateRandomTable() {
        generateARandomTableButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String name = tableName.getText();
                int number = Integer.parseInt(numberOfEntries.getText());
                StudentDataTable std = StudentDataTable.createRandomStudentTable(name, number);
                JOptionPane.showMessageDialog(Main.frame, "Success!");
                new Table(std);
            }
        });
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Menu.changeView(Main.frame);
            }
        });
    }

    public static void changeView(JFrame frame){
        frame.setContentPane(new CreateRandomTable().TopPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
