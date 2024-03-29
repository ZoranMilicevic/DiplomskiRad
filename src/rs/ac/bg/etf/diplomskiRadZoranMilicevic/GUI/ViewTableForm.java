package rs.ac.bg.etf.diplomskiRadZoranMilicevic.GUI;

import rs.ac.bg.etf.diplomskiRadZoranMilicevic.Tables.StudentDataTable;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewTableForm {
    private JButton backButton;
    private JButton viewTableButton;
    private JTextField textField1;
    private JPanel TopPanel;
    private JCheckBox isTheTableYouCheckBox;

    public ViewTableForm() {
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                MenuForm.changeView(Main.frame);
            }
        });
        viewTableButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String name = textField1.getText();
                boolean anon = isTheTableYouCheckBox.isSelected();
                StudentDataTable std = StudentDataTable.readFileData(name, anon);
                new Table(std, anon);
            }
        });
    }

    public static void changeView(JFrame frame){
        frame.setContentPane(new ViewTableForm().TopPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
