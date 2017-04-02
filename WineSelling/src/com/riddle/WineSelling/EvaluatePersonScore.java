package com.riddle.WineSelling;
import java.util.*;
import java.util.Map.Entry;

/**
 * @author Jasleen
 * This class evaluates the score of a person and stores it in a map
 * where score of a person is equal to the sum of the ranks of the wines she desires
 */

public class EvaluatePersonScore {

    //This map stores the person Id and her score.
    private Map<Integer, Integer> personIdToScoreMap;

    public EvaluatePersonScore() {
        personIdToScoreMap = new HashMap<Integer, Integer>();
    }

    /**
     * This method calculates the score of each personId and stores it in personIdToScoreMap
     * @param personIdToWineIdsMap
     * @param wineIdToRankMap
     */
    public void calcScore(Map<Integer, Set<Integer>> personIdToWineIdsMap,
            Map<Integer, Integer> wineIdToRankMap) {
        Iterator<Entry<Integer, Set<Integer>>> it = personIdToWineIdsMap.entrySet().iterator();
        while (it.hasNext()) {
            int score = 0;
            Map.Entry<Integer, Set<Integer>> pair = (Map.Entry<Integer, Set<Integer>>) it.next();
            int personId = Integer.parseInt(pair.getKey().toString());
            Set<Integer> wineIds = (Set<Integer>) pair.getValue();
            score = findScoreValue(wineIds, wineIdToRankMap);
            personIdToScoreMap.put(personId, score);
        }
    }

    public Map<Integer, Integer> getPersonIdToScoreMap() {
        return personIdToScoreMap;
    }

    private int findScoreValue(Set<Integer> wineIds, Map<Integer, Integer> wineIdToRankMap) {
        int score = 0;
        Iterator<Integer> it = wineIds.iterator();
        while (it.hasNext()) {
            int wineId = Integer.parseInt(it.next().toString());
            if (wineIdToRankMap.containsKey(wineId)) {
                score += wineIdToRankMap.get(wineId);
            }
        }
        return score;
    }
}
