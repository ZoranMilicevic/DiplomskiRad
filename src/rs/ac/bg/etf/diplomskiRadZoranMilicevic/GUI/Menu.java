package rs.ac.bg.etf.diplomskiRadZoranMilicevic.GUI;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu {
     JButton viewATable;
     JButton exitButton;
     JButton generateARandomTableButton1;
     JButton anonymizeATableButton;
     JPanel TopPanel;



    public Menu() {
        generateARandomTableButton1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CreateRandomTable.changeView(Main.frame);
            }
        });
        anonymizeATableButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                AnonymizeTable.changeView(Main.frame);
            }
        });
        viewATable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ViewTable.changeView(Main.frame);
            }
        });
    }

    public static void changeView(JFrame frame){
        frame.setContentPane(new Menu().TopPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
