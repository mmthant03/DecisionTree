import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import FeatureExtraction.Feature;

public class DecisionTree {

    public static String[] headers;
    public static int winnerPos = 42;
    public static ArrayList<Feature> features;

    /**
     * main method
     * this method takes two arguments
     * First Argument is the path to the input csv file
     * Second Argument is the path to where the output csv file will be written
     * @param args [pathToInputFile, pathToOutputFile]
     */
    public static void main(String[]args) {
        //TODO
        String inputFile = args[0];
        //String outputFile = args[1];
        try {
            importCsv(inputFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // testing the csv file input
        for (Feature f: features) {
            f.display();
        }
        for (String h: headers) {
            System.out.print(h+" ");
        }
        System.out.println();
    }

    /**
     * this method takes in the input file and
     * read that
      * @param inputFile
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void importCsv(String inputFile) throws FileNotFoundException, IOException {
        features = new ArrayList<>();
        // Build a buffer for input file
        BufferedReader input = new BufferedReader(new FileReader(inputFile));
        // Extract the header first
        String header = input.readLine();
        headers = (header!=null) ? header.split(",") : headers;
        // Then, row are extracted.
        String row = "";
        while ((row = input.readLine()) != null) {
            String[] data = row.split(",");
            // do something with the data
            Feature f = new Feature(buildBoard(data), winner(data));
            features.add(f);
        }
        input.close();
    }

    public static int[][] buildBoard(String[] board) {
        int[][] newBoard = new int[6][7];
        int boardIndex = 0;
        for (int column = 0; column < 7; column++) {
            for (int row = 0; row < 6; row++) {
                newBoard[row][column] = Integer.parseInt(board[boardIndex]);
                boardIndex++;
            }
        }
        return newBoard;
    }

    public static int winner(String[] board) {
        return Integer.parseInt(board[winnerPos]);
    }
}
