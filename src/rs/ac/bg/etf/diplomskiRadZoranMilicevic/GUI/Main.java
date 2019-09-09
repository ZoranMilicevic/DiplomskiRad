package rs.ac.bg.etf.diplomskiRadZoranMilicevic.GUI;


import rs.ac.bg.etf.diplomskiRadZoranMilicevic.DomainGeneralisationHierarchy.DGH;

import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static JFrame frame;
    public static ArrayList<DGH> dgh;

    public static void main(String[] args) {
        System.out.println("Generating all the dghs, depending on the size of the generalisation tables data, it is possible that this could take a while");
        dgh = DGH.generateAllDGH();
        System.out.println("All the dghs are generated!");

        frame = new JFrame("Menu");
        frame.setContentPane(new Menu().TopPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
