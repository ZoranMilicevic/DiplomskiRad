package rs.ac.bg.etf.diplomskiRadZoranMilicevic.GeneralisationTableGenerators;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class GeneralisationTableJMBG {
    public static void main(String[] args) {
        try {
            StringBuilder s = new StringBuilder();
            PrintWriter pw = new PrintWriter("C:\\Users\\Zoran Milicevic\\Desktop\\jmbg.txt");
            for(int i = 1; i<31; i++){
                StringBuilder s2;
                if(i<10) s2 = new StringBuilder("0" + Integer.toString(i));
                else s2 = new StringBuilder(Integer.toString(i));
                String str1 = s2.toString();
                for(int j = 1; j<13; j++){
                    s2 = new StringBuilder(str1);
                    if(j<10)s2.append('0' + Integer.toString(j));
                    else s2.append(Integer.toString(j));

                    String str2 = s2.toString();
                    for(int k = 986; k< 1000; k++){
                        s2 = new StringBuilder(str2);
                        s2.append(Integer.toString(k));

                        String str3 = s2.toString();
                        for(int p = 10; p<50; p++){
                            s2 = new StringBuilder(str3);
                            s2.append(Integer.toString(p));
                            s.append(s2.toString());
                            for(int l = 8; l>-1; l--) {
                                s2.setCharAt(l, '*');
                                s.append(" " + s2.toString());
                            }
                            s.append("\n");
                        }
                    }
                }

            }

            pw.write(s.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
