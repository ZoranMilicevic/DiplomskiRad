package rs.ac.bg.etf.diplomskiRadZoranMilicevic.GUI;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuForm {
     JButton viewATable;
     JButton exitButton;
     JButton generateARandomTableButton1;
     JButton anonymizeATableButton;
     JPanel TopPanel;



    public MenuForm() {
        generateARandomTableButton1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CreateRandomTableForm.changeView(Main.frame);
            }
        });
        anonymizeATableButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                AnonymizeTableForm.changeView(Main.frame);
            }
        });
        viewATable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ViewTableForm.changeView(Main.frame);
            }
        });
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0);
            }
        });
    }

    public static void changeView(JFrame frame){
        frame.setContentPane(new MenuForm().TopPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
