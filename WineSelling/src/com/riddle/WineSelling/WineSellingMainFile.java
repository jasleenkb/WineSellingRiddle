package com.riddle.WineSelling;
import java.io.IOException;

/**
 * @author Jasleen
 * This class solves the wine tasting problem where one person can be assigned as many as 3 wines
 * but one wine is assigned to only one person.
 */

public class WineSellingMainFile {

    private static InputReader inputReader;
    private static RankingWines rankingWines;
    private static EvaluatePersonScore evalScore;
    private static AssignWines assignWines;
    private static OutputWriter outputWriter;

    public static void main(String[] args) throws IOException{
        //read the input into a map
        inputReader = new InputReader();
        inputReader.readInputToMap();

        //Rank the wines in the order of least wanted to most wanted wine
        rankingWines = new RankingWines();
        rankingWines.rankWines(inputReader.getWineIdToNumberOfPeopleMap(), inputReader.getValueComparatorObject());

        //Evaluate Score of a person where score = sum of ranks of the wines she desires
        evalScore = new EvaluatePersonScore();
        evalScore.calcScore(inputReader.getPersonIdToWineIdsMap(), rankingWines.getWineIdToRankMap());

        //Finally, assign the wines to the people
        assignWines = new AssignWines();
        assignWines.assignWinesToPeople(inputReader.getPersonIdToWineIdsMap(),
                evalScore.getPersonIdToScoreMap(), rankingWines.getWineIdToRankMap());

        //Write output in another file
        outputWriter = new OutputWriter();
        outputWriter.writeOutputToFile(assignWines.getPersonIdToWineIdsSoldMap(),
                assignWines.getTotalWinesSold());
    }
}