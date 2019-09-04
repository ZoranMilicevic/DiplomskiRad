package rs.ac.bg.etf.diplomskiRadZoranMilicevic.Data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GeneralisationTable {
    public static final String ageGeneralisationTableFile = "C:\\Users\\Zoran Milicevic\\Documents\\InteliJProjects\\DiplomskiRad\\Data\\GeneralisationTables\\Age.xlxs";
    public static final String placesGeneralisationTableFile = "C:\\Users\\Zoran Milicevic\\Documents\\InteliJProjects\\DiplomskiRad\\Data\\GeneralisationTables\\City.xlxs";
    public static final String gpaGeneralisationTableFile = "C:\\Users\\Zoran Milicevic\\Documents\\InteliJProjects\\DiplomskiRad\\Data\\GeneralisationTables\\Gpa.xlxs";
    public static final String genderGeneralisationTableFile = "C:\\Users\\Zoran Milicevic\\Documents\\InteliJProjects\\DiplomskiRad\\Data\\GeneralisationTables\\Gender.xlxs";
    public static final String moduleGeneralisationTableFile = "C:\\Users\\Zoran Milicevic\\Documents\\InteliJProjects\\DiplomskiRad\\Data\\GeneralisationTables\\Module.xlxs";
    public static final String indexGeneralisationTableFile = "C:\\Users\\Zoran Milicevic\\Documents\\InteliJProjects\\DiplomskiRad\\Data\\GeneralisationTables\\IndexNumber.xlxs";
    public static final String phoneNumberGeneralisationTableFile = "C:\\Users\\Zoran Milicevic\\Documents\\InteliJProjects\\DiplomskiRad\\Data\\GeneralisationTables\\Phone.xlxs";
    public static final String studyYearGeneralisationTableFile = "C:\\Users\\Zoran Milicevic\\Documents\\InteliJProjects\\DiplomskiRad\\Data\\GeneralisationTables\\StudyYear.xlxs";
    public static final String jmbgGeneralisationTableFile = "C:\\Users\\Zoran Milicevic\\Documents\\InteliJProjects\\DiplomskiRad\\Data\\GeneralisationTables\\JMBG.xlxs";
    public static final String[] generalisationTableFiles = {ageGeneralisationTableFile, placesGeneralisationTableFile,
            gpaGeneralisationTableFile, genderGeneralisationTableFile, moduleGeneralisationTableFile,
            indexGeneralisationTableFile, phoneNumberGeneralisationTableFile, studyYearGeneralisationTableFile,
            jmbgGeneralisationTableFile};

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
