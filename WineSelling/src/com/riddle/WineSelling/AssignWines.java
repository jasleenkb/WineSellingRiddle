package com.riddle.WineSelling;
import java.util.*;
import java.util.Map.Entry;

/**
 * @author Jasleen
 * This class assigns the wines to the personIds:
 * the least desired wine needs to be sold first and so on.
 * The least desired will go the person with the least score.
 */

public class AssignWines {
    private int totalWinesSold;
    private final int MAX_WINES_ASSIGNED = 3;

    //This map stores the final ouput of the problem: Stores personId to WineIds the person gets
    private Map<Integer, Set<Integer>> personIdToWineIdsSoldMap;

    //This map stores a map of wineIds and all the personIds who want a particular wine 
    private Map<Integer, Set<Integer>> wineIdToPersonIdsMap;

    public AssignWines() {
        totalWinesSold = 0;
        personIdToWineIdsSoldMap = new HashMap<Integer, Set<Integer>>();
        wineIdToPersonIdsMap = new HashMap<Integer, Set<Integer>>();
    }

    /**
     * This method finally assigns wines to people starting from the least popular wine.
     * @param personIdToWineIdsMap
     * @param personIdToScoreMap
     * @param wineIdToRankMap
     */
    public void assignWinesToPeople(Map<Integer, Set<Integer>> personIdToWineIdsMap,
            Map<Integer, Integer> personIdToScoreMap, Map<Integer, Integer> wineIdToRankMap) {

        //Evaluate the map of wineId to the personIds who want this wine
        evaluateWineIdToPersonIdsMap(personIdToWineIdsMap, wineIdToRankMap);
        Iterator<Entry<Integer, Set<Integer>>> it = wineIdToPersonIdsMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Set<Integer>> pair = (Map.Entry<Integer, Set<Integer>>) it.next();
            int wineId = Integer.parseInt(pair.getKey().toString());
            Set<Integer> personIdsInterestedInThisWine = (Set<Integer>) pair.getValue();

            //Who gets this wine finally
            int personIdWithMinScore = assignThisWineToAPersonId(personIdsInterestedInThisWine,
                    personIdToScoreMap);
            if (personIdWithMinScore == -1) {
                //nobody gets this wine as everyone is assigned maximum number of wines already
                continue;
            }

            if (personIdToWineIdsSoldMap.containsKey(personIdWithMinScore)) {
                personIdToWineIdsSoldMap.get(personIdWithMinScore).add(wineId);
            } else {
                HashSet<Integer> wineIdList = new HashSet<Integer>();
                wineIdList.add(wineId);
                personIdToWineIdsSoldMap.put(personIdWithMinScore, wineIdList);
            }
            totalWinesSold += 1; //This wine is Sold! :)
        }
    }

    public int getTotalWinesSold() {
        return totalWinesSold;
    }

    public Map<Integer, Set<Integer>> getPersonIdToWineIdsSoldMap() {
        return personIdToWineIdsSoldMap;
    }

    public Map<Integer, Set<Integer>> getWineIdToPersonIdsMap() {
        return wineIdToPersonIdsMap;
    }

    
    /**
     * This method maps the wineId to the list of the people who desire this wine.
     * @param personIdToWineIdsMap
     * @param wineIdToRankMap
     */
    private void evaluateWineIdToPersonIdsMap(Map<Integer, Set<Integer>> personIdToWineIdsMap, 
            Map<Integer, Integer> wineIdToRankMap) {

        Iterator<Entry<Integer, Integer>> it = wineIdToRankMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> pair = (Map.Entry<Integer, Integer>) it.next();
            int wineId = Integer.parseInt(pair.getKey().toString());
            HashSet<Integer> personIdsSet = findPersonIdsInterestedInThisWine(personIdToWineIdsMap, wineId);
            wineIdToPersonIdsMap.put(wineId, personIdsSet);
        }
    }

    private HashSet<Integer> findPersonIdsInterestedInThisWine(
            Map<Integer, Set<Integer>> personIdToWineIdsMap, int wineId) {
        HashSet<Integer> personIdsSet = new HashSet<Integer>();
        Iterator<Entry<Integer, Set<Integer>>> it = personIdToWineIdsMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Set<Integer>> pair = (Map.Entry<Integer, Set<Integer>>)it.next();
            int personId = Integer.parseInt(pair.getKey().toString());
            Set<Integer> wineIds = (Set<Integer>) pair.getValue();
            if (wineIds.contains(wineId)) {
                personIdsSet.add(personId);
            }
        }
        return personIdsSet;
    }

    private int assignThisWineToAPersonId(Set<Integer> personsInterestedInThisWine,
            Map<Integer, Integer> personIdToScoreMap) {
        int minScore = Integer.MAX_VALUE;
        int score = 0;
        int personIdWithMinScore = -1;
        Iterator<Integer> it = personsInterestedInThisWine.iterator();
        while (it.hasNext()) {
            int personId = Integer.parseInt(it.next().toString());
            if(personIdToWineIdsSoldMap.containsKey(personId)) {
                if (personIdToWineIdsSoldMap.get(personId).size() == MAX_WINES_ASSIGNED)//check this
                    continue;
            }
            score = personIdToScoreMap.get(personId);
            if (score < minScore) {
                minScore = score;
                personIdWithMinScore = personId;
            }
        }
        return personIdWithMinScore;
    }
}
