package rs.ac.bg.etf.diplomskiRadZoranMilicevic.Data;

import java.util.ArrayList;

public class QuasiIdentifiers {
    private ArrayList<ArrayList<String>> quasiIdentifiersByRows;

    public QuasiIdentifiers() {
        this.quasiIdentifiersByRows = new ArrayList<>();
    }

    public QuasiIdentifiers(ArrayList<ArrayList<String>> quasiIdentifiersByRows) {
        this.quasiIdentifiersByRows = quasiIdentifiersByRows;
    }

    public ArrayList<ArrayList<String>> getQuasiIdentifiersByRows() {
        return quasiIdentifiersByRows;
    }

    public void setQuasiIdentifiersByRows(ArrayList<ArrayList<String>> quasiIdentifiersByRows) {
        this.quasiIdentifiersByRows = quasiIdentifiersByRows;
    }

    //---------------------------------------------------------------------------------

    public void insertRow(ArrayList<String> row){
        quasiIdentifiersByRows.add(row);
    }
    public int getNumberOfRows(){
        return quasiIdentifiersByRows.size();
    }

    public int getNUmberOfColumns(){
        return quasiIdentifiersByRows.get(0).size();
    }

    public ArrayList<String> getRow(int i){
        return quasiIdentifiersByRows.get(i);
    }

    public void supress(ArrayList<Integer> rowNumberList){
        for(int i: rowNumberList) {
            ArrayList<String> row = quasiIdentifiersByRows.get(i);
            for(int j = 0; j<row.size(); j++)row.set(j, "*");
        }
    }

    public void updateValues(ArrayList<Integer> rowNumberList, ArrayList<String> newRowValue){
        for(int i: rowNumberList){
            quasiIdentifiersByRows.set(i, newRowValue);
        }
    }

}
