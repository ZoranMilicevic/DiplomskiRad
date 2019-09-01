package rs.ac.bg.etf.diplomskiRadZoranMilicevic.Algorithm;

import rs.ac.bg.etf.diplomskiRadZoranMilicevic.Data.QuasiIdentifiers;
import rs.ac.bg.etf.diplomskiRadZoranMilicevic.DomainGeneralisationHierarchy.DGH;

import java.util.*;

public class Anonymize {
    //quasiIdentifiers is an ArrayList<ArrayList<String>>
    //where ArrayList<String> is one COLUMN from the table
    //so it's essentially an list of columns
    public static void anonymize(int k, QuasiIdentifiers quasiIdentifiers, ArrayList<DGH> dghList){
        Map<ArrayList<String>, Frequency> qi_frequency = new HashMap<>();
        Map<Integer, Set<String>> domains = new HashMap<>();
        Map<Integer, Integer> gen_levels = new HashMap<>();

        for(int i=0; i<quasiIdentifiers.getNUmberOfColumns(); i++)domains.put(i, new HashSet<>());

        for(int i = 0; i<quasiIdentifiers.getNUmberOfColumns(); i++) gen_levels.put(i, 0);

        for(int i =0; i<quasiIdentifiers.getNumberOfRows(); i++){
            ArrayList<String> row = quasiIdentifiers.getRow(i);
            if(qi_frequency.containsKey(row)){
                qi_frequency.get(row).increaseOccurrences();
                qi_frequency.get(row).insertRowNumber(i);
            }
            else{
                qi_frequency.put(row, new Frequency());
                for(int j = 0; j<row.size(); j++){
                    String value = row.get(j);
                    domains.get(j).add(value);
                }
            }
        }

        while(true){
            //find how many tuples don't satisfy K-anonymity
            int numberOfNonKanonTuples = 0;
            Set<Map.Entry<ArrayList<String>, Frequency>> entrySet = qi_frequency.entrySet();
            for(Map.Entry<ArrayList<String>, Frequency> entry:entrySet){
                if(entry.getValue().getOccurrences()<k)numberOfNonKanonTuples+=entry.getValue().getOccurrences();
            }

            if(numberOfNonKanonTuples > k){
                //find attribute with max cardinality
                int maxCardinality = 0;
                int maxAttributeIndex = -1;
                for(int index = 0; index<domains.size(); index++){
                    int curCardinality = domains.get(index).size();
                    if(maxCardinality < curCardinality){
                        maxCardinality = curCardinality;
                        maxAttributeIndex = index;
                    }
                }

                //generalise values in that attribute

                //reset the domain of that attribute
                domains.remove(maxAttributeIndex);
                domains.put(maxAttributeIndex, new HashSet<>());

                //remember done generalisations so you won't have to traverse dgh every time
                Map<String, String> generalisations = new HashMap<>();

                for(Map.Entry<ArrayList<String>, Frequency> entry:entrySet){
                    String attributeValue = entry.getKey().get(maxAttributeIndex);
                    String generalisedAttributeValue = null;

                    if(generalisations.containsKey(attributeValue)){
                        generalisedAttributeValue = generalisations.get(attributeValue);
                    }
                    else{
                        generalisedAttributeValue = dghList.get(maxAttributeIndex).generalise(attributeValue);
                        //root
                        if(generalisedAttributeValue == null)continue;
                        generalisations.put(attributeValue, generalisedAttributeValue);
                    }

                    //update tuple
                    ArrayList<String> newQ = entry.getKey();
                    Frequency newF = entry.getValue();

                    newQ.set(maxAttributeIndex, generalisedAttributeValue);

                    //if there exists a tuple like this
                    if(qi_frequency.containsKey(newQ)){
                        Frequency oldQ = qi_frequency.get(newQ);

                        newF.increaseOccurrences(oldQ.getOccurrences());
                        for(int rowNum: oldQ.getRowIndexList())newF.insertRowNumber(rowNum);

                        //remove the old one
                        qi_frequency.remove(oldQ);
                    }

                    //insert the generalised value into that attributes domain
                    domains.get(maxAttributeIndex).add(generalisedAttributeValue);
                }

                int p = gen_levels.get(maxAttributeIndex);
                gen_levels.remove(maxAttributeIndex);
                gen_levels.put(maxAttributeIndex, p+1);
            }
            //the rest that remains -> suppress
            else{
                for(Map.Entry<ArrayList<String>, Frequency> entry:entrySet){
                    if(entry.getValue().getOccurrences()<k){
                        quasiIdentifiers.supress(entry.getValue().getRowIndexList());
                    }
                }
                break;
            }
        }

    }
}
