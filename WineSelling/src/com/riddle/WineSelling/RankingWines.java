package com.riddle.WineSelling;
import java.util.*;

/**
 * @author Jasleen
 * This class ranks wines according to their popularity
 * starting from the least desired wine to the most desired wine
 */

public class RankingWines {
    private Map<Integer, Integer> wineIdToRankMap;

    public void rankWines(Map<Integer, Integer> wineIdToNumberOfPeopleMap, ValueComparator valueComparator) {
        wineIdToRankMap = new TreeMap<Integer, Integer>(valueComparator);
        wineIdToRankMap.putAll(wineIdToNumberOfPeopleMap);
    }

    public Map<Integer, Integer> getWineIdToRankMap() {
        return wineIdToRankMap;
    }
}