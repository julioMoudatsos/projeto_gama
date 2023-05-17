import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportNameGenerator {

    public static String generateReportName() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String currentDate = dateFormat.format(new Date());
        return "Report_" + currentDate + ".html";
    }

}

