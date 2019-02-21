package csvProject;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CsvFileManager {
    public static List<String [] > read(String pathUrl) {
        List<String [] > records = new ArrayList <> ();
        try {
            Reader rd = Files.newBufferedReader (Paths.get (pathUrl));
            CSVReader reader = new CSVReaderBuilder (rd).withSkipLines (1).build ( );
            records = reader.readAll ();

        } catch (Exception e) {
            e.printStackTrace ( );
        }
        return records;
    }

    public static void write(ArrayList <String[]> badData) {
        String url = "./data/badData.csv";
        try {
            BufferedWriter writer = Files.newBufferedWriter (Paths.get (url));
            CSVWriter csvWriter = new CSVWriter (writer,
                    CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            for (String[] line : badData) {
                csvWriter.writeNext (line);
            }
            writer.close ( );
        } catch (IOException e) {
            e.printStackTrace ( );
        }
    }
}
