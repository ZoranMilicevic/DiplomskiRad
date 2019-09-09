package rs.ac.bg.etf.diplomskiRadZoranMilicevic.DataflyAlgorithm;

import java.util.ArrayList;

public class Frequency {
    private int occurrences;
    private ArrayList<Integer> rowIndexList;

    public Frequency(int rowNum) {
        this.occurrences = 1;
        this.rowIndexList = new ArrayList<>();
        this.rowIndexList.add(rowNum);
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

    public void insertRowNumber(int number){
        if(!rowIndexList.contains(number)){
            rowIndexList.add(number);
        }
    }

    public void insertRowNumbers(ArrayList<Integer> numbers){
        for(int number: numbers)insertRowNumber(number);
    }


}
