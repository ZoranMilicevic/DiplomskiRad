package rs.ac.bg.etf.diplomskiRadZoranMilicevic.GeneralisationTableGenerators;

import java.io.*;
import java.util.ArrayList;

public class GeneralisationTablePhone {
    public static void main(String[] args) {
        try {
            StringBuilder s = new StringBuilder();
            for(int i = 6300000; i<6400000; i++){
                StringBuilder s2 = new StringBuilder("0" + Integer.toString(i));
                s.append(s2);

                for(int j = 7; j>2;j--) {
                    s2.setCharAt(j, '*');
                    s.append("\t" + s2);
                }
                s.append("\n");
            }

            PrintWriter pw = new PrintWriter("C:\\Users\\Zoran Milicevic\\Desktop\\telefon.txt");
            pw.write(s.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
