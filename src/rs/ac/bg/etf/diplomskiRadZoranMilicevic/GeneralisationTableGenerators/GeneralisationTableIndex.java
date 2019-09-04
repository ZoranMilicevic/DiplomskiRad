package rs.ac.bg.etf.diplomskiRadZoranMilicevic.GeneralisationTableGenerators;

import java.io.*;
import java.util.ArrayList;

public class GeneralisationTableIndex {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("C:\\Users\\Zoran Milicevic\\Desktop\\index.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            StringBuilder s = new StringBuilder();
            PrintWriter pw = new PrintWriter("C:\\Users\\Zoran Milicevic\\Desktop\\BrIndex2.txt");
            int i = 0;
            while(line!=null) {
                StringBuilder sb = new StringBuilder(line);
                s.append(line);

                sb.setCharAt(8, '*');
                s.append(" "+sb.toString());
                sb.setCharAt(7, '*');
                s.append(" "+sb.toString());
                sb.setCharAt(6, '*');
                s.append(" "+sb.toString());
                sb.setCharAt(3, '*');
                s.append(" "+sb.toString());


                s.append("\n");
                line = br.readLine();
                if(i%10==0){
                    pw.write(s.toString());
                    s = new StringBuilder();
                }
                i++;
            }

            pw.write(s.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
