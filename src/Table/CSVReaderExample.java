package Table;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVReaderExample {
    public static void main(String[] args) {
        String csvFile = "C:\\Users\\Anurag\\eclipse-workspace\\WebTable\\target\\test.csv";
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yy-HH-mm-ss");

        // Define the regex pattern to search for lines containing "roof"
        String regex = ".*\\broof\\b.*";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] nextLine;
            List<List<String>> csvData = new ArrayList<>();

            // Read and store the CSV data
            while ((nextLine = reader.readNext()) != null) {
                List<String> row = new ArrayList<>();
                for (String cell : nextLine) {
                    row.add(cell);
                }
                csvData.add(row);

                // Check if the line contains "roof" using regex
                String line = String.join(" ", row);
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    System.out.println("Line related to 'roof': " + line);
                }
            }

            // Print the CSV data to the console
            for (List<String> row : csvData) {
                for (String cell : row) {
                    System.out.print(cell + "\t"); // Use "\t" for tab-separated output
                }
                // Print the current timestamp
                String timestamp = dateFormat.format(new Date());
                System.out.print(timestamp);
                System.out.println(); // Move to the next line after each row
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}







