package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private static ExtentReports extentReports = ReportManager.getInstance();
    private static ExtentTest test;

    @Override
    public void onTestStart(ITestResult result){
        test = extentReports.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result){
        test.pass("test executado com sucesso");
    }

    @Override
    public void onTestFailure (ITestResult result){
        test.fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result){
        test.skip("Teste ignorado");
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }
}
