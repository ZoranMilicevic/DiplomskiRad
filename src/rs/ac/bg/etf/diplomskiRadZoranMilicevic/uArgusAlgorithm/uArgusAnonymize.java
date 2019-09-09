package rs.ac.bg.etf.diplomskiRadZoranMilicevic.uArgusAlgorithm;

import rs.ac.bg.etf.diplomskiRadZoranMilicevic.Data.QuasiIdentifiers;
import rs.ac.bg.etf.diplomskiRadZoranMilicevic.DataflyAlgorithm.Frequency;
import rs.ac.bg.etf.diplomskiRadZoranMilicevic.DomainGeneralisationHierarchy.DGH;

import java.util.*;

public class uArgusAnonymize {
    public static void uArgusAnonymize(int k, ArrayList<DGH> dghList, QuasiIdentifiers quasiIdentifiers){
        ArrayList<ArrayList<String>> columns = new ArrayList<>();
        for (int i = 0; i < quasiIdentifiers.getNUmberOfColumns(); i++) {
            columns.add(new ArrayList<>());
        }

        for(ArrayList<String> row:quasiIdentifiers.getQuasiIdentifiersByRows()){
            int i = 0;
            for(String value:row){
                columns.get(i++).add(value);
            }
        }

        int i = 0;
        for(ArrayList<String> column: columns){
            generaliseColumn(k, column, dghList.get(i++));
        }

        for(ArrayList<String> column: columns){
            for(String value: column) System.out.println(value);
        }

    }

    private static ArrayList<String> generaliseColumn(int k, ArrayList<String> column, DGH dgh) {
        Map<String, Frequency> qi_frequency = new HashMap<>();
        int i = 0;
        for(String value:column){
            if(qi_frequency.containsKey(value)){
                qi_frequency.get(value).increaseOccurrences();
                qi_frequency.get(value).insertRowNumber(i++);
            }
            else{
                qi_frequency.put(value, new Frequency(i++));
            }
        }

        while(true){
            int numberOfNonKanonValues = findNumberOfNonKanonValues(k, qi_frequency);

            if(numberOfNonKanonValues>k){
                Map<String, String> generalisations = new HashMap<>();
                Set<Map.Entry<String, Frequency>> entrySet = qi_frequency.entrySet();

                for(Map.Entry<String, Frequency> entry:entrySet){
                    String attributeValue = entry.getKey();
                    String generalisedAttributeValue = null;

                    if(generalisations.containsKey(attributeValue)){
                        generalisedAttributeValue = generalisations.get(attributeValue);
                    }
                    else{
                        generalisedAttributeValue = dgh.generalise(attributeValue);
                        //root
                        if(generalisedAttributeValue == null)continue;
                        generalisations.put(attributeValue, generalisedAttributeValue);
                    }

                    if(qi_frequency.containsKey(generalisedAttributeValue)){
                        qi_frequency.get(generalisedAttributeValue).increaseOccurrences(entry.getValue().getOccurrences());
                        qi_frequency.get(generalisedAttributeValue).insertRowNumbers(entry.getValue().getRowIndexList());
                        qi_frequency.remove(attributeValue);
                    }
                    else{
                        Frequency oldF = qi_frequency.get(attributeValue);
                        qi_frequency.remove(attributeValue);
                        qi_frequency.put(generalisedAttributeValue, oldF);
                    }
                    //entrySet = qi_frequency.entrySet();
                }
            }
            else{
                suppressTheRest(k, qi_frequency);
                updateValues(column, qi_frequency);
                return column;
            }
        }
    }

    private static void suppressTheRest(int k, Map<String, Frequency> qi_frequency) {
        Set<Map.Entry<String, Frequency>> entrySet = qi_frequency.entrySet();
        for(Map.Entry<String, Frequency> entry: entrySet){
            if(entry.getValue().getOccurrences()<k){
                Frequency oldF = entry.getValue();
                qi_frequency.remove(entry.getKey());
                qi_frequency.put("*", oldF);
                entrySet = qi_frequency.entrySet();
            }
        }
    }

    private static void updateValues(ArrayList<String> column, Map<String, Frequency> qi_frequency) {
        Set<Map.Entry<String, Frequency>> entrySet = qi_frequency.entrySet();
        for(Map.Entry<String, Frequency> entry: entrySet){
            for(int i: entry.getValue().getRowIndexList()){
                column.set(i, entry.getKey());
            }
        }
    }

    private static int findNumberOfNonKanonValues(int k, Map<String, Frequency> qi_frequency) {
        int numberOfNonKanonValues = 0;
        Set<Map.Entry<String, Frequency>> entrySet = qi_frequency.entrySet();
        for(Map.Entry<String, Frequency> entry: entrySet){
            if(entry.getValue().getOccurrences()<k)numberOfNonKanonValues += entry.getValue().getOccurrences();
        }

        return numberOfNonKanonValues;
    }




}
