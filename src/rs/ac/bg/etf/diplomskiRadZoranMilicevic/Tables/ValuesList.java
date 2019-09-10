package rs.ac.bg.etf.diplomskiRadZoranMilicevic.Tables;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class ValuesList {
    ArrayList<String> valuesList;

    public ValuesList(ArrayList<String> valuesList) {
        this.valuesList = valuesList;
    }

    public ArrayList<String> getValuesList() {
        return valuesList;
    }

    public void setValuesList(ArrayList<String> valuesList) {
        this.valuesList = valuesList;
    }

    //--------------------------------------------------------------

    public String getRandomValue(){
        Random r = new Random();
        int len = valuesList.size();
        int index = r.nextInt(len);
        return valuesList.get(index);
    }

    public static ValuesList readValuesFromFile(String fileName){
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            ArrayList<String> valuesList = new ArrayList<>();
            while(line!=null){
                valuesList.add(line);
                line = br.readLine();
            }
            return new ValuesList(valuesList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

