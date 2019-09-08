package rs.ac.bg.etf.diplomskiRadZoranMilicevic.Test;

import rs.ac.bg.etf.diplomskiRadZoranMilicevic.Algorithm.Anonymize;
import rs.ac.bg.etf.diplomskiRadZoranMilicevic.Data.QuasiIdentifiers;
import rs.ac.bg.etf.diplomskiRadZoranMilicevic.DomainGeneralisationHierarchy.DGH;
import rs.ac.bg.etf.diplomskiRadZoranMilicevic.Tables.StudentDataTable;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AnonymizeTest {
    public static final String directory = "C:\\Users\\Zoran Milicevic\\Documents\\InteliJProjects\\DiplomskiRad\\Data\\RandomTables\\";
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<DGH> dgh = DGH.generateAllDGH();
        simulation(dgh);
    }

    public static void simulation(ArrayList<DGH> dgh){
        printMenu();
        int o = sc.nextInt();
        while(o!=5){
            switch (o){
                case 1:
                    option1(dgh);
                    break;
                case 2:
                    option2(dgh);
                    break;
                case 3:
                    option3(dgh);
                    break;
                case 4:
                    option4(dgh);
                    break;
                case 5: return;
                default: System.out.println("This option does not exist");
            }

            printMenu();
            o=sc.nextInt();
        }
        System.out.println("Done. Exiting.");
    }

    public static void printMenu(){
        System.out.println("Possible options:");
        System.out.println("\t 1. Generate random table");
        System.out.println("\t 2. Generate and Anonymise random table");
        System.out.println("\t 3. Anonymise last randomly generated table");
        System.out.println("\t 4. Anonymise a specified table");
        System.out.println("\t 5. Exit");
        System.out.println("------------------------------------------");
        System.out.print("Your desired option is: ");
    }

    public static void option1(ArrayList<DGH> dgh){
        System.out.print("Input desired number of entries in the random table: ");
        int num = sc.nextInt();
        StudentDataTable.createRandomStudentTable(num);
    }

    public static void option2(ArrayList<DGH> dgh){
        System.out.print("Input desired number of entries in the random table: ");
        int num = sc.nextInt();
        StudentDataTable.createRandomStudentTable(num);
        System.out.println("Random table created!");
        option3(dgh);
    }

    public static void option3(ArrayList<DGH> dgh){
        System.out.println("Input the desired value of the parameter k: ");
        int k = sc.nextInt();

        System.out.println("Input in one line, which attributes are quasi identifiers, input -1 for end: ");
        StudentDataTable.printAttributes();

        ArrayList<Integer> attList = new ArrayList<>();
        int a = sc.nextInt();
        ArrayList<DGH> newDgh = new ArrayList<>();
        while(a!=-1){
            attList.add(a);
            newDgh.add(dgh.get(a));
            a = sc.nextInt();
        }
        QuasiIdentifiers qi = StudentDataTable.lastGeneratedRandomTable.createQuasiIdentifiersTable(attList);

        System.out.println("Stared anonymising");
        Anonymize.anonymize(k, newDgh, qi);
        System.out.println("Anonymisation done!");

        StudentDataTable.lastGeneratedRandomTable.printAnonTableToFile(attList, qi);
    }

    public static void option4(ArrayList<DGH> dgh){
        System.out.println("Input the desired value of the parameter k: ");
        int k = sc.nextInt();

        System.out.println("Input in one line, which attributes are quasi identifiers, input -1 for end: ");
        StudentDataTable.printAttributes();

        ArrayList<Integer> attList = new ArrayList<>();
        int a = sc.nextInt();
        ArrayList<DGH> newDgh = new ArrayList<>();
        while(a!=-1){
            attList.add(a);
            newDgh.add(dgh.get(a));
            a = sc.nextInt();
        }

        System.out.print("Input the path to the file: ");
        String fileName = sc.next();
        fileName = directory + fileName;
        StudentDataTable.lastGeneratedRandomTable = StudentDataTable.readFileData(fileName);

        QuasiIdentifiers qi = StudentDataTable.lastGeneratedRandomTable.createQuasiIdentifiersTable(attList);

        System.out.println("Stared anonymising");
        Anonymize.anonymize(k, newDgh, qi);
        System.out.println("Anonymisation done!");

        StudentDataTable.lastGeneratedRandomTable.printAnonTableToFile(attList, qi);
    }

}
