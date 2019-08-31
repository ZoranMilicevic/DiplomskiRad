package rs.ac.bg.etf.diplomskiRadZoranMilicevic.Data;

import java.util.ArrayList;

public class GeneralisationTable {
    private ArrayList<ArrayList<String>> genTable;

    public GeneralisationTable(ArrayList<ArrayList<String>> genTable) {
        this.genTable = genTable;
    }

    public ArrayList<ArrayList<String>> getGenTable() {
        return genTable;
    }

    public void setGenTable(ArrayList<ArrayList<String>> genTable) {
        this.genTable = genTable;
    }
}
