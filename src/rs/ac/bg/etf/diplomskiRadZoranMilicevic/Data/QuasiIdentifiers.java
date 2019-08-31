package rs.ac.bg.etf.diplomskiRadZoranMilicevic.Data;

import java.util.ArrayList;

public class QuasiIdentifiers {
    private ArrayList<ArrayList<String>> quasiIdentifiers;

    public QuasiIdentifiers() {
        quasiIdentifiers = new ArrayList<>();
    }

    public QuasiIdentifiers(ArrayList<ArrayList<String>> quasiIdentifiers) {
        this.quasiIdentifiers = quasiIdentifiers;
    }

    public ArrayList<ArrayList<String>> getQuasiIdentifiers() {
        return quasiIdentifiers;
    }

    public void setQuasiIdentifiers(ArrayList<ArrayList<String>> quasiIdentifiers) {
        this.quasiIdentifiers = quasiIdentifiers;
    }
}
