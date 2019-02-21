package csvProject;


import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        List<String [] > records = CsvFileManager.read ("./data/interview.csv");
        DataBaseManager.insertRecordsIntoTableX (records);

    }
}

