package io.github.adapter;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.adapter.listener.ExtentITestListenerClassAdapter;
import io.github.adapter.service.ExtentService;
import io.github.adapter.service.ExtentTestManager;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;

@Listeners({ExtentITestListenerClassAdapter.class})
public class TestngExtentReportListener {
    protected transient static ExtentTest test;
    protected transient static ExtentReports extent = ExtentService.getInstance();
    protected transient static ExtentSparkReporter spark = ExtentService.getSpark();


    @AfterMethod
    protected void afterMethod(ITestResult result) {
        test = ExtentTestManager.getTest(result);
        String[] namePath = result.getTestClass().getName().split("\\.");
        String name = namePath[namePath.length - 1];
        switch (result.getStatus()) {
            case ITestResult.FAILURE:
                test.assignCategory(name);
                break;
            case ITestResult.SKIP:
                test.assignCategory(name);
                break;
            default:
                test.assignCategory(name);
                break;
        }
    }
}
