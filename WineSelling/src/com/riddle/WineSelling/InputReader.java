package com.riddle.WineSelling;
import java.io.*;
import java.util.*;

/**
 * @author Jasleen
 * This class reads input from the file input.txt and stores the data in a map
 */

public class InputReader {

    //This map stores a map of person Id to the set of ids of the wines she desires.
    private Map<Integer, Set<Integer>> personIdToWineIdsMap;

    /*This map stores a map of Wine Ids and number of people who want that wine.
     * Number of people who want this wine gives the popularity score for the wine
     */
    private Map<Integer, Integer> wineIdToNumberOfPeopleMap;
    private ValueComparator valueComparator;

    public InputReader() {
        /*The following initializations can be done using spring dependency injection
         * or using a factory class. 
         */
        personIdToWineIdsMap = new HashMap<Integer, Set<Integer>>();
        wineIdToNumberOfPeopleMap = new HashMap<Integer, Integer>();
        valueComparator = new ValueComparator(wineIdToNumberOfPeopleMap);
    }

    public void readInputToMap() throws IOException{
        String inputFileName = "input.txt";
        String line = null;
        //Opening connection to the input file
        BufferedReader br = new BufferedReader(new FileReader(inputFileName));
        while ((line = br.readLine()) != null) {
            StringTokenizer tokens = new StringTokenizer(line);
            //Take only integers
            int personId = Integer.parseInt(tokens.nextToken().replaceFirst("person", ""));
            int wineId = Integer.parseInt(tokens.nextToken().replaceFirst("wine", ""));

            //populate the input map which stores personId to the list of wine Ids
            if (personIdToWineIdsMap.containsKey(personId)) {
                personIdToWineIdsMap.get(personId).add(wineId);
            } else {
                HashSet<Integer> wineIdList = new HashSet<Integer>();
                wineIdList.add(wineId);
                personIdToWineIdsMap.put(personId, wineIdList);
            }

            //populate wineIds map which stores wineIds and the number of people who want this wine
            if (wineIdToNumberOfPeopleMap.containsKey(wineId)) {
                wineIdToNumberOfPeopleMap.put(wineId, wineIdToNumberOfPeopleMap.get(wineId)+1);
            } else {
                wineIdToNumberOfPeopleMap.put(wineId, 1);
            }
        }
        //Close the connection to the input stream
        br.close();
    }

    public Map<Integer, Set<Integer>> getPersonIdToWineIdsMap() {
        return personIdToWineIdsMap;
    }

    public Map<Integer, Integer> getWineIdToNumberOfPeopleMap() {
        return wineIdToNumberOfPeopleMap;
    }
    public ValueComparator getValueComparatorObject() {
        return valueComparator;
    }
}

class ValueComparator implements Comparator<Integer> {
    Map<Integer, Integer> base;

    public ValueComparator(Map<Integer, Integer> base) {
        this.base = base;
    }

    public int compare(Integer a, Integer b) {
        if (base.get(a) < base.get(b)) {
            return -1;
        } else{ //if (base.get(a) > base.get(b)){
            return 1;
        } /*else {
            return 0;
        }*/
    }
}