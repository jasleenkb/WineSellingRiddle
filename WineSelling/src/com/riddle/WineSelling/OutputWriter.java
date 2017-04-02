package com.riddle.WineSelling;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

/**
 * @author Jasleen
 * This class writes output of the wine selling problem into the output file.
 * 
 */

public class OutputWriter {

    /**
     * @param personIdToWineIdsSoldMap
     * @param totalWinesSold
     * @throws IOException
     */
    public void writeOutputToFile(Map<Integer, Set<Integer>> personIdToWineIdsSoldMap,
            int totalWinesSold) throws IOException {

        final String outputFileName = "output.txt";
        //Open the connection to the output file
        BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName));

        //Write the Total Wines Sold to the output file as the first line of input
        //line.append("Total Wines Sold = ").append(totalWinesSold);
        bw.write("Total Wines Sold = " + totalWinesSold + "\n");

        Iterator<Entry<Integer, Set<Integer>>> it = personIdToWineIdsSoldMap.entrySet().iterator();
        while (it.hasNext()) {
            StringBuilder line = new StringBuilder();
            Map.Entry<Integer, Set<Integer>> pair = (Map.Entry<Integer, Set<Integer>>) it.next();
            int personId = Integer.parseInt(pair.getKey().toString());
            Set<Integer> winesReceived = (Set<Integer>) pair.getValue();
            line.append("person").append(personId).append("\t").append("wine\n");
            findAndPopulateWinesReceived(winesReceived, line, bw);
        }
        //Close the connection to the output stream
        bw.close();
    }

    private void findAndPopulateWinesReceived(Set<Integer> winesReceived, StringBuilder line,
            BufferedWriter bw) throws IOException{
        Iterator<Integer> it = winesReceived.iterator();
        while(it.hasNext()) {
            int wineId = Integer.parseInt(it.next().toString());
            line.append(wineId);
            bw.write(line.toString());
        }
    }
}
