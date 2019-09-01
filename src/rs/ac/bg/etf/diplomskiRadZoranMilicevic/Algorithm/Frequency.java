package rs.ac.bg.etf.diplomskiRadZoranMilicevic.Algorithm;

import java.util.ArrayList;

public class Frequency {
    private int occurrences;
    private ArrayList<Integer> rowIndexList;

    public Frequency() {
        this.occurrences = 0;
        this.rowIndexList = new ArrayList<>();
    }

    public Frequency(int occurrences, ArrayList<Integer> rowIndexList) {
        this.occurrences = occurrences;
        this.rowIndexList = rowIndexList;
    }

    public int getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(int occurrences) {
        this.occurrences = occurrences;
    }

    public ArrayList<Integer> getRowIndexList() {
        return rowIndexList;
    }

    public void setRowIndexList(ArrayList<Integer> rowIndexList) {
        this.rowIndexList = rowIndexList;
    }

    public void increaseOccurrences(){
        occurrences++;
    }

    public void increaseOccurrences(int num){
        occurrences+=num;
    }

    //TODO check contains
    public void insertRowNumber(int number){
        if(!rowIndexList.contains(number)){
            rowIndexList.add(number);
        }
    }


}
