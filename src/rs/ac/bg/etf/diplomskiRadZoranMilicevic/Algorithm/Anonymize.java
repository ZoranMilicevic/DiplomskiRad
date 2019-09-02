package rs.ac.bg.etf.diplomskiRadZoranMilicevic.Algorithm;

import rs.ac.bg.etf.diplomskiRadZoranMilicevic.Data.QuasiIdentifiers;
import rs.ac.bg.etf.diplomskiRadZoranMilicevic.DomainGeneralisationHierarchy.DGH;

import java.lang.reflect.Array;
import java.util.*;

public class Anonymize {
    public static void anonymize(int k, ArrayList<DGH> dghList, QuasiIdentifiers quasiIdentifiers){
        Map<Integer, Set<String>> domains = new HashMap<>();
        for(int i=0; i<quasiIdentifiers.getNUmberOfColumns(); i++)domains.put(i, new HashSet<>());

        Map<ArrayList<String>, Frequency> qi_frequency = createQi_Frequency(quasiIdentifiers,domains);

        while(true){
            //find how many tuples don't satisfy K-anonymity
            int numberOfNonKanonTuples = findNumberOfNonKanonTuples(k, qi_frequency);

            if(numberOfNonKanonTuples > k){
                //find attribute with max cardinality
                int maxAttributeIndex = findAttributeWithMaxCardinality(domains);

                //generalise values in that attribute
                //reset the domain of that attribute
                domains.remove(maxAttributeIndex);
                domains.put(maxAttributeIndex, new HashSet<>());

                //remember done generalisations so you won't have to traverse dgh every time
                Map<String, String> generalisations = new HashMap<>();

                Set<Map.Entry<ArrayList<String>, Frequency>> entrySet = qi_frequency.entrySet();
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
                    //TODO - shitty contains and remove doesn't work!
                    ArrayList<String> oldQ = null;
                    for(Map.Entry<ArrayList<String>, Frequency> entry2:entrySet){
                        if(entry2.getKey().equals(newQ) && entry2.getKey()!=newQ){
                            Frequency oldF = entry2.getValue();
                            newF.increaseOccurrences(oldF.getOccurrences());
                            for(int rowNum: oldF.getRowIndexList())newF.insertRowNumber(rowNum);
                             oldQ = entry2.getKey();
                        }
                    }

                    if(oldQ!=null){
                        Map<ArrayList<String>, Frequency> qi_frequency2 = new HashMap<>();
                        for(Map.Entry<ArrayList<String>, Frequency> entry2:entrySet) {
                            if(entry2.getKey()!=oldQ){
                                qi_frequency2.put(entry2.getKey(), entry2.getValue());
                            }
                        }
                        qi_frequency=qi_frequency2;
                        entrySet = qi_frequency.entrySet();
                    }

                    //insert the generalised value into that attributes domain
                    domains.get(maxAttributeIndex).add(generalisedAttributeValue);
                }
            }
            //the rest that remains -> suppress
            else{
                suppressTheRest(k, quasiIdentifiers, qi_frequency);
                break;
            }
        }

    }

    private static Map<ArrayList<String>, Frequency> createQi_Frequency(QuasiIdentifiers quasiIdentifiers, Map<Integer, Set<String>> domains){
        Map<ArrayList<String>, Frequency> qi_frequency = new HashMap<>();
        for(int i =0; i<quasiIdentifiers.getNumberOfRows(); i++){
            ArrayList<String> row = quasiIdentifiers.getRow(i);
            if(qi_frequency.containsKey(row)){
                qi_frequency.get(row).increaseOccurrences();
                qi_frequency.get(row).insertRowNumber(i);
            }
            else{
                qi_frequency.put(row, new Frequency(i));
                for(int j = 0; j<row.size(); j++){
                    String value = row.get(j);
                    domains.get(j).add(value);
                }
            }
        }
        return qi_frequency;
    }

    private static int findNumberOfNonKanonTuples(int k, Map<ArrayList<String>, Frequency> qi_frequency){
        int numberOfNonKanonTuples = 0;
        Set<Map.Entry<ArrayList<String>, Frequency>> entrySet = qi_frequency.entrySet();
        for(Map.Entry<ArrayList<String>, Frequency> entry:entrySet){
            if(entry.getValue().getOccurrences() < k)numberOfNonKanonTuples+=entry.getValue().getOccurrences();
        }
        return numberOfNonKanonTuples;
    }

    private static int findAttributeWithMaxCardinality(Map<Integer, Set<String>> domains){
        int maxCardinality = -1;
        int maxAttributeIndex = -1;
        for(int index = 0; index<domains.size(); index++){
            int curCardinality = domains.get(index).size();
            if(maxCardinality < curCardinality){
                maxCardinality = curCardinality;
                maxAttributeIndex = index;
            }
        }
        return maxAttributeIndex;
    }

    private static void suppressTheRest(int k, QuasiIdentifiers quasiIdentifiers, Map<ArrayList<String>, Frequency> qi_frequency){
        Set<Map.Entry<ArrayList<String>, Frequency>> entrySet = qi_frequency.entrySet();
        for(Map.Entry<ArrayList<String>, Frequency> entry:entrySet){
            if(entry.getValue().getOccurrences()<k){
                quasiIdentifiers.supress(entry.getValue().getRowIndexList());
            }
        }
    }
}
