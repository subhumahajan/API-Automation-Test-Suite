package api.Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) 
        {
        	String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new Date());
        	String reportName = "CRUDAPI_" + timeStamp + ".html";	
            ExtentSparkReporter spark = new ExtentSparkReporter("./reports/" + reportName);
            spark.config().setDocumentTitle("REQRES API Test Automation Report");
            spark.config().setReportName("REQRES API Testing");
            spark.config().setTheme(Theme.DARK);

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("User", System.getProperty("user.name"));
        }
        return extent;
    }
}
