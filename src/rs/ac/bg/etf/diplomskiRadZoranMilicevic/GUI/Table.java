package rs.ac.bg.etf.diplomskiRadZoranMilicevic.GUI;

import rs.ac.bg.etf.diplomskiRadZoranMilicevic.Tables.StudentDataTable;

import javax.swing.*;

public class Table {
    JFrame jf;
    JTable jt;

    public Table(StudentDataTable std, boolean anon) {
        this.jf = new JFrame();
        if(anon)jt = new JTable(std.getMatrix(), StudentDataTable.anonAttributes);
        else jt = new JTable(std.getMatrix(), StudentDataTable.attributes);

        JScrollPane sp = new JScrollPane(jt);
        jf.add(sp);
        jf.setSize(1200, 600);
        jf.setVisible(true);
    }
}
