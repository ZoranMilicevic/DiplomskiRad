package rs.ac.bg.etf.diplomskiRadZoranMilicevic.Tables;

import rs.ac.bg.etf.diplomskiRadZoranMilicevic.Data.QuasiIdentifiers;
import rs.ac.bg.etf.diplomskiRadZoranMilicevic.GUI.Table;

import java.io.*;
import java.util.ArrayList;

public class StudentDataTable {
    public static final String directory = System.getProperty("user.dir") + File.separator + "Data" + File.separator + "Values" + File.separator;
    public static final String nameValueFile = directory + "Name.txt";
    public static final String surnameValueFile = directory + "Surname.txt";
    public static final String ageValuesFile = directory + "Age.txt";
    public static final String placesValuesFile = directory + "City.txt";
    public static final String gpaValuesFile = directory + "Gpa.txt";
    public static final String genderValuesFile = directory + "Gender.txt";
    public static final String moduleValuesFile = directory + "Module.txt";
    public static final String indexNumberValuesFile = directory + "IndexNumber.txt";
    public static final String studyYearValuesFile = directory + "StudyYear.txt";
    public static final String phoneNumberValuesFile = directory + "Phone.txt";
    public static final String jmbgValuesFile = directory + "JMBG.txt";
    public static final String[] valueFilesDirectories = {nameValueFile, surnameValueFile, ageValuesFile, placesValuesFile, gpaValuesFile, genderValuesFile,
            moduleValuesFile, indexNumberValuesFile, studyYearValuesFile, phoneNumberValuesFile, jmbgValuesFile};
    public static final String randomTablesDirectory = "Data" + File.separator + "RandomTables" + File.separator;
    public static final String anonTablesDirecotry = "Data" + File.separator + "AnonymisedTables" + File.separator;

    public static String[] attributes = {"Name", "Surname", "Age", "City", "GPA", "Gender", "Module", "Index Number", "Study Year", "Phone Number", "JMBG"};
    public static String[] anonAttributes = {"Age", "City", "GPA", "Gender", "Module", "Index Number", "Study Year", "Phone Number", "JMBG"};

    ArrayList<ArrayList<String>> table;

    public StudentDataTable() {
        this.table = new ArrayList<>();
    }

    public StudentDataTable(ArrayList<ArrayList<String>> table) {
        this.table = table;
    }

    public ArrayList<ArrayList<String>> getTable() {
        return table;
    }

    public void setTable(ArrayList<ArrayList<String>> table) {
        this.table = table;
    }

    //----------------------------------------------------------------------------------------

    public void insertRow(ArrayList<String> row){
        table.add(row);
    }

    public void printDataTableToFile(String fileName){
        try {
            PrintWriter pw = new PrintWriter(new File(fileName));
            for(int i = 0; i<table.size(); i++){
                int len = table.get(0).size();
                for(int j = 0; j<len; j++){
                    pw.write(table.get(i).get(j));
                    if(j!=len-1)pw.write(",");
                    else pw.write("\n");
                }
            }
            pw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printAnonTableToFile(ArrayList<Integer> indexArray, QuasiIdentifiers qi, String fileName){
        fileName = anonTablesDirecotry + fileName + ".csv";
        try {
            PrintWriter pw = new PrintWriter(fileName);
            for(int i=0; i<table.size(); i++){
                int u=0;
                for(int j = 0; j<table.get(0).size(); j++) {
                    if (indexArray.contains(j)) {
                        pw.write(qi.getRow(i).get(u++));
                        pw.write(",");
                    } else {
                        pw.write(table.get(i).get(j));
                        pw.write(",");
                    }
                }
                pw.write("\n");
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public QuasiIdentifiers createQuasiIdentifiersTable(ArrayList<Integer> indexArray){
        QuasiIdentifiers qi = new QuasiIdentifiers();

        for(ArrayList<String> row: table){
            ArrayList<String> newRow = new ArrayList<>();
            for(int i:indexArray){
                newRow.add(row.get(i));
            }
            qi.insertRow(newRow);
        }

        return qi;
    }

    public static StudentDataTable readFileData(String fileName, boolean anon){
        if(anon)fileName = anonTablesDirecotry + fileName + ".csv";
        else fileName = randomTablesDirectory + fileName + ".csv";
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
            return new StudentDataTable(values);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static StudentDataTable createRandomStudentTable(String name, int numberOfEntries) {
        StudentDataTable table = new StudentDataTable();
        ArrayList<ValuesList> valuesListsArray = new ArrayList<>();
        for(String fileName:valueFilesDirectories) valuesListsArray.add(ValuesList.readValuesFromFile(fileName));

        for (int i = 0; i <numberOfEntries ; i++) {
            ArrayList<String> row = new ArrayList<>();
            for (int j = 0; j < valuesListsArray.size(); j++) {
                row.add(valuesListsArray.get(j).getRandomValue());
            }
            table.insertRow(row);
        }

        table.printDataTableToFile(randomTablesDirectory + name + ".csv");

        return table;
    }

    public String[][] getMatrix(){
        String[][] matrix = new String[table.size()][table.get(0).size()];
        for (int i = 0; i < table.size(); i++) {
            for (int j = 0; j < table.get(i).size(); j++) {
                matrix[i][j] = table.get(i).get(j);
            }
        }
        return matrix;
    }

    public void removePrivateData(){
        for(ArrayList<String> row:table){
            row.remove(0);
            row.remove(0);
        }
    }

}
