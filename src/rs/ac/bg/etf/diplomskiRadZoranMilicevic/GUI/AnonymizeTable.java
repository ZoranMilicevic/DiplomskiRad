package rs.ac.bg.etf.diplomskiRadZoranMilicevic.GUI;

import rs.ac.bg.etf.diplomskiRadZoranMilicevic.Data.QuasiIdentifiers;
import rs.ac.bg.etf.diplomskiRadZoranMilicevic.DataflyAlgorithm.DataflyAnonymize;
import rs.ac.bg.etf.diplomskiRadZoranMilicevic.Tables.StudentDataTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AnonymizeTable {
    private JButton anonymizeButton;
    private JTextField originalTable;
    private JTextField anonTable;
    private JPanel TopPanel;
    private JTextField kValue;
    private JButton backButton;
    private JList list1;
    private JPanel listPanel;

    public AnonymizeTable() {
        list1 = new JList(StudentDataTable.attributes);
        listPanel.setLayout(new BorderLayout());
        listPanel.add(list1);

        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Menu.changeView(Main.frame);
            }
        });
        anonymizeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String origName = originalTable.getText();
                String anonName = anonTable.getText();
                int k = Integer.parseInt(kValue.getText());

                StudentDataTable std = StudentDataTable.readFileData(origName, false);

                int[] arr =  list1.getSelectedIndices();
                ArrayList<Integer> attList = new ArrayList<>();
                for(int j:arr)attList.add(j);

                QuasiIdentifiers qi = std.createQuasiIdentifiersTable(attList);

                DataflyAnonymize.dataflyAnonymize(k, Main.dgh, qi);
                JOptionPane.showMessageDialog(Main.frame, "Success!");

                std.printAnonTableToFile(attList , qi, anonName);

                new Table(StudentDataTable.readFileData(anonName, true));
            }
        });
    }

    public static void changeView(JFrame frame){
        AnonymizeTable at = new AnonymizeTable();
        frame.setContentPane(at.TopPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
