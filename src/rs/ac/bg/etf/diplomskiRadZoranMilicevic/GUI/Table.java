package rs.ac.bg.etf.diplomskiRadZoranMilicevic.GUI;

import rs.ac.bg.etf.diplomskiRadZoranMilicevic.Tables.StudentDataTable;

import javax.swing.*;

public class Table {
    JFrame jf;
    JTable jt;

    public Table(StudentDataTable std) {
        this.jf = new JFrame();
        jt = new JTable(std.getMatrix(), StudentDataTable.attributes);

        JScrollPane sp = new JScrollPane(jt);
        jf.add(sp);
        jf.setSize(1200, 600);
        jf.setVisible(true);
    }
}
