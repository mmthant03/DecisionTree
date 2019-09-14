import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import FeatureExtraction.Feature;

public class DecisionTree {

    public static String[] headers; // store the headers from csv file
    public static int winnerPos = 42; // winner is always at 43rd column of the csv file
    public static ArrayList<Feature> features; // features extracted from given board configuration

    /**
     * main method
     * this method takes two arguments
     * First Argument is the path to the input csv file
     * Second Argument is the path to where the output csv file will be written
     * @param args [pathToInputFile, pathToOutputFile]
     */
    public static void main(String[]args) {
        String inputFile = "";
        String outputFile = "";
        // if no input is specified, alert and exit the program
        if(args.length < 1) {
            System.out.println("Please enter input file name!");
            System.exit(0);
        }
        // if input is specified but output is not, use the default output file
        else if(args.length == 1) {
            inputFile = args[0];
            outputFile = "trainingData/output.csv";
            System.out.println("You forgot to enter output file name! Data will be written to '"+outputFile+"' as default!");

        }
        // if input and output are both specified, use them as they are
        else if(args.length == 2) {
            inputFile = args[0];
            outputFile = args[1];
        }

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
        // Then, row are extracted line by line.
        String row = "";
        while ((row = input.readLine()) != null) {
            String[] data = row.split(",");
            // do something with the data
            Feature f = new Feature(buildBoard(data), winner(data));
            features.add(f);
        }
        input.close();
    }

    /**
     * build the board from flatten string array
     * @param board
     * @return
     */
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

    /**
     * get the winner piece from string array
     * @param board
     * @return
     */
    public static int winner(String[] board) {
        return Integer.parseInt(board[winnerPos]);
    }
    
    /**
     * function returns the most common output value among a set of examples
     * @param featureList
     * @return
     */
    public int pluralityValue(List<Feature> featureList) {
    	int count0 = 0;
    	int count1 = 0;
    	int count2 = 0;
    	int currentWinner;
    	for(int k = 0; k < featureList.size(); k++) {
    		currentWinner = featureList.get(k).getWinner();
    		if(currentWinner == 1) {
    			count1++;
    		} else if(currentWinner == 2) {
    			count2++;
    		} else {
    			count0++;
    		}
    	}
    	
    	if((count1 >= count2) && (count1 >= count0)) {
    		return 1;
    	} else if((count1 < count2) && (count2 >= count0)) {
    		return 2;
    	} else {
    		return 0;
    	}
    }
}
