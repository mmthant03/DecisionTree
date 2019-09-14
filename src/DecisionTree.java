import java.io.*;
import java.util.ArrayList;

import FeatureExtraction.Feature;

public class DecisionTree {

    public static String[] headers; // store the headers from csv file
    public static ArrayList<String> boardData = new ArrayList<>();
    public static String[] newFeatures = {"bottom_left", "center", "bottom_right", "higher_chances"};
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
            outputCsv(outputFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // testing the input
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
            boardData.add(row);
            // Extract features from the data
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
     * @return player who wins the game at given configuration
     */
    public static int winner(String[] board) {
        return Integer.parseInt(board[winnerPos]);
    }

    /**
     * writes the data to the output csv file
     * @param outputFile
     * @throws IOException
     */
    public static void outputCsv(String outputFile) throws IOException{
        FileWriter output = new FileWriter(outputFile);
        // append the original headers
        for (String h : headers) {
            output.append(h);
            output.append(",");
        }
        // append the new features
        for (int i = 0 ; i < newFeatures.length; i++) {
            output.append(newFeatures[i]);
            if(i != newFeatures.length - 1) output.append(",");
            else output.append("\n");
        }
        String bottomLeft = "";
        String center = "";
        String bottomRight = "";
        String higherChances = "";
        // append the data row by row
        for (int i = 0 ; i < boardData.size(); i++) {
            output.append(boardData.get(i));
            output.append(",");
            bottomLeft = ""+features.get(i).getBottomLeft();
            output.append(bottomLeft);
            output.append(",");
            center = ""+features.get(i).getCenter();
            output.append(center);
            output.append(",");
            bottomRight = ""+features.get(i).getBottomRight();
            output.append(bottomRight);
            output.append(",");
            higherChances = ""+features.get(i).getHigherChances();
            output.append(higherChances);
            output.append("\n");
        }
        output.flush();
        output.close();
    }
}
