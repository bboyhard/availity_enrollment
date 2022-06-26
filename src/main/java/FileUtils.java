import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class FileUtils {
    // Method for creating a csv file and sorting enrollees.
    public static void dumpCSV(String csvFileName, List<Enrollee> enrollees) {
        // Add a header to the csv file.
        List<String> header = new ArrayList<>();
        header.add("userId");
        header.add("firstName");
        header.add("lastName");
        header.add("version");
        header.add("insuranceCompany");
        try {
            BufferedWriter writer = java.nio.file.Files.newBufferedWriter(Paths.get(csvFileName));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(header.toArray(new String[0])));
            // Sort based on lastname and firstname of enrollee.
            enrollees.sort(Comparator.comparing(Enrollee::getLastName).thenComparing(Enrollee::getFirstName));
            for (Enrollee enrollee : enrollees) {
                csvPrinter.printRecord(enrollee.getValues());
            }
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Method to read from a csv file the return a list of enrolless.
    public List<Enrollee> readCsvToEnrollee (String filePath) {
        CsvSchema orderLineSchema = CsvSchema.emptySchema().withHeader();
        List<Enrollee> returnList = new ArrayList<>();
        CsvMapper csvMapper = new CsvMapper();
            try {
                MappingIterator<Enrollee> orderLines = csvMapper.readerFor(Enrollee.class)
                        .with(orderLineSchema)
                        .readValues(new File(filePath));

                returnList = orderLines.readAll();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        return returnList;
    }
}
