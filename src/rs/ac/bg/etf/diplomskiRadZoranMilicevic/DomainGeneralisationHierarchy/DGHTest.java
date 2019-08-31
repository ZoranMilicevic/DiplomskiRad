package rs.ac.bg.etf.diplomskiRadZoranMilicevic.DomainGeneralisationHierarchy;

import rs.ac.bg.etf.diplomskiRadZoranMilicevic.Data.GeneralisationTable;
import rs.ac.bg.etf.diplomskiRadZoranMilicevic.Data.QuasiIdentifiers;
import rs.ac.bg.etf.diplomskiRadZoranMilicevic.Tree.Node;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class DGHTest {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("C:\\Users\\Zoran Milicevic\\Desktop\\podaci.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            ArrayList<ArrayList<String>> values = new ArrayList<>();
            while(line!=null){
                String[] tokens = line.split(" ");
                ArrayList<String> lineTokens = new ArrayList<>();
                for(String token:tokens){
                    lineTokens.add(token);
                }
                values.add(lineTokens);
                line = br.readLine();
            }

            DGH dgh = DGH.generateDGH(new GeneralisationTable(values));
            printTree(dgh.getTree().getRoot());
            System.out.println();
            System.out.println(dgh.generalise("31"));
            System.out.println(dgh.getTree().getGeneralisationLevels());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
