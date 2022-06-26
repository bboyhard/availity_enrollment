
import java.util.*;
import java.util.stream.Collectors;

public class FrontEnd {
    public static void main(String[] args) {

        Scanner scanner= new Scanner(System.in);
        System.out.print("Enter path the CSV file: ");
        String filePath= scanner.nextLine();

        FileUtils fileUtils = new FileUtils();

        List<Enrollee> listOfEnrollees = fileUtils.readCsvToEnrollee(filePath);
        // Using java 8 collectors we can group all  enrollees into a map based on insurance companies.
        Map<String, List<Enrollee>> enrolleeByInsurance = listOfEnrollees.stream()
                .collect(Collectors
                .groupingBy(Enrollee::getInsuranceCompany));
        // With that map we can then process the creation of the CSV file per insurance company.
        for(Map.Entry<String, List<Enrollee>> entry : enrolleeByInsurance.entrySet()) {
            FileUtils.dumpCSV("availity_sortedData_"+entry.getKey(),entry.getValue());
        }
    }
}
