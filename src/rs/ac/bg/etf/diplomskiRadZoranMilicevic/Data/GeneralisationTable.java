package rs.ac.bg.etf.diplomskiRadZoranMilicevic.Data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GeneralisationTable {
    public static final String directory = "C:\\Users\\Zoran Milicevic\\Documents\\InteliJProjects\\DiplomskiRad\\Data\\GeneralisationTables\\";
    public static final String ageGeneralisationTableFile = directory + "Age.csv";
    public static final String placesGeneralisationTableFile = directory + "City.csv";
    public static final String gpaGeneralisationTableFile = directory + "Gpa.csv";
    public static final String genderGeneralisationTableFile = directory + "Gender.csv";
    public static final String moduleGeneralisationTableFile = directory + "Module.csv";
    public static final String indexGeneralisationTableFile = directory + "IndexNumber.csv";
    public static final String studyYearGeneralisationTableFile = directory + "StudyYear.csv";
    public static final String phoneNumberGeneralisationTableFile = directory + "Phone.csv";
    public static final String jmbgGeneralisationTableFile = directory + "JMBG.csv";
    public static final String[] generalisationTableFiles = {ageGeneralisationTableFile, placesGeneralisationTableFile,
            gpaGeneralisationTableFile, genderGeneralisationTableFile, moduleGeneralisationTableFile,
            indexGeneralisationTableFile, studyYearGeneralisationTableFile, phoneNumberGeneralisationTableFile};
            //, jmbgGeneralisationTableFile};

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

    public static GeneralisationTable readFileData(String fileName){
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            ArrayList<ArrayList<String>> values = new ArrayList<>();
            while(line!=null){
                String[] tokens = line.split(",");
                ArrayList<String> lineTokens = new ArrayList<>();
                for(String token:tokens){
                    lineTokens.add(token);
                }
                values.add(lineTokens);
                line = br.readLine();
            }
            return new GeneralisationTable(values);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
