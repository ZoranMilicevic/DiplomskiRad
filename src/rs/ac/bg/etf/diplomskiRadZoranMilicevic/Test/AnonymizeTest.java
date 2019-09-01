package rs.ac.bg.etf.diplomskiRadZoranMilicevic.Test;

import rs.ac.bg.etf.diplomskiRadZoranMilicevic.Algorithm.Anonymize;
import rs.ac.bg.etf.diplomskiRadZoranMilicevic.Data.GeneralisationTable;
import rs.ac.bg.etf.diplomskiRadZoranMilicevic.Data.QuasiIdentifiers;
import rs.ac.bg.etf.diplomskiRadZoranMilicevic.DomainGeneralisationHierarchy.DGH;
import rs.ac.bg.etf.diplomskiRadZoranMilicevic.Tree.Node;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class AnonymizeTest {
    public static final String directory = "C:\\Users\\Zoran Milicevic\\Desktop";

    public static void main(String[] args) {
        ArrayList<DGH> dgh = new ArrayList<>();
        dgh.add(readFileAndGenerateDGH(directory + "\\age.csv"));
        dgh.add(readFileAndGenerateDGH(directory + "\\city.csv"));
        dgh.add(readFileAndGenerateDGH(directory + "\\zip.csv"));

        ArrayList<ArrayList<String>> data = readFileData(directory+"\\data.csv").getGenTable();
        ArrayList<ArrayList<String>> quasiIdentifiers = new ArrayList<>();

        for(ArrayList<String> row : data){
            ArrayList<String> newRow = new ArrayList<>();
            for(int i = 1; i<row.size()-1; i++)newRow.add(row.get(i));
            quasiIdentifiers.add(newRow);
        }

        System.out.println("Start anonymizing");

        Anonymize.anonymize(2, new QuasiIdentifiers(quasiIdentifiers), dgh);

        printTable(directory+ "\\anon.csv", data, quasiIdentifiers);



    }

    public static DGH readFileAndGenerateDGH(String fileName){
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

            DGH dgh = DGH.generateDGH(new GeneralisationTable(values));
            return dgh;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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


    public static void printTree(Node root){
        Queue<Node> visited = new ArrayDeque<>();
        Queue<Node> unvisited = new ArrayDeque<>();

        visited.add(root);
        unvisited.add(root);

        while(!unvisited.isEmpty()){
            Node current = unvisited.remove();
            visited.add(current);
            System.out.println(current.getData() + " " + current.getDepth());
            for(Node n: current.getChildren()){
                if(!visited.contains(n)) unvisited.add(n);
            }
        }
    }

    public static void printTable(String fileName, ArrayList<ArrayList<String>> data, ArrayList<ArrayList<String>> quasi){
        try {
            PrintWriter fw = new PrintWriter(new File(fileName));
            for(int i = 0; i<data.size(); i++){
                ArrayList<String> d = data.get(i);
                ArrayList<String> q = quasi.get(i);

                fw.append(d.get(0)); fw.append(',');
                fw.append(q.get(0)); fw.append(',');
                fw.append(q.get(1)); fw.append(',');
                fw.append(q.get(2)); fw.append(',');
                fw.append(d.get(4)); fw.append('\n');
            }
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
