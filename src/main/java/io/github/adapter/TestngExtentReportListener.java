package io.github.adapter;


import com.aventstack.extentreports.service.ExtentTestManager;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;

@Listeners({ExtentITestListenerClassAdapter.class})
public interface TestngExtentReportListener {

    @AfterMethod
    default void afterMethod(ITestResult result) {
        String[] namePaht = result.getTestClass().getName().split("\\.");
        String name=namePaht[namePaht.length-1];
        switch (result.getStatus()) {
            case ITestResult.FAILURE:
                ExtentTestManager.getTest(result).assignCategory(name);
                break;
            case ITestResult.SKIP:
                ExtentTestManager.getTest(result).assignCategory(name);
                break;
            default:
                ExtentTestManager.getTest(result).assignCategory(name);
                break;
        }
    }
}
