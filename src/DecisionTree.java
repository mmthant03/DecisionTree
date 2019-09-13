import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class DecisionTree {

    String[] headers;



    public static void main(String[]args) {
        //TODO
        String inputFile = args[0];
        String outputFile = args[1];



    }

    public void importCsv(String inputFile) throws FileNotFoundException {
        BufferedReader input = new BufferedReader(new FileReader(inputFile));
        String row = "";
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            // do something with the data
        }
        csvReader.close();
    }
}
