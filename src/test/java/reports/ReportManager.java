package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.BeforeSuite;

public class ReportManager {
    private static String reportPath = "target/reports/index.html";
    private static ExtentReports extentReports;
    private static ExtentTest extentTest;


    public static ExtentReports getInstance() {
        if (extentReports == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setReportName("Automation Test API");
            spark.config().setDocumentTitle("Dog API test execution");

            extentReports = new ExtentReports();
            extentReports.attachReporter(spark);

            extentReports.setSystemInfo("Framework", "TestNG");
            extentReports.setSystemInfo("Language", "Java");
            extentReports.setSystemInfo("Tester", "Jeison");
        }
        return extentReports;
    }
}
