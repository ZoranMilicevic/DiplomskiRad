package rs.ac.bg.etf.diplomskiRadZoranMilicevic.Tables;

import rs.ac.bg.etf.diplomskiRadZoranMilicevic.Data.QuasiIdentifiers;

import java.io.*;
import java.util.ArrayList;

public class StudentDataTable {
    public static final String ageValuesFile = "C:\\Users\\Zoran Milicevic\\Documents\\InteliJProjects\\DiplomskiRad\\Data\\Values\\Age.txt";
    public static final String placesValuesFile = "C:\\Users\\Zoran Milicevic\\Documents\\InteliJProjects\\DiplomskiRad\\Data\\Values\\City.txt";
    public static final String gpaValuesFile = "C:\\Users\\Zoran Milicevic\\Documents\\InteliJProjects\\DiplomskiRad\\Data\\Values\\Gpa.txt";
    public static final String genderValuesFile = "C:\\Users\\Zoran Milicevic\\Documents\\InteliJProjects\\DiplomskiRad\\Data\\Values\\Gender.txt";
    public static final String moduleValuesFile = "C:\\Users\\Zoran Milicevic\\Documents\\InteliJProjects\\DiplomskiRad\\Data\\Values\\Module.txt";
    public static final String indexNumberValuesFile = "C:\\Users\\Zoran Milicevic\\Documents\\InteliJProjects\\DiplomskiRad\\Data\\Values\\IndexNumber.txt";
    public static final String studyYearValuesFile = "C:\\Users\\Zoran Milicevic\\Documents\\InteliJProjects\\DiplomskiRad\\Data\\Values\\StudyYear.txt";
    public static final String phoneNumberValuesFile = "C:\\Users\\Zoran Milicevic\\Documents\\InteliJProjects\\DiplomskiRad\\Data\\Values\\Phone.txt";
    public static final String jmbgValuesFile = "C:\\Users\\Zoran Milicevic\\Documents\\InteliJProjects\\DiplomskiRad\\Data\\Values\\JMBG.txt";
    public static final String[] valueFilesDirectories = {ageValuesFile, placesValuesFile, gpaValuesFile, genderValuesFile,
            moduleValuesFile, indexNumberValuesFile, studyYearValuesFile, phoneNumberValuesFile, jmbgValuesFile};
    public static final String randomTablesDirectory = "C:\\Users\\Zoran Milicevic\\Documents\\InteliJProjects\\DiplomskiRad\\Data\\RandomTables\\table";
    public static final String anonTablesDirecotry = "C:\\Users\\Zoran Milicevic\\Documents\\InteliJProjects\\DiplomskiRad\\Data\\AnonymisedTables\\anonTable";
    public static int ID = 0;
    public static int IDANON = 0;
    public static StudentDataTable lastGeneratedRandomTable = null;
    public static String[] attributes = {"0. Age", "1. City", "2. GPA", "3. Gender", "4. Module", "5. Student Index Number", "6. Study Year", "7. Phone Number", "8. JMBG"};

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

    public void printAnonTableToFile(ArrayList<Integer> indexArray, QuasiIdentifiers qi){
        String fileName = anonTablesDirecotry + IDANON++ + ".csv";

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

    public static StudentDataTable readFileData(String fileName){
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

    public static void createRandomStudentTable(int numberOfEntries) {
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

        table.printDataTableToFile(randomTablesDirectory + Integer.toString(ID++) + ".csv");

        lastGeneratedRandomTable = table;
    }

    public static void printAttributes(){
        for(String s: attributes){
            System.out.println("\t" + s);
        }
    }

}
